package com.callor.blackjack.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Delayed;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.service.CardService;
import com.callor.blackjack.service.GameService;
import com.callor.blackjack.utils.Line;

public class Game {
	public static void main(String[] args) {

		CardService cardService = new CardService();
		cardService.makeDeck();

		GameService game = new GameService();

		List<CardDto> myDeckList = new ArrayList<>();
		List<CardDto> pubDeckList = cardService.getDeck();
		int dealerSum = game.calculateSum(game.getDealerDeck());
		int playerSum = game.calculateSum(game.getPlayerDeck());

		game.start(); // 게임 시작
		game.showPlayerDeck();
		game.showDealerDeck();

		game.dealerHit(pubDeckList, "딜러");

//		CardDto cardDto = new CardDto();
//		List<CardDto> cards = new ArrayList<CardDto>();
//		for(CardDto dto : pubDeckList) {
//			System.out.println(dto + " ");
//			
//		}

		Scanner scanner = new Scanner(System.in);

//		if (playerSum > 21) {
//			
//			return;
//		} else if (dealerSum > 21) {
//			
//			return;
//		}

		while (true) {

			System.out.print("카드를 더 받으시겠습니까? (hit/stay) >> ");
			String playerChoice = scanner.nextLine();

			if (playerChoice.equalsIgnoreCase("hit")) {

				CardDto playerCard = cardService.getRandomCard();
//				System.out.println("추가한 카드 : " + playerCard + " ");

				int sumValue = game.calculateSum(myDeckList);
				Line.sLine(50);
				game.playerHit(pubDeckList, "플레이어");
				game.showPlayerDeck();
				game.showDealerDeck();
			}
				if (playerSum > 21) {
					System.out.println("플레이어 Bust! 더 이상 카드를 받을 수 없습니다.");
					break;
				} else if (playerSum > 21) {
					System.out.println("플레이어 Bust!");
					break; // 게임 종료
				} else if (playerChoice.equalsIgnoreCase("stay")) {
				System.out.println("플레이어가 Stay를 선택했습니다.");
				break; // 게임 종료
			} else {
				System.out.println("올바른 명령을 입력하세요.");
			}

			// end if
		}	// end while

//		// 딜러의 카드 합을 계산하고 게임 결과 판별
		dealerSum = game.calculateSum(game.getDealerDeck());
		System.out.println("딜러의 카드 합 : " + dealerSum);
		game.judgeWinner(dealerSum, playerSum); // 승자 판별

	}
}
