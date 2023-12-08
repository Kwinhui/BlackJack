package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;

public class CardService {
	String suit = "♣,◆,♠,♥";
	String value = "A,2,3,4,5,6,7,8,9,10,K,Q,J";
	private CardDto card = null;
	private List<CardDto> cards = null;

	public CardService() {
		card = new CardDto();
		cards = new ArrayList<CardDto>();
	}

	public List<CardDto> getDeck() {
		return this.cards;
	}

	public void makeDeck() {

		String[] suits = suit.split(",");
		String[] values = value.split(",");

		for (String suit : suits) {
			int intValue = 0;
			for (String value : values) {
				try {
					intValue = Integer.valueOf(value);

				} catch (Exception e) {
					if (value.equals("A")) {

						intValue = 1;
					} else
						intValue = 10;

				}
//				System.out.println(intValue + suit);

//				Collections.shuffle(cards);
//				System.out.println(intValue);
//				System.out.println(cards);


				CardDto card = new CardDto();
				card.setSuit(suit);
				card.setValue(intValue);
				card.setDenomination(value);
				cards.add(card);
				cards.size();
			}

		} // end for

	} // end makeDeck

	public CardDto getRandomCard() {
		// 카드 뽑기
		int size = cards.size();
		int select = (int) (Math.random() * size);

		return cards.get(select);
	}

	public CardDto draw() {
		// 카드 제거
		CardDto selectedCard = getRandomCard();
		cards.remove(selectedCard); // 뽑은 카드 제거
		return selectedCard;
	}
	
	// 딜러 카드의 합이 16 이하이면 1장 추가
	public void playingPhase() {
		List<CardDto> deckList = new ArrayList<CardDto>();
		String[] suits = suit.split(",");
		String[] values = value.split(",");
		CardDto cards = new CardDto();
		makeDeck();
		String str = value;
		
		int num = 0;
		try {
			num = Integer.valueOf(str);
		} catch (Exception e) {
			
		}
		if(num > 16) {
			getRandomCard();
		}
		
		
	}
	
	

//	public CardDto getCard() {
//		return null;
//	}

}
// end class
