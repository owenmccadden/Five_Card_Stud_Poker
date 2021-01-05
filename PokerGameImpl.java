package cards;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;

public class PokerGameImpl implements ActionListener, PokerGame {
	
	private Deck deck;
	private PokerHand playerHand;
	private PokerHand computerHand;
	private int pot;
	private int chips;
	
	private JFrame frame;
	private JPanel compHandPanel;
	private JPanel playerHandPanel;
	
	private JPanel playerHandPanelFront;
	private JPanel compHandPanelFront1;
	private JPanel compHandPanelFront2;
	
	private JButton dealButton;
	private JButton betButton;
	private JButton foldButton;
	private JButton playButton;
	
	private JPanel buttonPanel;
	
	private JLabel yourChips;
	private JLabel totalPot;
	
	private JPanel scorePanel;
	private JTextField textField;
	
	public PokerGameImpl(int numChips) {
		frame = new JFrame();
		frame.setSize(2000, 3000);
		frame.setBackground(new Color(0x35654d));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		reset(numChips);
	}
	
	
	public void reset(int numChips) {
		deck = new DeckImpl();
		playerHand = deck.dealHand();
		computerHand = deck.dealHand();
		chips = numChips;
		pot = 0;
		
		dealButton = new JButton("Deal");
		dealButton.addActionListener(this);
		dealButton.setFocusable(false);
		dealButton.setEnabled(false);
		
		betButton = new JButton("Bet");
		betButton.addActionListener(this);
		betButton.setFocusable(false);
		
		playButton = new JButton("Double Down");
		playButton.addActionListener(this);
		playButton.setFocusable(false);
		playButton.setEnabled(false);
		
		
		foldButton = new JButton("Fold");
		foldButton.addActionListener(this);
		foldButton.setFocusable(false);
		foldButton.setEnabled(false);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 20));
		textField.setCaretColor(Color.BLACK);
		
		yourChips = new JLabel();
		yourChips.setText("Total Chips: " + "$" + chips);
		
		
		totalPot = new  JLabel();
		totalPot.setText("Current Pot Total: " + "$" + pot);
		
		
		scorePanel = new JPanel();
//		scorePanel.setLayout(new BorderLayout());
		scorePanel.setPreferredSize(new Dimension(200, 80));
		scorePanel.setBackground(new Color(255,255,255));
		scorePanel.add(yourChips, BorderLayout.NORTH);
		scorePanel.add(totalPot, BorderLayout.CENTER);
		scorePanel.add(betButton, BorderLayout.SOUTH);
		scorePanel.add(textField, BorderLayout.SOUTH);
		
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(1000, 300));
		buttonPanel.setBackground(new Color(0x35654d));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 110));
		buttonPanel.add(dealButton);
//		buttonPanel.add(betButton);
		buttonPanel.add(playButton);
		buttonPanel.add(foldButton);
		buttonPanel.add(scorePanel);
		
		compHandPanel = new JPanel();
		compHandPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		compHandPanel.setPreferredSize(new Dimension(2000, 300));
		compHandPanel.setBackground(new Color(0x35654d));
		for (int i=0; i<computerHand.getCards().length; i++) {
			compHandPanel.add(new JLabel(new ImageIcon(getScaledImage(computerHand.getCards()[i].getBack(), 164, 250))));
		}
		
		compHandPanelFront1 = new JPanel();
		compHandPanelFront1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		compHandPanelFront1.setPreferredSize(new Dimension(2000, 300));
		compHandPanelFront1.setBackground(new Color(0x35654d));
		for (int i=0; i<computerHand.getCards().length - 1; i++) {
			compHandPanelFront1.add(new JLabel(new ImageIcon(getScaledImage(computerHand.getCards()[i].getBack(), 164, 250))));
		}
		compHandPanelFront1.add(new JLabel(new ImageIcon(getScaledImage(computerHand.getCards()[4].getFront(), 164, 250))));
		
		compHandPanelFront2 = new JPanel();
		compHandPanelFront2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		compHandPanelFront2.setPreferredSize(new Dimension(2000, 300));
		compHandPanelFront2.setBackground(new Color(0x35654d));
		for (int i=0; i<computerHand.getCards().length; i++) {
			compHandPanelFront2.add(new JLabel(new ImageIcon(getScaledImage(computerHand.getCards()[i].getFront(), 164, 250))));
		}
		
		playerHandPanel = new JPanel();
		playerHandPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		playerHandPanel.setPreferredSize(new Dimension(2000, 300));
		playerHandPanel.setBackground(new Color(0x35654d));
		for (int i=0; i<playerHand.getCards().length; i++) {
			playerHandPanel.add(new JLabel(new ImageIcon(getScaledImage(playerHand.getCards()[i].getBack(), 164, 250))));
		}
		
		playerHandPanelFront = new JPanel();
		playerHandPanelFront.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		playerHandPanelFront.setPreferredSize(new Dimension(2000, 300));
		playerHandPanelFront.setBackground(new Color(0x35654d));
		for (int i=0; i<playerHand.getCards().length; i++) {
			playerHandPanelFront.add(new JLabel(new ImageIcon(getScaledImage(playerHand.getCards()[i].getFront(), 164, 250))));
		}
		
		frame.add(compHandPanel, BorderLayout.NORTH);
		frame.add(playerHandPanel, BorderLayout.SOUTH);
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == dealButton) {
			frame.remove(playerHandPanel);
			frame.add(playerHandPanelFront, BorderLayout.SOUTH);
			frame.setVisible(true);
			
			frame.remove(compHandPanel);
			frame.add(compHandPanelFront1, BorderLayout.NORTH);
			frame.setVisible(true);
			
			dealButton.setEnabled(false);
			playButton.setEnabled(true);
			foldButton.setEnabled(true);
		}
		
		if (e.getSource() == betButton) {
			int wager = Integer.valueOf(textField.getText());
			if ((wager * 2) > chips) {
				JOptionPane.showMessageDialog(null, "Wager must be less than half of total chips.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				dealButton.setEnabled(true);
				betButton.setEnabled(false);
				chips -= wager;
				pot += wager;
				yourChips.setText("Total Chips: " + "$" + chips);
				totalPot.setText("Current Pot Total: " + "$" + pot);
				textField.setEditable(false);
			}
		}
		
		if (e.getSource() == playButton) {
			frame.remove(compHandPanelFront1);
			frame.add(compHandPanelFront2, BorderLayout.NORTH);
			chips -= pot;
			pot *= 2;
			yourChips.setText("Total Chips: " + "$" + chips);
			totalPot.setText("Current Pot Total: " + "$" + pot);
			frame.setVisible(true);
			if (getWinner()) {
				chips += pot * 2;
				pot = 0;
				yourChips.setText("Total Chips: " + "$" + chips);
				totalPot.setText("Current Pot Total: " + "$" + pot);
				JOptionPane.showMessageDialog(null, "You win!", "", JOptionPane.PLAIN_MESSAGE);
				frame.getContentPane().removeAll();
				reset(chips);
			} else {
				pot = 0;
				yourChips.setText("Total Chips: " + "$" + chips);
				totalPot.setText("Current Pot Total: " + "$" + pot);
				JOptionPane.showMessageDialog(null, "You lose!", "", JOptionPane.PLAIN_MESSAGE);
				if (chips == 0) {
					JOptionPane.showMessageDialog(null, "Out of Chips ... Game Over!", "", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				} else {
					frame.getContentPane().removeAll();
					frame.repaint();
					reset(chips);
				}
			}
		}
		
		if (e.getSource() == foldButton) {
			pot = 0;
			yourChips.setText("Total Chips: " + "$" + chips);
			totalPot.setText("Current Pot Total: " + "$" + pot);
			frame.getContentPane().removeAll();
			frame.repaint();
			reset(chips);
		}
		
	}
	
	public boolean getWinner() {
		if (playerHand.compareTo(computerHand) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	private static Image getScaledImage(BufferedImage srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

}
