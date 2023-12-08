package com.callor.blackjack.exec;

import java.util.List;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.service.CardService;
import com.callor.blackjack.service.GameService;

public class Game {
	public static void main(String[] args) {
		CardService card = new CardService();
		List<CardDto> deckList = card.getDeck();
		card.makeDeck();
		
		
		
		
//		System.out.println(card.getRandomCard());
//		System.out.println(card.draw());
//		System.out.println(card.getCard());
//		for(CardDto dto : deckList) {
//			System.out.println(dto);
//			
//		}
		// 랜덤카드뽑기
		card.getRandomCard();
		GameService game = new GameService();
		game.Start();
//		card.draw();
//		card.draw();
		System.out.println(deckList.size());

//		System.out.println(card.getRandomCard());
		

	}

}
