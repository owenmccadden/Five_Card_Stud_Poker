package cards;

public class CardImpl  implements Card {
	
	private static String[] strings = { null, null, "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
			"Ten", "Jack", "Queen", "King", "Ace" };

	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;

	public int rank;
	public Card.Suit suit;

	// Constructor
	public CardImpl(int rank, Card.Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	// Returns rank of card
	public int getRank() {
		return this.rank;
	}

	// Returns suit of card
	public Suit getSuit() {
		return this.suit;
	}

	// Checks if a card equals another card
	public boolean equals(Card other) {
		return (rank == other.getRank()) && (suit == other.getSuit());
	}

	// Returns suit of card as a string
	public String toString() {
		return strings[getRank()] + " of " + Card.suitToString(getSuit());
	}

}
