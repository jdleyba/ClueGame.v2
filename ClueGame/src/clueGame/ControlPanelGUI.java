package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanelGUI extends JPanel{
	private int dieRoll = 6;
	private String whosTurn = "Miss Scarlet";
	ArrayList<String> guesses = new ArrayList<String>();
	
	
	public ControlPanelGUI() {
		guesses.add("Miss Scarlet Lounge Candlestick");
		setLayout(new GridLayout(2,3));
		JPanel panel = createNameDisplay();
		add(panel);
		panel = createButtonPanel();
		add(panel);
		panel = createDiceDisplay();
		add(panel);
		panel = createGuessesDisplay();
		add(panel);
	}
	private JPanel createGuessesDisplay() {
		JPanel panel = new JPanel();
		JLabel guessesLabel = new JLabel("Guesses");
		panel.add(guessesLabel);
		
		for(int i =0; i < guesses.size(); i++) {
			String guess = guesses.get(i);
			JLabel guessLabel = new JLabel(guess);
			panel.add(guessLabel);
		}
		return panel;
	}
	private JPanel createNameDisplay() {
		JPanel panel = new JPanel();
		JLabel whoLabel = new JLabel("Whose turn?");
		JLabel nameLabel = new JLabel(whosTurn);
		panel.add(whoLabel);
		panel.add(nameLabel);
		panel.setBounds(getVisibleRect());
		return panel;
	}	
	private JPanel createDiceDisplay() {
		JPanel panel = new JPanel();
		JLabel diceRoll = new JLabel("Die Roll");
		String number = Integer.toString(dieRoll);
		JLabel roll = new JLabel(number);
		panel.add(diceRoll);
		panel.add(roll);
		panel.setBounds(getVisibleRect());
		return panel;
	}	
	private JPanel createButtonPanel() {
		JButton accuse = new JButton("Make an Accusation");
		JButton next = new JButton("Next Player");
		JPanel panel = new JPanel();
		panel.add(next);
		panel.add(accuse);
		return panel;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clue Game");
		frame.setSize(300,200);
		ControlPanelGUI gui = new ControlPanelGUI();
		frame.add(gui,BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
