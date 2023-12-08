package com.project.blackjack.model;

import java.util.List;

public class GamerDto {
	
	public List<CardDto> deckList;			// 플레이어,딜러가 받은 카드를 저장할 List
	public List<CardDto> dealerHand;
	public List<CardDto> playerHand;
	public List<CardDto> getDealerHand() {
		return dealerHand;
	}
	public List<CardDto> getPlayerHand() {
		return playerHand;
	}
	
	
	
	

	
}
