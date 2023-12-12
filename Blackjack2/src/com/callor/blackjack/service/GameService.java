package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.model.GameDto;
import com.callor.blackjack.utils.Line;

public class GameService {
	private List<CardDto> cards = null;
	private List<CardDto> playerDeckList = null;
	private List<CardDto> dealerDeckList = null;
	private GameDto gameDto = null;
	private CardService cardService = null; // CardService 객체를 멤버로 추가

	public GameService() {
		this.cards = new ArrayList<CardDto>();
		this.playerDeckList = new ArrayList<CardDto>();
		this.dealerDeckList = new ArrayList<CardDto>();
		this.cardService = new CardService();
		this.cardService.makeDeck();
		this.gameDto = new GameDto();

	}

	public List<CardDto> getPlayerDeck() {

		return this.playerDeckList;
	}

	public List<CardDto> getDealerDeck() {
		return this.dealerDeckList;
	}

	public int calculateSum(List<CardDto> deckList) {
		int sumValue = 0;
		int aceCount = 0; // A 카드의 개수를 세기 위한 변수 추가

		for (CardDto dto : deckList) {
			int cardValue = dto.getValue();

			if (cardValue == 1) { // A 카드인 경우
				aceCount++;
				sumValue += 1;

			}

			else {
				sumValue += cardValue;
			}
		}

		return sumValue;
	}

	public void start() {
		this.playerDeckList = new ArrayList<>();
		this.dealerDeckList = new ArrayList<>();
		
		
		
		
		int playerSum = calculateSum(getPlayerDeck()); // 플레이어 또는 딜러의 합 계산
		int DealerSum = calculateSum(getDealerDeck());
		int count = 2;

//		System.out.print("플레이어의 카드 : ");
		for (int i = 0; i < count; i++) {
			CardDto playerCard = this.cardService.getRandomCard();
			while(cards.contains(playerCard)) {
				playerCard = this.cardService.getRandomCard();
			}
			cards.remove(playerCard);
			this.playerDeckList.add(playerCard);

//			System.out.print(playerCard +" ");
		}
		

//		System.out.print("딜러의 카드 : ");
		for (int i = 0; i < count; i++) {
			CardDto dealerCard = this.cardService.getRandomCard();
	        while (cards.contains(dealerCard)) {
	            dealerCard = this.cardService.getRandomCard();
	        }

			cards.remove(dealerCard);

			this.dealerDeckList.add(dealerCard);
//			System.out.print(dealerCard + " ");
		}
		

//		if (sumValue < 17) {
//			System.out.println("딜러의 합이 16 미만입니다!! 한장 추가!");
//			CardDto dealerCard = this.cardService.getRandomCard();
//			this.dealerDeckList.add(dealerCard);
//			
//		}


	}

	public void showPlayerDeck() {
		int sumValue = calculateSum(getPlayerDeck());
		System.out.print("플레이어의 카드 : ");

		for (CardDto playerCard : this.playerDeckList) {
			System.out.print(playerCard + " ");
		}
		System.out.println();
		System.out.println("플레이어 합 : " + sumValue);
	}

	public void showDealerDeck() {
		int sumValue = calculateSum(getDealerDeck());
		System.out.print("딜러의 카드 : ");
		for (CardDto dealerCard : this.dealerDeckList) {
			System.out.print(dealerCard + " ");
		}
		System.out.println();
		System.out.println("딜러 합 : " + sumValue);
	}

	public void hit(List<CardDto> myDeckList, List<CardDto> pubDeckList, String playName) {
		int sumValue = calculateSum(myDeckList); // 플레이어 또는 딜러의 합 계산
		CardDto playerCard = this.cardService.getRandomCard();
		this.playerDeckList.add(playerCard);
//		if (playName.equals("딜러") && sumValue < 17) {
//			// 딜러이며 합이 16 이하일 때 카드 추가
//			myDeckList.add(pubDeckList.get(0));
//			pubDeckList.remove(0);
			System.out.println("딜러가 받은 카드: " + myDeckList.get(myDeckList.size())); // 받은 카드 출력
//			System.out.println("딜러가 1장을 더 받았습니다.");
//		}

//		CardDto newCard = pubDeckList.get(0);
//		myDeckList.add(newCard);
//		pubDeckList.remove(0);

		sumValue = calculateSum(getPlayerDeck()); // 카드 합 다시 계산

//		Line.sLine(50);
//		System.out.println("플레이어 현재점수 : " + sumValue);
//		System.out.println("플레이어가 받은 카드: " + myDeckList.get(myDeckList.size() - 1)); // 받은 카드 출력

		// 21을 초과하는지 체크하여 bust 여부 판단
		if (sumValue > 21) {
			System.out.println(playName + " Bust!");
		}
	}

	public void playerHit(List<CardDto> pubDeckList, String player) {
		int sumValue = calculateSum(playerDeckList);

		System.out.println("플레이어 hit 한장 추가!");
		CardDto playerCard = this.cardService.getRandomCard();
		this.playerDeckList.add(playerCard);
		System.out.println("추가한 카드 : " + playerCard);
		
		sumValue = calculateSum(playerDeckList);
		if (sumValue > 21) {
			System.out.println("플레이어 Bust!! 딜러 Win!!");
			return;
		}

	}

	public void dealerHit(List<CardDto> pubDeckList, String dealer) {
		int sumValue = calculateSum(dealerDeckList);

		while (sumValue < 17) {
			Line.sLine(50);
			System.out.println("딜러의 합이 16 미만입니다!! 한장 추가!");
			CardDto dealerCard = this.cardService.getRandomCard();
			System.out.println("추가한 카드 : " + dealerCard + " ");
			this.dealerDeckList.add(dealerCard);
			sumValue = calculateSum(dealerDeckList);
			showDealerDeck();
			if (sumValue > 21) {
				System.out.println("딜러 Bust!! 플레이어 Win!!");
				break;
			}

		}

//		System.out.println(dealerDeckList);

	}

	// GameService 클래스에 승자를 판별하는 메소드 추가
	public void judgeWinner(int dealerSum, int playerSum) {
//		if (dealerSum > 21 && playerSum <= 21) {
//			System.out.println("딜러 Bust! 플레이어 승!");
//		}
//		else if (playerSum > 21) {
//			System.out.println("플레이어 Bust! 딜러 승!");
//		}
		if (dealerSum > playerSum) {
			System.out.println("딜러 승!");
		} else if (dealerSum < playerSum) {
			System.out.println("플레이어 승!");
		} else {
			System.out.println("무승부!");
		}
		if (dealerSum == 21 && playerSum == 21) {
			System.out.printf("딜러 카드 합 : %d, 플레이어 카드 합 : %d 무승부!!", dealerSum, playerSum);
		}
	}

}
