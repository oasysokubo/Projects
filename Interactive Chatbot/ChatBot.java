import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;


public class ChatBot extends JFrame {
	
	// JTextField
	private JTextField textInput = new JTextField();
	
	// JTextArea 
	private JTextArea textArea = new JTextArea();
	
	// Command list and description
	private String[][] commands = {{"//clear", " - clear textfield"}, {"//quit", " - quit program"}, {"//play", " - play a game"}, {"//sleep", " - go to sleep"}, {"//time", " - find time"}, {"//math", " - do some math"}};
	
	// clear text area
	private JButton clear;
	
	// math variables
	private double X, Y;
	
	// greetings possibilities
	private String[] greeting = {"Yo!", "What's up!", "Hello", "Hey", "Good day"};
	private int greetingsRandomizer;
	
	public ChatBot() {
		// Beginning message
		JOptionPane.showMessageDialog(this, "Welcome to Oasys Okubo's Chat Bot!");
		// JFrame setting
		this.setTitle("Oasys Okubo ChatBot");
		this.setSize(600, 600);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Text aspect
		textInput.setBounds(5, 540, 590, 30);
		
		// Text actionListener
		textInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String inputText = textInput.getText();
			textArea.append("You: " + inputText + "\n");
			TextAnalysis(inputText);
			textInput.setText("");
			}
		});
		
		// JTextArea aspect
		textArea.setLocation(5, 5);
		textArea.setSize(590, 510);
		
		// Adds text aspects to JFrame
		this.add(textInput);
		this.add(textArea);
		
		clear = new JButton("Clear");
		clear.setBounds(5, 520, 590, 20);
		add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
			}
		});
	}
	
	
	public void TextAnalysis(String input) {
		if(input.contains("hi") || input.contains("yo") || input.contains("hello") || input.contains("hey")) {
			greetingsRandomizer = (int)(Math.random() * 5);
			botResponse(greeting[greetingsRandomizer]);
		}
		else if(input.contains("thank") || input.contains("appreciate")) {
			botResponse("You're Welcome!");
		}
		else if(input.contains("command") || input.contains("Command")) {
			for(int i = 0; i < commands.length; i++) {
				for(int z = 0; z < commands[i].length; z++) {
				textArea.append(commands[i][z]);
				}
				textArea.append("\n");
			}
			textArea.append("\n");
		}
		else if(input.contains("time") || input.contains("date")) {
			time();
		}
		else if(input.contains("//clear")) {
			textArea.setText(null);
		}
		else if(input.contains("//quit")) {
			System.exit(0);
		}
		else if(input.contains("//")) {
			if(input.contains("//time")) {
				time();
			}
			else if(input.contains("//math")) {
				botResponse("Type in an equation (Format: value followed by mathametical\nsymbol followed by value.");
			}
			else if(input.contains("//quit")) {
				System.exit(0);
			}
			else {
				botResponse("Command does not exist:(");
			}
		}
		
		else {
			botResponse("I didn't understand, please re-input.");
		}
	}
	
	public void time() {
		Clock c = Clock.systemUTC();  
	    Instant time = c.instant();
		botResponse(time.toString());
	}
	
	public void botResponse(String x) {
		textArea.append("Oasys Bot: " + x + "\n");
		textArea.append("\n");
	}
	
	
	public static void main(String[] args) {
		new ChatBot();
	}

}
