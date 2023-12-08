package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;

public class GameService {
	List<CardDto> deckList = new ArrayList<CardDto>();
	private String Dealer;
	private String Player;
	public GameService() {
		this.Player = "플레이어";
		this.Dealer = "딜러";
	}
	public void getCard(CardDto card) {
		this.deckList.add(card);
	}
	
	public void Start() {
		CardService cards = new CardService();
		deckList = cards.getDeck();
		CardDto cardDto = new CardDto();
		CardService card = new CardService();
		System.out.println("카드를 섞겠습니다.");
		card.makeDeck();
		System.out.print("플레이어 2장 : ");
		for(int i = 0; i < 2; i++) {
			System.out.print(card.getRandomCard());
		}
		System.out.println();
		System.out.print("딜러 2장 : ");
		for(int i = 0; i < 2; i++) {
			card.getRandomCard();
			System.out.print(card.getRandomCard());
			


		}
	}
	
		

		// 진짜됐나??
		
				

}
