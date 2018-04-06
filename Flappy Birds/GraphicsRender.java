import javax.swing.*;
import java.awt.*;

public class GraphicsRender extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected void PaintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		FlappyBirds.flappybirds.repaint(g);
	}
	

}