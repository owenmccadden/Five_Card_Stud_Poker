package cards;

public class DeckImpl implements Deck {
	
	private Card[] cardDeck;
	private int numLeftToDeal;
	
	public DeckImpl() {
		numLeftToDeal = 52;
		cardDeck = new Card[numLeftToDeal];
		int cidx = 0;
		for (Card.Suit s : Card.Suit.values()) {
			for (int rank = 2; rank <= CardImpl.ACE; rank++) {
				cardDeck[cidx] = new CardImpl(rank, s);
				cidx += 1;
			}
		}
		randomize();
	}

	@Override
	public boolean hasHand() {
		return numLeftToDeal >= 5;
	}

	@Override
	public Card dealNextCard() {
		if (numLeftToDeal == 0) {
			throw new RuntimeException();
		}
		
		numLeftToDeal --;
		return cardDeck[nextUndealtIndex()];
	}

	@Override
	public PokerHand dealHand() {
		if (hasHand() == false) {
			throw new RuntimeException("Deck does not have enough cards to deal another hand");
		}

		Card[] hand_cards = new Card[5];
		for (int i = 0; i < hand_cards.length; i++) {

			hand_cards[i] = dealNextCard();
		}
		PokerHand h = new PokerHandImpl(hand_cards);
		return h;
	}

	@Override
	public void findAndRemove(Card c) {
		if (numLeftToDeal == 0) {
			return;
		}

		for (int i = nextUndealtIndex(); i < 52; i++) {
			if (cardDeck[i].equals(c)) {
				Card tmp = cardDeck[i];
				cardDeck[i] = cardDeck[nextUndealtIndex()];
				cardDeck[nextUndealtIndex()] = tmp;
				dealNextCard();
				return;
			}
		}
		return;
	}
	
	private int nextUndealtIndex() {
		return 52 - numLeftToDeal;
	}
	
	private void randomize() {
		for (int i = 0; i < cardDeck.length; i++) {
			int swap_idx = i + ((int) (Math.random() * (cardDeck.length - i)));
			Card tmp = cardDeck[i];
			cardDeck[i] = cardDeck[swap_idx];
			cardDeck[swap_idx] = tmp;
		}
	}

}
