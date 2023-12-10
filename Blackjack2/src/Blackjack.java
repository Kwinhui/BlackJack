import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    private List<String> deck;
    private List<String> playerHand;
    private List<String> dealerHand;

    public Blackjack() {
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }

    public void createDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
        Collections.shuffle(deck);
    }

    public int getCardValue(String card) {
        String[] cardSplit = card.split(" ");
        String rank = cardSplit[0];

        if (rank.equals("Ace")) {
            return 1;
        } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack") || rank.equals("10")) {
            return 10;
        } else {
            return Integer.parseInt(rank);
        }
    }

    public void initialDeal() {
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
    }

    public void displayHands(boolean showDealerCard) {
        System.out.println("\nDealer's Hand:");
        if (showDealerCard) {
            for (String card : dealerHand) {
                System.out.println(card);
            }
        } else {
            System.out.println("Hidden card");
            System.out.println(dealerHand.get(1));
        }

        System.out.println("\nPlayer's Hand:");
        for (String card : playerHand) {
            System.out.println(card);
        }
    }

    public int calculateHandValue(List<String> hand) {
        int handValue = 0;
        int aceCount = 0;

        for (String card : hand) {
            int cardValue = getCardValue(card);
            if (cardValue == 11) {
                aceCount++;
            }
            handValue += cardValue;
        }

        while (handValue > 21 && aceCount > 0) {
            handValue -= 10;
            aceCount--;
        }

        return handValue;
    }

    public void playGame() {
        createDeck();
        initialDeal();
        displayHands(false);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int playerHandValue = calculateHandValue(playerHand);
            if (playerHandValue == 21) {
                System.out.println("Blackjack! You win!");
                break;
            } else if (playerHandValue > 21) {
                System.out.println("Busted! You lose.");
                break;
            }

            System.out.print("\nHit or Stand? (H/S): ");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("H")) {
                playerHand.add(deck.remove(0));
                displayHands(false);
            } else if (choice.equals("S")) {
                int dealerHandValue = calculateHandValue(dealerHand);
                displayHands(true);

                while (dealerHandValue < 17) {
                    dealerHand.add(deck.remove(0));
                    dealerHandValue = calculateHandValue(dealerHand);
                    displayHands(true);
                }

                if (dealerHandValue > 21) {
                    System.out.println("Dealer busted! You win!");
                } else if (dealerHandValue == playerHandValue) {
                    System.out.println("Push! It's a tie.");
                } else if (dealerHandValue > playerHandValue) {
                    System.out.println("Dealer wins!");
                } else {
                    System.out.println("You win!");
                }
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.playGame();
    }
}
