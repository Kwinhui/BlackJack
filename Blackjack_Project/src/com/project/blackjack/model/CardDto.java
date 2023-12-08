package com.project.blackjack.model;

	
	
public class CardDto {
	
	private String cardDeck;		// 52장의 숫자카드 + 패턴카드
	private String denomination;	// 2 ~ 10 4쌍
	private String cardSuit;		// ♥ ◆ ♣ ♠ 4쌍 
	private int value;				// 카드 2장의 합
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getCardDeck() {
		return cardDeck;
	}
	public void setCardDeck(String cardDeck) {
		this.cardDeck = cardDeck;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getCardSuit() {
		return cardSuit;
	}
	public void setCardSuit(String cardSuit) {
		this.cardSuit = cardSuit;
	}
	@Override
	public String toString() {
		return "[숫자 = " + denomination + ", 패턴 = " + cardSuit
				+ ", 카드 = " + value + cardSuit + "]";
	}
//	public String toString() {
//		return "[denomination=" + denomination + ", cardSuit=" + cardSuit
//				+ ", value=" + value + "]";
//	}
	
	
	
	
}
