package com.project.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {
	
	
	// 딜러가 16 미만이면 카드 한장 받고
	// 처음 받은 카드 2장의 합이 21이 되면 무승부
	// 						합이 21 초과 되면 진다.
	// 16 미만이면 반드시 hit 17 이상이면 hit and stay
	// 플레이어가 스탑을 했을때
	// 재시작 되면 카드는 초기화
	// 카드는 4개
	// A(1), 2~10, (K,Q,J = 10)
	// math.random
	
	private List<CardDeck> pattern = null;
	private List<CardDeck> num = null;
	public void deck() {
		
		
		
		String[] pattern1 = new String[] {"♥", "♣", "◆", "♠"};
		String[] num1 = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "K", "Q", "J" };
		
//		num = new ArrayList<>();
//		pattern = new ArrayList<>();
//		for (int i = 0; i < pattern1.length; i++) {
//			for(int j = 0; j < num1.length; j++) {
//				
//			}
//		}

		
		
		
	}
	
	
	


		
	
	
	
	
	
	
}
