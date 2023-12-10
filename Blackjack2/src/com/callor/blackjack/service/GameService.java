package com.callor.blackjack.service;

import java.util.ArrayList;
import java.util.List;

import com.callor.blackjack.model.CardDto;

public class GameService {
	
	
    private List<CardDto> playerDeckList = new ArrayList<>();
    private List<CardDto> dealerDeckList = new ArrayList<>();
    private CardService cardService; // CardService 객체를 멤버로 추가
    public List<CardDto> getPlayerDeck() {
        return this.playerDeckList;
    }

    public List<CardDto> getDealerDeck() {
        return this.dealerDeckList;
    }

    public int calculateSum(List<CardDto> deckList) {
        int sumValue = 0;
        int aceCount = 0; // A 카드의 개수를 세기 위한 변수

        for (CardDto dto : deckList) {
            int cardValue = dto.getValue();

            if (cardValue == 1) { // A 카드가 있는 경우
                aceCount++;
                sumValue += 11; // 우선 11로 계산

                // 만약 총 합이 21을 넘어가면 A를 1로 계산
                if (sumValue > 21) {
                    sumValue -= 10; // A를 1로 계산하여 총합에서 10을 빼줌
                }
            } else {
                sumValue += cardValue;
            }
        }

        // A 카드가 있고, 총 합이 21을 넘지 않으면서 11로 계산된 경우
        // A를 1로 계산하여 총 합에서 10을 빼줌
        while (aceCount > 0 && sumValue > 21) {
            sumValue -= 10;
            aceCount--;
        }

        return sumValue;
    }

    // 나머지 메서드는 그대로 유지

    public GameService() {
        this.cardService = new CardService();
        this.cardService.makeDeck();
    }

 // GameService 클래스의 start 메서드 수정

    public void start() {
        this.playerDeckList = new ArrayList<>();
        this.dealerDeckList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            CardDto playerCard = this.cardService.getRandomCard();
            this.playerDeckList.add(playerCard);

            CardDto dealerCard = this.cardService.getRandomCard();
            this.dealerDeckList.add(dealerCard);
        }
    }





    public void showPlayerDeck() {
        System.out.println("플레이어의 카드:");
        for (CardDto playerCard : this.playerDeckList) {
            System.out.print(playerCard + " ");
        }
        System.out.println();
    }

    public void showDealerDeck() {
        System.out.println("딜러의 카드:");
        for (CardDto dealerCard : this.dealerDeckList) {
            System.out.print(dealerCard + " ");
        }
        System.out.println();
    }
 // GameService 클래스의 hit 메서드 수정

 // GameService 클래스의 hit 메서드 수정

    public void hit(List<CardDto> myDeckList, List<CardDto> pubDeckList, String playName) {
        // (기존 코드)
        if (myDeckList.size() >= 2) {
            System.out.println(playName + "는 이미 2장의 카드를 가지고 있습니다.");
            return;
        }

        if (pubDeckList.isEmpty()) {
            System.out.println("덱에 카드가 없습니다. 카드를 추가하세요.");
            return;
        }

        // 딜러의 카드 합이 16 이하일 때 딜러에게 1장 더 주기
        int sumValue = calculateSum(myDeckList);
        if (playName.equals("딜러") && sumValue <= 16) {
            myDeckList.add(pubDeckList.get(0));
            pubDeckList.remove(0);
            System.out.println("딜러가 1장을 더 받았습니다.");
            return;
        }

        // 받은 카드를 내 카드 리스트에 추가
        myDeckList.add(pubDeckList.get(0));
        // 받을 카드에서 1장을 제외
        pubDeckList.remove(0);

        // (기존 코드)
        sumValue = calculateSum(myDeckList); // 카드 합 구하기

        System.out.println("-".repeat(50));
        System.out.println("현재점수 : " + sumValue);

        // 21을 초과하는지 체크하여 bust 여부 판단
        if (sumValue > 21) {
            System.out.println(playName + " Bust!");
        }
    }
 // GameService 클래스에 딜러가 카드를 추가하는 메소드 추가
    public void dealerHit(List<CardDto> pubDeckList) {
        int dealerSum = calculateSum(dealerDeckList);
        while (dealerSum <= 16) {
            cardService.hit(dealerDeckList, pubDeckList, "딜러");
            dealerSum = calculateSum(dealerDeckList);
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
    }


}
