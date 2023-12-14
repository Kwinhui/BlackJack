package com.callor.blackjack.model;

public class GameDto {
	public int sumValue;	// 카드의 합을 계산
	
	public int playerSum;	// 플레이어 카드의 합
	public int dealerSum;	// 딜러 카드의 합
	public int getSumValue() {
		return sumValue;
	}
	public int getPlayerSum() {
		return playerSum;
	}
	public int getDealerSum() {
		return dealerSum;
	}
	
	

}
