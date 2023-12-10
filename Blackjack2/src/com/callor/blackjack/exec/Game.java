package com.callor.blackjack.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.service.CardService;
import com.callor.blackjack.service.GameService;

public class Game {
	public static void main(String[] args) {
		CardService cardService = new CardService();
		cardService.makeDeck();
		GameService game = new GameService();

		List<CardDto> myDeckList = new ArrayList<>();
		List<CardDto> pubDeckList = cardService.getDeck();

		game.start(); // 게임 시작

		// 딜러에게 카드 나눠주기
		cardService.hit(game.getDealerDeck(), pubDeckList, "딜러");
		game.showDealerDeck(); // 딜러 카드 보여주기

		// 플레이어에게 카드 나눠주기
		cardService.hit(game.getPlayerDeck(), pubDeckList, "플레이어");
		game.showPlayerDeck(); // 플레이어 카드 보여주기

		// 딜러와 플레이어 카드의 합을 출력
		int dealerSum = game.calculateSum(game.getDealerDeck());
		int playerSum = game.calculateSum(game.getPlayerDeck());
		System.out.println("딜러의 카드 합 : " + dealerSum);
		System.out.println("플레이어의 카드 합 : " + playerSum);

		Scanner scanner = new Scanner(System.in);

		while (true) {
		    System.out.println("카드를 더 받으시겠습니까? (hit/stay)");
		    String playerChoice = scanner.nextLine();

		    if (playerChoice.equalsIgnoreCase("hit")) {
		        cardService.hit(game.getPlayerDeck(), pubDeckList, "플레이어");
		        game.showPlayerDeck(); // 플레이어 카드 보여주기
		        playerSum = game.calculateSum(game.getPlayerDeck());
		        System.out.println("플레이어의 카드 합 : " + playerSum);

		        if (playerSum > 21) {
		            System.out.println("플레이어 Bust!");
		            break; // 게임 종료
		        }
		    } else if (playerChoice.equalsIgnoreCase("stay")) {
		        System.out.println("플레이어가 Stay를 선택했습니다.");
		        break; // 게임 종료
		    } else {
		        System.out.println("올바른 명령을 입력하세요.");
		    }
		}

		// 게임 종료 후 결과 출력
		game.dealerHit(pubDeckList); // 딜러 카드 추가
		game.showDealerDeck(); // 딜러 카드 보여주기
		dealerSum = game.calculateSum(game.getDealerDeck());
		System.out.println("딜러의 카드 합 : " + dealerSum);

		game.judgeWinner(dealerSum, playerSum); // 승자 판별

	}
}
