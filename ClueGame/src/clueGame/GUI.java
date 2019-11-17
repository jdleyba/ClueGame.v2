package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel{
	private int diceRoll;
	private String whosTurn;
	
	public GUI() {
		setLayout(new GridLayout(2,0));
		JPanel panel = createButtonPanel();
		add(panel);
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
		frame.setSize(500,500);
		GUI gui = new GUI();
		frame.add(gui,BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
