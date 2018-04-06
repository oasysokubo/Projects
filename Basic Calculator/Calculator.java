import javax.script.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{

	Calculator() {
		JOptionPane.showMessageDialog(this, "Welcome to Oasys Okubo's Calculator!");
	}
	
	public static void main(String[] args) {
		new Calculator();
		JFrame j = new JFrame("Okubo's Calculator");
		Container c = j.getContentPane();
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setLayout(new GridLayout(4,4,4,4));
		final JTextField t = new JTextField(100);
		Font myFontSize = t.getFont().deriveFont(Font.BOLD,50f);
		t.setFont(myFontSize);
		c.add(t,BorderLayout.NORTH);		
			
		final JButton  n1 =  new JButton("1");
		final JButton  n2 =  new JButton("2");
		final JButton  n3 =  new JButton("3");
		final JButton  n4 =  new JButton("4");
		final JButton  n5 =  new JButton("5");
		final JButton  n6 =  new JButton("6");
		final JButton  n7 =  new JButton("7");
		final JButton  n8 =  new JButton("8");
		final JButton  n9 =  new JButton("9");
		final JButton  n10 =  new JButton("0");
		final JButton  n11 =  new JButton("+");
		final JButton  n12 =  new JButton("-");
		final JButton  n13 =  new JButton("*");
		final JButton  n14 =  new JButton("/");
		final JButton  n15 =  new JButton("=");
		final JButton  n16 =  new JButton("C");

		n1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1 = n1.getText();
				String global = t.getText();
				global = global.concat(num1);
				t.setText(global);	
				}
		});

		n2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1 = n2.getText();
				String global = t.getText();
				global = global.concat(num1);
				t.setText(global);	
				}
		});
			  
		n3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1 = n3.getText();
				String global = t.getText();
				global = global.concat(num1);
				t.setText(global);				
				}	
		});
		       
		       n4.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n4.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);		
					}			
				});
		              
		       n5.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n5.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);	
					}	
				});
		       
		       n6.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n6.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);				
					}					
				});

		       n7.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n7.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);	
					}
				});
		        
		       n8.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n8.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);
					}
				});
		       
		       n9.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n9.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);
					}
				});
		       
		       n10.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n10.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);
					}
				});
		       
		       
		       n11.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n11.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);
					}	
				});
 
		       n12.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n12.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);	
					}	
				});
		       
		       n13.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n13.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);	
					}
				});
		       
		       n14.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String num1 = n14.getText();
						String global = t.getText();
						global = global.concat(num1);
						t.setText(global);
					}
				});
       
		       n15.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String global = t.getText();
						ScriptEngineManager mgr = new ScriptEngineManager();
					    ScriptEngine engine = mgr.getEngineByName("JavaScript");
					    try {
							String s = engine.eval(global).toString();
							t.setText(s);
						} catch (ScriptException e1) {
							e1.printStackTrace();
						}
					}
				});

		       n16.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String global = t.getText();
						global = null;
						t.setText(global);
					}
				});
		  
		       p1.add(n1);
		       p1.add(n2);
		       p1.add(n3);
		       p1.add(n4);
		       p1.add(n5);
		       p1.add(n6);
		       p1.add(n7);
		       p1.add(n8);
		       p1.add(n9);
		       p1.add(n10);
		       p1.add(n11);
		       p1.add(n12);
		       p1.add(n13);
		       p1.add(n14);
		       p1.add(n15);
		       p1.add(n16);
		       c.add(p1,BorderLayout.CENTER);
		       j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		       j.setSize(400,400);
		       j.setVisible(true);       
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {			
		}

}
