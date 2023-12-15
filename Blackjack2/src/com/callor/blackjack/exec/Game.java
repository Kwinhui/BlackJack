package com.callor.blackjack.exec;

import java.util.List;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.service.CardService;
import com.callor.blackjack.service.GameService;

public class Game {
	public static void main(String[] args) {

		
		CardService cardService = new CardService();

		GameService game = new GameService();


		List<CardDto> pubDeckList = cardService.getDeck();
	
		
		game.start(); // 게임 시작
		game.showPlayerDeck();
		game.showDealerDeck();
		
		
		game.dealerHit("딜러");
		
		game.playerHit("플레이어");
		game.calculateSum(pubDeckList);

		
		


	}
}
