package com.callor.blackjack.model;

public class CardDto {
    private String denomination;
    private String suit;
    private String value;
    
    
    
    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        if (denomination.equals("A")) {
            return 1;
        } else if (denomination.equals("K") || denomination.equals("Q") || denomination.equals("J")) {
            return 10;
        } else {
            try {
                return Integer.valueOf(denomination);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return suit + denomination + " ";
    }
}
