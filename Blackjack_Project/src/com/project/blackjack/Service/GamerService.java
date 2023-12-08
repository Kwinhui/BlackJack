package com.project.blackjack.Service;

import java.util.ArrayList;
import java.util.List;

import com.project.blackjack.model.CardDto;
import com.project.blackjack.model.GamerDto;

public class GamerService {
	
	CardDto cardDto = new CardDto();
	CardService cardService = new CardService();
	GamerDto gamerDto = new GamerDto();

		List<CardDto>dealerHand = new ArrayList<CardDto>();
		List<CardDto>plyerHand = new ArrayList<CardDto>();
	
	
	
	public void receive() {
		
		dealerHand.add(cardService.getRadnomCard());
		
		
		
		
	}
		
		
	
	
	
	public static void main(String[] args) {
		GamerDto gamerDto = new GamerDto();
		
		System.out.println(gamerDto.getPlayerHand());
		
		
	}
	
}
