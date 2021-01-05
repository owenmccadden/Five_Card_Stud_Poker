package cards;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

public class CardImpl implements Card {
	
	private static String[] strings = { null, null, "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
			"Ten", "Jack", "Queen", "King", "Ace" };

	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;

	private int rank;
	private Card.Suit suit;
	private BufferedImage image;
	private BufferedImage back;

	// Constructor
	public CardImpl(int rank, Card.Suit suit) {
		this.rank = rank;
		this.suit = suit;
		try {
			if (rank == 11) {
				this.image = ImageIO.read(new File("J" + String.valueOf(suit).charAt(0) + ".png"));
			} else if (rank == 12) {
				this.image = ImageIO.read(new File("Q" + String.valueOf(suit).charAt(0) + ".png"));
			} else if (rank == 13) {
				this.image = ImageIO.read(new File("K" + String.valueOf(suit).charAt(0) + ".png"));
			} else if (rank == 14) {
				this.image = ImageIO.read(new File("A" + String.valueOf(suit).charAt(0) + ".png"));
			} else {
				this.image = ImageIO.read(new File(String.valueOf(rank) + String.valueOf(suit).charAt(0) + ".png"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			this.back = ImageIO.read(new File("red_back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Returns rank of card
	public int getRank() {
		return this.rank;
	}

	// Returns suit of card
	public Suit getSuit() {
		return this.suit;
	}
	
	public BufferedImage getFront() {
		return this.image;
	}
	
	public BufferedImage getBack() {
		return this.back;
	}

	// Checks if a card equals another card
	public boolean equals(Card other) {
		return (rank == other.getRank()) && (suit == other.getSuit());
	}

	// Returns suit of card as a string
	public String toString() {
		return strings[getRank()] + " of " + Card.suitToString(getSuit());
	}
	
	public void display() throws IOException {
		JFrame window = new JFrame("Five-Card Stud Poker");
		window.setSize(500, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = new JPanel(new BorderLayout());
		JLabel cardLabel = new JLabel(new ImageIcon(this.image));
		cardLabel.setSize(5, 5);
		content.add(cardLabel);
		window.add(content);
		
		window.setVisible(true);
	}
	
	public void displayBack() throws IOException {
		JFrame window = new JFrame("Five-Card Stud Poker");
		window.setSize(500, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = new JPanel(new BorderLayout());
		JLabel cardLabel = new JLabel(new ImageIcon(this.back));
		content.add(cardLabel);
		window.add(content);
		
		window.setVisible(true);
	}

}
