package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.model.GameDto;
import com.callor.blackjack.utils.Line;

public class GameService {

	private List<CardDto> playerDeckList = null;
	private List<CardDto> dealerDeckList = null;
	private GameDto gameDto = null;
	private CardService cardService = null; // CardService 객체를 멤버로 추가

	public GameService() {
		this.playerDeckList = new ArrayList<>();
		this.dealerDeckList = new ArrayList<>();
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
	    int count = 1;
	    for (int i = 0; i < 2; i++) {
	        CardDto playerCard = this.cardService.getRandomCard();
	        this.playerDeckList.add(playerCard);

//	        if (i == 0) { // 첫 번째 카드일 때
//	            CardDto dealerCard = this.cardService.getRandomCard();
//	            this.dealerDeckList.add(dealerCard);
//	        } else { // 두 번째 카드일 때
//	            CardDto dealerCard = this.cardService.getRandomCard();
//	            this.dealerDeckList.add(dealerCard);
//	        }
	    }
	    for (int i = 0; i < 2; i++) {
	    	CardDto dealerCard = this.cardService.getRandomCard();
	    	this.dealerDeckList.add(dealerCard);
	    }
	    
	}

	public void showPlayerDeck() {
		System.out.print("플레이어의 카드 : ");
		
		for (CardDto playerCard : this.playerDeckList) {
			System.out.print(playerCard + " ");
		}
		System.out.println();
	}

	public void showDealerDeck() {
		System.out.print("딜러의 카드 : ");
		for (CardDto dealerCard : this.dealerDeckList) {
			System.out.print(dealerCard + " ");
		}
		System.out.println();
	}

	public void hit(List<CardDto> myDeckList, List<CardDto> pubDeckList, String playName) {
	    if (pubDeckList.isEmpty()) {
	        System.out.println("덱에 카드가 없습니다. 카드를 추가하세요.");
	        return;
	    }

	    int sumValue = calculateSum(myDeckList); // 플레이어 또는 딜러의 합 계산

	    if (playName.equals("딜러") && sumValue < 17) {
	        // 딜러이며 합이 16 이하일 때 카드 추가
	        myDeckList.add(pubDeckList.get(0));
	        pubDeckList.remove(0);
	        System.out.println("딜러가 받은 카드: " + myDeckList.get(myDeckList.size() - 1)); // 받은 카드 출력
	        System.out.println("딜러가 1장을 더 받았습니다.");
	    }
//	    } else if (myDeckList.size() >= 2) {
//	        // 플레이어이며 이미 2장의 카드를 가지고 있을 때
//	        System.out.println(playName + "는 이미 2장의 카드를 가지고 있습니다.");
//	    } else {
//	        // 카드를 받는 경우
	        CardDto newCard = pubDeckList.get(0);
	        myDeckList.add(newCard);
	        pubDeckList.remove(0);

	        sumValue = calculateSum(myDeckList); // 카드 합 다시 계산

	        Line.sLine(50);
	        System.out.println("현재점수 : " + sumValue);
	        System.out.println("플레이어가 받은 카드: " + myDeckList.get(myDeckList.size() - 1)); // 받은 카드 출력

	        // A 카드 처리 - A를 11로 더했을 때 21을 넘으면 1로 처리
	        for (CardDto card : myDeckList) {
	            if (card.getDenomination().equals("A") && sumValue > 21) {
	                sumValue -= 10; // A를 1로 처리
	            }
	        }

	        // 21을 초과하는지 체크하여 bust 여부 판단
	        if (sumValue > 21) {
	            System.out.println(playName + " Bust!");
	        }
	    }
	


	public void dealerHit(List<CardDto> pubDeckList) {
	    int dealerSum = calculateSum(dealerDeckList); // 딜러의 현재 카드 합 계산
	    while (dealerSum < 17) { // 딜러의 합이 17 미만일 때만 추가 카드 뽑기
	        cardService.hit(dealerDeckList, pubDeckList, "딜러");
	        dealerSum = calculateSum(dealerDeckList); // 딜러가 카드를 받은 후에 합을 다시 계산합니다
	    }
	}
	

	// GameService 클래스에 승자를 판별하는 메소드 추가
	public void judgeWinner(int dealerSum, int playerSum) {
		if (dealerSum > 21 && playerSum <= 21) {
			System.out.println("딜러 Bust! 플레이어 승!");
		} else if (playerSum > 21) {
			System.out.println("플레이어 Bust! 딜러 승!");
		} else if (dealerSum > playerSum) {
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
