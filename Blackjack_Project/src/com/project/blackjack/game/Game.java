package com.project.blackjack.game;

import java.util.List;

import com.project.blackjack.Service.CardService;
import com.project.blackjack.Service.GameService;
import com.project.blackjack.model.CardDto;

public class Game {
	public static void main(String[] args) {
		CardService card = new CardService();
		CardDto black = new CardDto();
		
		
		card.makeDeck();
		List<CardDto> deckList = card.getDeck();
		
		// 모든 덱list 랜덤으로 뽑기
//		for(BlackjackDto dto : deckList) {
//			System.out.println(dto);
//		}
		
//		System.out.println(card.getRadnomCard());
		GameService game = new GameService();
		System.out.println(game.DealerReceive());
		System.out.println(game.PlayerReceive());
		System.out.println(deckList.size());
		
		for(int i = 0; i < 4; i++) {
			if(game.DealerReceive().equals(deckList.get(i))) {
				deckList.remove(i);
				
			}
		}
		for(CardDto dto : deckList) {
			System.out.println(dto);
			
		}
		
		
		
		
	}
}
