import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
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

	// switches to symbol "X" when even or "O" when odd
	private int opponent = 0;

	private Font font = new Font("Verdana", Font.BOLD, 50);

	// Bot components
	private int rand;
	private Timer time;

	// OptionPane
	private Object[] options = { "Restart", "Quit" };

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
		JFrame frame = new JFrame("Okubo's Tic Tac Toe");
		frame.setSize(450, 450);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TicTacToe());
		frame.setVisible(true);
	}

	// timer()
	// bot timer for real-time action
	private void timer(Timer time) {
	}

	// bot()
	// Randomized move of the opponent
	private void bot() {
		time = new Timer();
		rand = (int) (Math.random() * 9);
		if (button[rand].getText() == "") {
			button[rand].setText("O");
		} else if (checkAll() != button.length) {
			bot();
		}
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

			int option = 0;

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

				option = JOptionPane.showConfirmDialog(null, "Game Over!");
				resetButtons();
				if (option == JOptionPane.YES_OPTION)
					System.exit(0);
				
			}

			// check if all buttons are filled
			else if (checkAll() == button.length) {
				option = JOptionPane.showConfirmDialog(null, "It's a draw!");
				resetButtons();

				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}

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
