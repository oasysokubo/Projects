import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TicTacToe extends JPanel {

	/*
	 * ============================================================================
	 * ================================ Fields ====================================
	 * ============================================================================
	 */
	private JButton[] button = new JButton[9];

	private Font font = new Font("Verdana", Font.BOLD, 50);

	// Bot components
	private int rand;
	private int check;

	// OptionPane
	private Object[] options = { "Quit", "Restart" };

	// constructor
	public TicTacToe() {
		setLayout(new GridLayout(3, 3));
		setBackground(Color.black);
		setButtons();
	}

	/*
	 * ============================================================================
	 * ============================ private Methods ===============================
	 * ============================================================================
	 */
	// setButtons()
	private void setButtons() {
		for (int i = 0; i < 9; i++) {
			button[i] = new JButton();
			button[i].setText("");
			button[i].setFont(font);
			button[i].addActionListener(new buttonListener());
			add(button[i]);
		}
	}

	// resetButtons()
	private void resetButtons() {
		for (int i = 0; i < 9; i++) {
			button[i].setText("");
		}
	}

	// setFrame()
	private static void setFrame() {
		JFrame frame = new JFrame("Tic Tac Toe (1 Player)");
		frame.setSize(450, 450);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TicTacToe());
		frame.setVisible(true);
	}

	// timer()
	// bot timer for real-time action
	private void timer() {
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// bot()
	// Algorithm move of the opponent
	private void bot() {
		
		// called to get best action
		check = getBestMove();
		
		if (button[check].getText() == "") {
			
			timer();
			button[check].setText("O");
			
		} else {
			
			rand = (int) (Math.random() * 9);
			
			if (button[rand].getText() == "") {
				timer();
				button[rand].setText("O");
			} else if (checkAll() != button.length) {
				bot();
			}
			
		}
	}
	
	// moveAnalysis()
	// analyze of next action based on current presents
	private int getBestMove() {
		
		/*
		 * 0 | 1 | 2
		 * 3 | 4 | 5
		 * 6 | 7 | 8
		*/

		// diagonal check
		if(moveCheck(4, 8)) return 0;
		else if(moveCheck(4, 6)) return 2;
		else if(moveCheck(0, 8)) return 4;
		else if(moveCheck(2, 6)) return 4;
		else if(moveCheck(2, 4)) return 6;
		else if(moveCheck(0, 4)) return 8;
		
		
		// vertical check
		else if(moveCheck(3, 6)) return 0;
		else if(moveCheck(0, 6)) return 3;
		else if(moveCheck(0, 3)) return 6;
		
		else if(moveCheck(4, 7)) return 1;
		else if(moveCheck(1, 7)) return 4;
		else if(moveCheck(1, 4)) return 7;
		
		else if(moveCheck(5, 8)) return 2;
		else if(moveCheck(2, 8)) return 5;
		else if(moveCheck(2, 5)) return 8;
		
		
		// horizontal check
		else if(moveCheck(1, 2)) return 0;
		else if(moveCheck(0, 2)) return 1;
		else if(moveCheck(0, 1)) return 2;
		
		else if(moveCheck(4, 5)) return 3;
		else if(moveCheck(3, 5)) return 4;
		else if(moveCheck(3, 4)) return 5;
		
		else if(moveCheck(7, 8)) return 6;
		else if(moveCheck(6, 8)) return 7;
		else if(moveCheck(6, 7)) return 8;
		
		else return (int) (Math.random() * 9);
	}
	
	private boolean moveCheck(int x, int y) {
		
		if (button[x].getText().equals(button[y].getText()) && !button[x].getText().equals(""))
			return true;
		else
			return false;
		
	}

	/*
	 * ============================================================================
	 * =============================== Helpers ====================================
	 * ============================================================================
	 */
	// checkAll()
	// check if all buttons are empty
	public int checkAll() {
		int count = 0;
		for (int i = 0; i < button.length; i++) {
			if (button[i].getText() != "") {
				count++;
			}
		}

		return count;
	}

	/*
	 * ==========================================================================================================================================================
	 * ================================================================= buttonListener =========================================================================
	 * ==========================================================================================================================================================
	 */
	private class buttonListener extends JPanel implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			JButton clicked = (JButton) e.getSource();

			int optionPane = 0;

			// check if box has no action performed
			if (clicked.getText() == "") {
				
				clicked.setText("X");
				
				// AI implementation for opposing opponent
				bot();
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Do not pick a filled space");
			}

			// win combination check
			if (winAnalysis() == true) {

				optionPane = JOptionPane.showOptionDialog(null, "Game Over!", "Menu", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				
				resetButtons();
				
				if (optionPane == JOptionPane.YES_OPTION)
					System.exit(0);
				
			}

			// check if all buttons are filled
			else if (checkAll() == button.length) {
				optionPane = JOptionPane.showOptionDialog(null, "It's a draw!", "Menu", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				
				resetButtons();

				if (optionPane == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}

		// winAnalysis()
		// check for winning combination
		public boolean winAnalysis() {
			// check all possible patterns/combinations of winning

			// horizontal check
			if (winCheck(0, 1) && winCheck(1, 2))
				return true;
			else if (winCheck(3, 4) && winCheck(4, 5))
				return true;
			else if (winCheck(6, 7) && winCheck(7, 8))
				return true;

			// vertical check
			else if (winCheck(0, 3) && winCheck(3, 6))
				return true;
			else if (winCheck(1, 4) && winCheck(4, 7))
				return true;
			else if (winCheck(2, 5) && winCheck(5, 8))
				return true;

			// diagonal check
			else if (winCheck(0, 4) && winCheck(4, 8))
				return true;
			else if (winCheck(2, 4) && winCheck(4, 6))
				return true;
			else
				return false;
		}

		public boolean winCheck(int x, int y) {
			if (button[x].getText().equals(button[y].getText()) && !button[x].getText().equals(""))
				return true;
			else
				return false;
		}
	}
	/*
	 * ==========================================================================================================================================================
	 * ================================================================ /buttonListener =========================================================================
	 * ==========================================================================================================================================================
	 */

	public static void main(String[] args) {
		setFrame();
		new TicTacToe();
	}
}
