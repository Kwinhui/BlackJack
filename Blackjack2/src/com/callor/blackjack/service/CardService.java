package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.utils.Line;

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

        int sumValue = calculateSum(myDeckList); // 플레이어 또는 딜러의 합 계산

        if (sumValue > 21) {
            System.out.println(playName + " Bust!"); // 버스트인 경우 더 이상 카드를 받지 못하도록 처리
            return;
        }

        // 여기서 카드를 받는 조건을 수정하여 원하는 대로 카드를 받을 수 있습니다.
        if (myDeckList.size() < 5) { // 예시로 최대 5장까지 카드를 받을 수 있도록 설정했습니다. 원하시는 대로 조절 가능합니다.
            CardDto newCard = pubDeckList.get(0);
            myDeckList.add(newCard);
            pubDeckList.remove(0);

            sumValue = calculateSum(myDeckList); // 카드 합 다시 계산

            Line.sLine(50);
            System.out.println("현재점수 : " + sumValue);
            System.out.println("플레이어가 받은 카드: " + myDeckList.get(myDeckList.size() - 1)); // 받은 카드 출력

            // A 카드 처리 - A를 11로 더했을 때 21을 넘으면 1로 처리
            for (CardDto card : myDeckList) {
                if (card.getDenomination().equals("A") && sumValue > 21) {
                    sumValue -= 10; // A를 1로 처리
                }
            }

            // 21을 초과하는지 체크하여 bust 여부 판단
            if (sumValue > 21) {
                System.out.println(playName + " Bust!");
            }
        } else {
            System.out.println(playName + "는 이미 최대 허용 카드 개수를 가지고 있습니다.");
        }
    }
}
    
