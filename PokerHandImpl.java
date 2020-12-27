package cards;

public class PokerHandImpl implements PokerHand {

	// Instance fields
	private Card[] c;

	// Constructor
	public PokerHandImpl(Card[] cards) {
		if (cards == null) {
			throw new RuntimeException("bad");
		}
		if (cards.length != 5) {
			throw new RuntimeException("bad");
		}
		for (int i = 0; i < 5; i++) {
			if (cards[i] == null) {
				throw new RuntimeException("bad");
			}
		}

		c = cards.clone();
		c = sort(c);
	}

	// Returns poker hand
	public Card[] getCards() {
		return c.clone();
	}

	// Checks if poker hand contains a specified card
	public boolean contains(Card c2) {
		for (int i = 0; i < 5; i++) {
			if (c[i].equals(c2)) {
				return true;
			}
		}
		return false;
	}

	// Checks if poker hand is a flush
	public boolean isFlush() {
		for (int i = 1; i < 5; i++) {
			if (c[i].getSuit() != c[0].getSuit()) {
				return false;
			}
		}
		return true;
	}

	// Checks if poker hand is straight
	public boolean isStraight() {

		boolean nextCard = true;
		for (int i = 0; i < 4; i++) {
			if (c[i].getRank() + 1 != c[i + 1].getRank()) {
				nextCard = false;
				break;
			}
		}
		return nextCard || isTheWheel();
	}

	// Checks if poker hand is "the wheel" a special version of a straight
	private boolean isTheWheel() {
		int[] ranks = { 2, 3, 4, 5, 14 };
		for (int i = 0; i < 5; i++) {
			if (c[i].getRank() != ranks[i]) {
				return false;
			}
		}
		return true;
	}
	
	// Checks if poker hand contains one pair
	public boolean isOnePair() {

		int pairIdx = findPairStartingAt(0);

		boolean noOtherPairs = findPairStartingAt(pairIdx + 1) == -1;

		return ((pairIdx != -1) && noOtherPairs);

	}

	// Checks if poker hand contains two pairs 
	public boolean isTwoPair() {

		int firstPairIdx = findPairStartingAt(0);
		int secondPairIdx = findPairStartingAt(firstPairIdx + 2);

		return ((firstPairIdx != -1) && (secondPairIdx != -1) && !isFourOfAKind() && !isFullHouse());
	}

	// Checks if poker hand contains three of a kind
	public boolean isThreeOfAKind() {
		int firstPairIdx = findPairStartingAt(0);

		if ((firstPairIdx == -1) || (firstPairIdx == 3)) {
			return false;
		}

		return ((c[firstPairIdx].getRank() == c[firstPairIdx + 2].getRank()) && !isFourOfAKind() && !isFullHouse());
	}

	// Checks if poker hand is a full house
	public boolean isFullHouse() {
		return (((c[0].getRank() == c[1].getRank()) && (c[2].getRank() == c[3].getRank())
				&& (c[3].getRank() == c[4].getRank()))
				|| ((c[0].getRank() == c[1].getRank()) && (c[1].getRank() == c[2].getRank())
						&& (c[3].getRank() == c[4].getRank())));
	}

	// Checks if poker hand contains four of a kind
	public boolean isFourOfAKind() {
		return (((c[0].getRank() == c[1].getRank()) && (c[1].getRank() == c[2].getRank())
				&& (c[2].getRank() == c[3].getRank()))
				|| ((c[1].getRank() == c[2].getRank()) && (c[2].getRank() == c[3].getRank())
						&& (c[3].getRank() == c[4].getRank())));
	}

	// Checks if poker hand is straight flush
	public boolean isStraightFlush() {
		return isStraight() && isFlush();
	}

	// Returns hand rank based on relationship among cards
	public int getHandRank() {
		if (isOnePair() == true) {
			return c[findPairStartingAt(0)].getRank();
		} else if (isTwoPair() == true) {
			return c[3].getRank();
		} else if (isThreeOfAKind() == true || isFourOfAKind() == true || isFullHouse() == true) {
			return c[2].getRank();
		} else if (isTheWheel() == true) {
			return 5;
		} else {
			return c[4].getRank();
		}
	}

	// Compares poker hand to another poker hand based on hand value and hand rank
	public int compareTo(PokerHand other) {
		if (getHandTypeValue() < other.getHandTypeValue()) {
			return -1;
		} else if (getHandTypeValue() > other.getHandTypeValue()) {
			return 1;
		} else {
			if (getHandRank() < other.getHandRank()) {
				return -1;
			} else if (getHandRank() > other.getHandRank()) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	// Returns hand value based on relationships among cards
	public int getHandTypeValue() {

		if (isStraightFlush())
			return 9;

		if (isOnePair())
			return 2;

		if (isTwoPair())
			return 3;

		if (isThreeOfAKind())
			return 4;

		if (isStraight())
			return 5;
		if (isFlush())
			return 6;
		if (isFullHouse())
			return 7;
		if (isFourOfAKind())
			return 8;
		return 1;
	}

	// Helper method that returns the starting index of a pair
	private int findPairStartingAt(int num) {
		if (num < 0)
			num = 0;
		if (num >= 4)
			return -1;

		for (int i = num; i < 4; i++)
			if (c[i].getRank() == c[i + 1].getRank())
				return i;

		return -1;
	}

	// Helper method to sort cards in poker hand
	private Card[] sort(Card[] c) {
		for (int i = 0; i < 5; i++) {
			for (int j = i; j < 5; j++) {
				if (c[j].getRank() < c[i].getRank()) {
					Card tmp = c[i];
					c[i] = c[j];
					c[j] = tmp;
				}
			}
		}
		return c;
	}

}
