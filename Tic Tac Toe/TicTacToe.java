import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JPanel {

	private static final long serialVersionUID = 1L;

	// button initialization
	private JButton[] button = new JButton[9];
	
	// switches to symbol "X" when even or "O" when odd  
	private int opponent = 0;
	
	// button fonts
	private Font font = new Font("Verdana", Font.BOLD, 50);
	
	public TicTacToe() {
		JOptionPane.showMessageDialog(this, "Welcome to Oasys Okubo's Tic Tac Toe!");
		setLayout(new GridLayout(3, 3));
		setBackground(Color.black);
		setButtons();
	}
	
	public void setButtons() {
		for(int i = 0; i < 9; i++) {
			button[i] = new JButton();
			button[i].setText("");
			button[i].setFont(font);
			button[i].addActionListener(new buttonListener());
			add(button[i]);
		}
	}
	
	public void resetButtons() {
		for(int i = 0; i < 9; i++) {
			button[i].setText("");
		}	
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Okubo's Tic Tac Toe");
		frame.setSize(450, 450);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TicTacToe());
		frame.setVisible(true);
	}
	
	
	private class buttonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// gets which button is clicked
			JButton clicked = (JButton)e.getSource();
			
			// variable for which button is clicked
			int option = 0;
			
			if(opponent % 2 == 0) {
				clicked.setText("X");
			} else clicked.setText("O");
			
			if(winAnalysis() == true) {
				char[] oppSwitch = {'X', 'O'};
				if(opponent % 2 == 0) {
					option = JOptionPane.showConfirmDialog(null, "GAME OVER.\n" + oppSwitch[0] + " Won!");
				} else option = JOptionPane.showConfirmDialog(null, "GAME OVER.\n" + oppSwitch[1] + " Won!");
				resetButtons();
				if(option == JOptionPane.YES_OPTION)
					System.exit(0);
			}
			/*if(winAnalysis() == false) {
				option = JOptionPane.showConfirmDialog(null, "It's a draw.");
				if(option == JOptionPane.YES_OPTION)
					System.exit(0);
			}*/
			opponent++;
		}
		
		public boolean winAnalysis() {
			// check all possible patterns/combinations of winning with if/else statements
			
			// horizontal check
			if(winCheck(0, 1) && winCheck(1, 2))
				return true;
			else if(winCheck(3, 4) && winCheck(4, 5))
				return true;
			else if(winCheck(6, 7) && winCheck(7, 8))
				return true;
			
			// vertical check
			else if(winCheck(0, 3) && winCheck(3, 6))
				return true;
			else if(winCheck(1, 4) && winCheck(4, 7))
				return true;
			else if(winCheck(2, 5) && winCheck(5, 8))
				return true;
			
			// diagonal check
			else if(winCheck(0, 4) && winCheck(4, 8))
				return true;
			else if(winCheck(2, 4) && winCheck(4, 6))
				return true;
			else
				return false;
		}
		
		public boolean winCheck(int x, int y) {
			if(button[x].getText().equals(button[y].getText()) && !button[x].getText().equals(""))
				return true;
			else
				return false;
			
		}
	}
}

