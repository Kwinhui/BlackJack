package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;

public class CardService {
    String suit = "♣,◆,♠,♥";
    String value = "A,2,3,4,5,6,7,8,9,10,K,Q,J";
    private List<CardDto> cards;
    private List<CardDto> pubDeckList;

    public CardService() {
        cards = new ArrayList<CardDto>();
        pubDeckList = new ArrayList<CardDto>();
    }

    public List<CardDto> getDeck() {
        return this.cards;
    }
    
    private int calculateSum(List<CardDto> deckList) {
        int sumValue = 0;
        for (CardDto dto : deckList) {
            sumValue += dto.getValue();
        }
        return sumValue;
    }

    public void makeDeck() {
        String[] suits = suit.split(",");
        String[] values = value.split(",");

        for (String suit : suits) {
            for (String value : values) {
                CardDto card = new CardDto();
                card.setSuit(suit);
                card.setDenomination(value);

                this.cards.add(card);
            }
        }
    }


    public CardDto getRandomCard() {
        int size = cards.size();
        int select = (int) (Math.random() * size);

        CardDto selectedCard = cards.get(select);
        cards.remove(select);

        return selectedCard;
    }

    public void hit(List<CardDto> myDeckList, List<CardDto> pubDeckList, String playName) {
        if (pubDeckList.isEmpty()) {
            System.out.println("덱에 카드가 없습니다. 카드를 추가하세요.");
            return;
        }

        // 받은 카드를 내 카드 리스트에 추가
        myDeckList.add(pubDeckList.get(0));
        // 받을 카드에서 1장을 제외
        pubDeckList.remove(0);
        
        int sumValue = calculateSum(myDeckList); // 카드 합 구하기

        System.out.println("-".repeat(50));
        System.out.println("현재점수 : " + sumValue);

        // 21을 초과하는지 체크하여 bust 여부 판단
        if (sumValue > 21) {
            System.out.println(playName + " Bust!");
        }
    }
}
