package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.utils.Line;


public class CardService {
	private List<CardDto> cards;
	private String suit = "♣,◆,♠,♥";
	private String value = "A,2,3,4,5,6,7,8,9,10,K,Q,J";

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

	// getRandomCard() method 실행시 
	// 52장의 덱 리스트중 랜덤카드 1장씩 출력후
	// 뽑은 카드를 바로 리스트카드 에서 제거
	public CardDto getRandomCard() {
		int size = cards.size();


		int select = (int) (Math.random() * size);

		CardDto selectedCard = cards.get(select);
		
		cards.remove(select); // 뽑은카드 cards 리스트에서 제거

		return selectedCard;
	}

}
