package cards;

public interface Player {
	String getName();
	int getChips();
	PokerHand getHand();
	void showCards();
	void setHand(PokerHand p);
	void placeBet(int bet);
	void winHand(int pot);
}
