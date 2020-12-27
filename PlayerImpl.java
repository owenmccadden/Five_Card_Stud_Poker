package cards;

public class PlayerImpl implements Player {
	
	private int chips;
	private PokerHand hand;
	private String name;
	
	public PlayerImpl(int chips, String name) {
		this.chips = chips;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getChips() {
		return this.chips;
	}
	
	
	public PokerHand getHand() {
		return this.hand;
	}

	@Override
	public void showCards() {
		for (int i=0; i<hand.getCards().length; i++) {
			System.out.println(hand.getCards()[i].toString());
		}
		
	}

	public void setHand(PokerHand p) {
		hand = p;
	}
	
	public void placeBet(int bet) {
		chips -= bet;
	}
	
	public void winHand(int pot) {
		chips += pot;
	}

}
