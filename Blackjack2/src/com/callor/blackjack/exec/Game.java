package com.callor.blackjack.exec;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.service.CardService;
import com.callor.blackjack.service.GameService;

public class Game {
	public static void main(String[] args) {
//		List<CardDto> cards = new ArrayList<CardDto>();
		
		CardService cardService = new CardService();

		GameService game = new GameService();


		List<CardDto> pubDeckList = cardService.getDeck();
//		int dealerSum = game.calculateSum(game.getDealerDeck());
//		int playerSum = game.calculateSum(game.getPlayerDeck());
		
		
		game.start(); // 게임 시작
		game.showPlayerDeck();
		game.showDealerDeck();
		
		
		game.dealerHit("딜러");
		
		game.playerHit("플레이어");
		game.calculateSum(pubDeckList);

		
		


	}
}
