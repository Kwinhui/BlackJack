package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.blackjack.model.CardDto;
import com.callor.blackjack.model.GameDto;
import com.callor.blackjack.utils.Line;

public class GameService {
	private Scanner scan = null;
	private List<CardDto> cards = null;
	private List<CardDto> playerDeckList = null;
	private List<CardDto> dealerDeckList = null;
	private GameDto gameDto = null;
	private CardService cardService = null; // CardService 객체를 멤버로 추가

	public GameService() {
		this.scan = new Scanner(System.in);
		this.cards = new ArrayList<CardDto>();
		this.playerDeckList = new ArrayList<CardDto>();
		this.dealerDeckList = new ArrayList<CardDto>();
		this.cardService = new CardService();
		this.cardService.makeDeck();
		this.gameDto = new GameDto();

	}

	// 플레이어가 받은 카드 저장
	public List<CardDto> getPlayerDeck() {

		return this.playerDeckList;
	}

	// 딜러가 받은 카드 저장
	public List<CardDto> getDealerDeck() {
		return this.dealerDeckList;
	}

	// 플레이어와 딜러의 덱리스트에서 각각 정수의 합을 계산
	public int calculateSum(List<CardDto> deckList) {

		int sumValue = 0;

		// 플레이어와 딜러의 덱리스트에서 A일경우 1
		for (CardDto dto : deckList) {
			// CardDto의 getValue method에 적혀있는 A일땐 1,
			// K,Q,J 일땐 정수 10으로 리턴한 값을 cardValue에 저장
			int cardValue = dto.getValue();

			sumValue += cardValue;

		}
		return sumValue;
	}

	public void start() {
		
		// 플레이어와 딜러의 새로운 리스트 생성
		this.playerDeckList = new ArrayList<>();
		this.dealerDeckList = new ArrayList<>();
		// 2장씩 받아야함
		int count = 2;

		for (int i = 0; i < count; i++) {
			CardDto playerCard = this.cardService.getRandomCard();
			// List<CardDto> playerDeckList 에 playerCard 2장을 추가
			this.playerDeckList.add(playerCard);
			cards.add(playerCard);

		}

		for (int i = 0; i < count; i++) {
			CardDto dealerCard = this.cardService.getRandomCard();

			cards.remove(dealerCard);
			// List<CardDto> DealerDeckList 에 dealerCard 2장을 추가
			this.dealerDeckList.add(dealerCard);

			cards.add(dealerCard);

		}

	}
	// 플레이어 덱을 보여줌
	public void showPlayerDeck() {
		// 플레이어가 받은 덱리스트에서 합을 계산후 sumValue에 저장
		int sumValue = calculateSum(getPlayerDeck());
		System.out.print("플레이어의 카드 : ");
		// game() method 에서 2장씩 받은 덱리스트를
		// 한장씩 꺼내서 playerCard 출력
		for (CardDto playerCard : this.playerDeckList) {
			System.out.print(playerCard + " ");
		}
		System.out.println();
		System.out.println("플레이어 합 : " + sumValue);

	}
	// 딜러 덱을 보여줌
	public void showDealerDeck() {
		// 딜러가 받은 덱리스트에서 합을 계산후 sumValue에 저장
		int sumValue = calculateSum(getDealerDeck());
		System.out.print("딜러의 카드 : ");
		// game() method 에서 2장씩 받은 덱리스트를
		// 한장씩 꺼내서 dealerCard 출력
		for (CardDto dealerCard : this.dealerDeckList) {
			System.out.print(dealerCard + " ");
		}
		System.out.println();
		System.out.println("딜러 합 : " + sumValue);
		System.out.println("남은카드 : " + cardService.getDeck().size() + "장");

	}

	
	
	public void playerHit(String player) {
		
		int sumValue = calculateSum(playerDeckList);		// 플레이어 카드 합
		int sumValue2 = calculateSum(dealerDeckList);		// 딜러 카드 합
		while (true) {
			if (sumValue2 > 21)		// 플레이어 합이 21이 넘으면 종료
				return;
			System.out.print("카드를 더 받으시겠습니까? (hit/stay) >> ");
			String str = scan.nextLine();

			if (!str.equalsIgnoreCase("hit") && !str.equalsIgnoreCase("stay")) {
				System.out.println("올바른값을 입력해주세요");
				continue;
			}

			if (str.equalsIgnoreCase("hit")) {
				Line.sLine(50);
				System.out.println("플레이어 hit 한장 추가!");
				CardDto playerCard = this.cardService.getRandomCard();
				this.playerDeckList.add(playerCard);
				System.out.println("추가한 카드 : " + playerCard);
				showPlayerDeck();
				sumValue = calculateSum(playerDeckList);
				gameDto.playerSum = sumValue;
				System.out.println("남은카드 : " + cardService.getDeck().size() + "장");
				Line.sLine(50);

			}
			if (sumValue > 21) {
				System.out.println("플레이어 Bust!! 딜러 Win!!");

				return;
			}
			if (str.equalsIgnoreCase("stay")) {
				if (sumValue > sumValue2) {
					if (sumValue == 21) {
						System.out.println("남은카드 : " + cardService.getDeck().size() + "장");
						System.out.println("플레이어 블랙잭!");
						System.out.println("플레이어 Win!!");
						break;
					}
					System.out.println("남은카드 : " + cardService.getDeck().size() + "장");
					System.out.println("플레이어 Win!");

					break;
				} else if (sumValue < sumValue2) {
					if (sumValue2 == 21) {
						System.out.println("남은카드 : " + cardService.getDeck().size() + "장");
						System.out.println("딜러 블랙잭!");
						System.out.println("딜러 Win!!");
					}
					System.out.println("남은카드 : " + cardService.getDeck().size() + "장");
					System.out.println("딜러 Win!");

					break;
				} else if (sumValue == sumValue2) {
					System.out.println("남은카드 : " + cardService.getDeck().size() + "장");
					System.out.println("무승부!");

					break;
				}

			}

		}

	}

	public void dealerHit(String dealer) {
		int sumValue = calculateSum(dealerDeckList);

		while (sumValue < 17) {
			Line.sLine(50);
			System.out.println("딜러의 합이 16 미만입니다!! 한장 추가!");
			CardDto dealerCard = this.cardService.getRandomCard();
			System.out.println("추가한 카드 : " + dealerCard + " ");
			this.dealerDeckList.add(dealerCard);
			Line.sLine(50);

			sumValue = calculateSum(dealerDeckList);
			showDealerDeck();
			gameDto.dealerSum = sumValue;
			Line.sLine(50);
			this.showPlayerDeck();
			if (sumValue > 21) {
				System.out.println("딜러 Bust!! 플레이어 Win!!");

				break;
			}

		}

	}

}
