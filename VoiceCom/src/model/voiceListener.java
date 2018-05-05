package model;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class voiceListener {
	
	// Logger
	private Logger logger = Logger.getLogger(getClass().getName());

	// store speech
	private String speech;
	
	// split equation
	private String[] splitEqn;
	

	// allowed numbers
	List<String> allowedStrings = Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred",
			"thousand", "million", "billion", "trillion");
	
	String[] arithmeticEx = {"+", "-", "*", "/"};
	
	// Math expression possibilities
	String[] mathEx = { "plus","add","addition","added by","added","minus","subtract","subtract by","subtracted",
			"multiplication","multiply","multiplied by","times","division","divide","divided by","divided" };
	
	// random expressions
	String[] unknown = { "What do you mean by", "What's", "What is", "What about" };


	// Constructor
	public voiceListener(String speech) {

		this.speech = speech;

		// call to analyze speech
		commandEx();

	}

	/*
	 * commandEx() analyzes String and executes unique command
	 * 
	 * @param s takes in String from parsed speech
	 */
	public void commandEx() {
	
		
		// <math>
		// function calls to specific math operation
		if (speech.contains("divide")) {
			System.out.printf("%s is %.2f\n", speech, calculate(speech));
		}
		else if(speech.contains("multipl") || speech.contains("times")) {
			System.out.printf("%s is %.2f\n", speech, calculate(speech));
		}
		else if(speech.contains("add") || speech.contains("plus")) {
			System.out.printf("%s is %.2f\n", speech, calculate(speech));
		}
		else if(speech.contains("minus") || speech.contains("subtract")) {
			System.out.printf("%s is %.2f\n", speech, calculate(speech));
		}
		// </math>
		
		// <feelings>
		
		// </feelings>
		
		// <random>
		else if(speech.contains("test")) {
			System.out.println("There is nothing to test.");
		}
		// </random>
		
		// <command>
		else if(speech.contains("help")) {
			
		}
		else if(speech.contains("quit")) {
			System.out.println("Bye now\nGoing to sleep.");
			System.exit(0);
		}
		// </command>
		
		else {
			System.out.printf("What do you mean by \"%s\"\n", speech);
		}
		
		
	}

	
	/*
	 * @return a calculated two variable equation
	 * 
	 * @param s is the string of the speech
	 */
	public double calculate(String s) {
		
		for(int i = 0; i < mathEx.length; i++) {
			
			if(s.contains(mathEx[i])) {
				
				if(s.contains("plus") || s.contains("add")) 
					return math(split(s, mathEx[i]), "+");
				
				else if(s.contains("divide")) 
					return math(split(s, mathEx[i]), "/");
				
				else if(s.contains("multipl") || s.contains("times"))
					return math(split(s, mathEx[i]), "*");
				
				else if(s.contains("minus") || s.contains("subtract"))
					return math(split(s, mathEx[i]), "-");
			}
		}
		return 0;
	}
	
	
	/*
	 * @return array of split speech
	 * 
	 * @param s takes in speech and v is the specific arithmetic property
	 */

	public String[] split(String s, String v) {

		if (s != null && s.length() > 0) {
			splitEqn = s.split(v);
			return splitEqn;
		} else {
			System.err.println("Error: Unable to split null String");
			System.exit(1);
		}

		return null;
	}
	
	
	// ----------------
	/* 
	 * @return result of mathamatical equation
	 * 
	 * @param s is the math equation in String form
	 *
	
	public double calculateExpressions(String s) throws ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("JavaScript");
		return (double) engine.eval(s);
	}*/

	// ------------------
	
	

	/*
	 * calculates based on unique statement, only able to calculate with two variables currentlyt
	 * 
	 * @param m takes in a splitted array of the speech, v is a specific arithmetic property
	 */

	public double math(String[] split, String v) {
		
		boolean isValidInput = true;
		
		// numParse results
		double r1;
		double r2;
		
		if(split.length != 2) {
			System.err.println("Error: equation does not contain enough information");
			System.exit(1);
		}

		for (String str : split) {
			if (!allowedStrings.contains(str)) {
				isValidInput = false;
				//System.err.printf("Invalid word found: %s\n", str);
				System.err.println(split[0] + " " + split[1]);
				break;
			}
		}
		
		if (isValidInput) {
			// call getIndex see where key word occurs.
			// Analyze unique statement and prompt for correct operation
			// create the variables for the first number. Preferably a for loop which loops until and skips the getIndex value.
			// Another for loop for the second number after unique statement.
			
			r1 = numParse(split[0]);
			r2 = numParse(split[1]);
			
			if(v.equals("+")) {
				return r1 + r2;
			}
			else if(v.equals("-")) {
				return r1 - r2;
			}
			else if(v.equals("/")) {
				return r1 / r2;
			}
			else if(v.equals("*")) {
				return r1 * r2;
			}
		}
		return 0;
	}

	/*
	 * 
	 * 
	 * @param is the split speech
	 */
	public double numParse(String str) {
		
		// parsed number variables
		int result = 0;
		int finalResult = 0;
		
		if (str.contains("zero")) {
			result += 0;
		} if (str.contains("one")) {
			result += 1;
		} if (str.contains("two")) {
			result += 2;
		} if (str.contains("three")) {
			result += 3;
		} if (str.contains("four")) {
			result += 4;
		} if (str.contains("five")) {
			result += 5;
		} if (str.contains("six")) {
			result += 6;
		} if (str.contains("seven")) {
			result += 7;
		} if (str.contains("eight")) {
			result += 8;
		} if (str.contains("nine")) {
			result += 9;
		} if (str.contains("ten")) {
			result += 10;
		} if (str.contains("eleven")) {
			result += 11;
		} if (str.contains("twelve")) {
			result += 12;
		} if (str.contains("thirteen")) {
			result += 13;
		} if (str.contains("fourteen")) {
			result += 14;
		} if (str.contains("fifteen")) {
			result += 15;
		} if (str.contains("sixteen")) {
			result += 16;
		} if (str.contains("seventeen")) {
			result += 17;
		} if (str.contains("eighteen")) {
			result += 18;
		} if (str.contains("nineteen")) {
			result += 19;
		} if (str.contains("twenty")) {
			result += 20;
		} if (str.contains("thirty")) {
			result += 30;
		} if (str.contains("forty")) {
			result += 40;
		} if (str.contains("fifty")) {
			result += 50;
		} if (str.contains("sixty")) {
			result += 60;
		} if (str.contains("seventy")) {
			result += 70;
		} if (str.contains("eighty")) {
			result += 80;
		} if (str.contains("ninety")) {
			result += 90;
		} if (str.contains("hundred")) {
			result *= 100;
		} if (str.contains("thousand")) {
			result *= 1000;
			finalResult += result;
			result = 0;
		} if (str.contains("million")) {
			result *= 1000000;
			finalResult += result;
			result = 0;
		} if (str.contains("billion")) {
			result *= 1000000000;
			finalResult += result;
			result = 0;
		} if (str.contains("trillion")) {
			result *= 1000000000000L;
			finalResult += result;
			result = 0;
		}
		
		finalResult += result;
		result = 0;
		return finalResult;
	}
	
	// Getters and Setters
	
	/*
	 * @return index of where str occures in array
	 * 
	 * @param s is the split speech array and str is searched
	 */
	public int getIndex(String[] s, String str) {
		for(int i = 0; i < s.length; i++) {
			if(s[i].equals(str)) 
				return i;
		}
		return 0;
	}
	
}
