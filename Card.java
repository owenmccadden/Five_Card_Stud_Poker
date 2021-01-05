package cards;

import java.awt.image.BufferedImage;
import java.io.IOException;
public interface Card {
	public enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS
	};

	int getRank();

	Card.Suit getSuit();
	
	BufferedImage getFront();
	BufferedImage getBack();

	String toString(); 

	boolean equals(Card other);  
	
	void display() throws IOException;
	
	void displayBack() throws IOException;

	// Used in toString method to assign suit
	public static String suitToString(Card.Suit s) {
		switch (s) {
		case SPADES:
			return "Spades";
		case HEARTS:
			return "Hearts";
		case DIAMONDS:
			return "Diamonds";
		case CLUBS:
			return "Clubs";
		}
		return null;
	}
}
