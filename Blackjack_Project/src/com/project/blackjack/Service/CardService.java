package com.project.blackjack.Service;

import java.util.ArrayList;
import java.util.List;

import com.project.blackjack.model.CardDto;

public class CardService {
	String suit = "♣,◆,♠,♥";
	String value = "A,2,3,4,5,6,7,8,9,10,K,Q,J";
	private List<CardDto> deckList;
	// 2 ~ 10
	// ★ ◆ ♠ ♥

	public CardService() {
		this.deckList = new ArrayList<CardDto>();
	}

	public List<CardDto> getDeck() {
		return this.deckList;
	}

	public void makeDeck() {

		String[] suits = suit.split(",");
		String[] values = value.split(",");

		for (String suit : suits) {
			for (String value : values) {
				int intValue = 0;
				try {
					intValue = Integer.valueOf(value);

				} catch (Exception e) {
					if (value.equals("A")) {

						intValue = 1;
					} else
						intValue = 10;

				}
//				Collections.shuffle(deckList);	// = random

				CardDto blackjack = new CardDto();
				blackjack.setCardSuit(suit);
				blackjack.setDenomination(value);
				blackjack.setValue(intValue);
				deckList.add(blackjack);

			} // end for value
		} // end for suit

	} // end makeDeck
	
	// 랜덤으로 1장 뽑기
	public CardDto getRadnomCard() {
		int size = deckList.size();
		int select = (int) (Math.random() * size);
		
		
		return deckList.get(select);

	}

} // end class
