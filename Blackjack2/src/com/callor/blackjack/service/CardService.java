package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.utils.Line;


public class CardService {
	String suit = "♣,◆,♠,♥";
	String value = "A,2,3,4,5,6,7,8,9,10,K,Q,J";
	private List<CardDto> cards;

	public CardService() {
		cards = new ArrayList<CardDto>();

	}

	public List<CardDto> getDeck() {
		return this.cards;
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
		
		cards.remove(select); // 뽑은카드 cards 리스트에서 제거
		

		
		

		return selectedCard;
	}

}
