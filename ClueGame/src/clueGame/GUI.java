package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JPanel{
	private int dieRoll = 6;
	private String whosTurn = "Miss Scarlet";
	
	public GUI() {
		setLayout(new GridLayout(3,0));
		JPanel panel = createNameDisplay();
		add(panel);
		panel = createDiceDisplay();
		add(panel);
		panel = createButtonPanel();
		add(panel);
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
		frame.setSize(100,200);
		GUI gui = new GUI();
		frame.add(gui,BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
