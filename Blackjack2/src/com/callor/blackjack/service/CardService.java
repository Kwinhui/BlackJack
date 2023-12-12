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
    private CardDto cardDto = null;

    public CardService() {
        cards = new ArrayList<CardDto>();
        pubDeckList = new ArrayList<CardDto>();
        cardDto = new CardDto();
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
        if(size == 0) {
        	return null;
        }
        int select = (int) (Math.random() * size);

        CardDto selectedCard = cards.get(select);
//        System.out.println(selectedCard);
        cards.remove(select);

        return selectedCard;
    }

//    public void hit(List<CardDto> myDeckList, List<CardDto> pubDeckList, String playName) {
////        if (pubDeckList.isEmpty()) {
////            System.out.println("덱에 카드가 없습니다. 카드를 추가하세요.");
////            return;
////        }
//
//        int sumValue = calculateSum(myDeckList); // 플레이어 또는 딜러의 합 계산
//
//        CardDto dealerCard = this.getRandomCard();
//        sumValue = calculateSum(myDeckList); // 카드 합 다시 계산
//        cards.add(cardDto);
//        if (sumValue > 21) {
//            System.out.println(playName + " Bust!"); // 버스트인 경우 더 이상 카드를 받지 못하도록 처리
//            return;
//        }
        
        
        
         
        
//            CardDto newCard = pubDeckList.get(0);
//            myDeckList.add(newCard);
//            pubDeckList.remove(0);
//            System.out.println(pubDeckList);

//            Line.sLine(50);
//            System.out.println("현재점수 : " + sumValue);
//            System.out.println("플레이어가 받은 카드1: " + myDeckList.get(myDeckList.size() - 1)); // 받은 카드 출력
//            System.out.println(myDeckList.get(myDeckList.size() - 1));



    
//    }
}
    
