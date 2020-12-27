package cards;
import java.util.Scanner;

public class GameImpl implements Game {
	
	private PlayerImpl player;
	private PlayerImpl computer;
	private int pot;
	private Deck d;
	
	public GameImpl(int numChips) {
		d = new DeckImpl();
		player = new PlayerImpl(numChips, "Player");
		computer = new PlayerImpl(numChips, "Computer");
		pot = 0;
	}
	
	public void play() {
		while (player.getChips() > 0 && computer.getChips() > 0) {
			round();
		}
		System.out.println("");
		if (player.getChips() == 0) {
			System.out.println("Computer wins the game. :(");
		} else {
			System.out.println("You win the game!");
		}
		return;
	}
	
	public void round() {
		player.setHand(d.dealHand());
		computer.setHand(d.dealHand());
		System.out.println(player.getName() + ": " + player.getChips() + " chips");
		System.out.println(computer.getName() + ": " + computer.getChips() + " chips");
		System.out.println(" ");
		System.out.println("Your Hand:");
		System.out.println(" ");
		player.showCards();
		System.out.println(" ");
		System.out.println("Place Bet.");
		int stakes = bet();
		player.placeBet(stakes);
		computer.placeBet(stakes);
		pot += (stakes * 2);
		System.out.println(" ");
		System.out.println("Computer's Hand:");
		System.out.println(" ");
		computer.showCards();
		System.out.println(" ");
		
		if (player.getHand().compareTo(computer.getHand()) == 1) {
			System.out.println("You win!");
			player.winHand(pot);
		} else {
			System.out.println("You lose. :(");
			computer.winHand(pot);
		}
		pot = 0;
		d = new DeckImpl();
	}
	
	public int bet() {
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}
}
