package com.project.blackjack.Service;

import java.util.ArrayList;
import java.util.List;

import com.project.blackjack.model.CardDto;
import com.project.blackjack.model.GamerDto;

public class GameService {
	CardDto cardDto = null;
	List<CardDto> deckList = null;
	CardService cardService = null;
	List<CardService> deck = null;
	public GameService() {
		cardService = new CardService();
		deck = new ArrayList<CardService>();
		cardDto = new CardDto();
		deckList = new ArrayList<CardDto>();
				
	}
	GamerDto gamerDto = new GamerDto();
	public String PlayerReceive() {
//		List<CardDto> deckList = cardService.getDeck();
		
		for(int i = 0; i < 2; i++) {
//			deckList = new ArrayList<CardDto>();
//			cardService.makeDeck();
//			cardService.getDeck();
			System.out.println(cardService.getRadnomCard());
//			deckList.remove(i);
//			deckList.remove(0);
			
//			deckList.remove(i);
		}
		return "";
		
	}
	
	public String DealerReceive() {
		
		for(int i = 0; i < 2; i++) {
			cardService.makeDeck();
			cardService.getDeck();
			System.out.println(cardService.getRadnomCard());
//			deckList.remove(cardService.getRadnomCard());
			
			
		}
		return "";
		
	}
	
}
