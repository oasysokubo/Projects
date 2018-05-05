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
	
	// variables being parsed from String to integer
	private double r1;
	private double r2;
	

	// allowed numbers
	List<String> allowedStrings = Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred",
			"thousand", "million", "billion", "trillion", "+", "-", "/", "*");
	
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
			System.out.printf("%s is %f\n", speech, calculate(speech));
		}
		else if(speech.contains("multipl") || speech.contains("times")) {
			System.out.printf("%s is %f\n", speech, calculate(speech));
		}
		else if(speech.contains("add") || speech.contains("plus")) {
			System.out.printf("%s is %f\n", speech, calculate(speech));
		}
		else if(speech.contains("minus") || speech.contains("subtract")) {
			System.out.printf("%s is %f\n", speech, calculate(speech));
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
		
		if(split == null) {
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
		/*
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
		}*/
		return 0;
	}
	
	
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
	

	/*
	 * 
	 * 
	 * @param is the split speech
	 */
	public int numParse(String str) {
		
		int result = 0;
		int finalResult = 0;
		
		
		if (str.equalsIgnoreCase("zero")) {
			result += 0;
		} else if (str.equalsIgnoreCase("one")) {
			result += 1;
		} else if (str.equalsIgnoreCase("two")) {
			result += 2;
		} else if (str.equalsIgnoreCase("three")) {
			result += 3;
		} else if (str.equalsIgnoreCase("four")) {
			result += 4;
		} else if (str.equalsIgnoreCase("five")) {
			result += 5;
		} else if (str.equalsIgnoreCase("six")) {
			result += 6;
		} else if (str.equalsIgnoreCase("seven")) {
			result += 7;
		} else if (str.equalsIgnoreCase("eight")) {
			result += 8;
		} else if (str.equalsIgnoreCase("nine")) {
			result += 9;
		} else if (str.equalsIgnoreCase("ten")) {
			result += 10;
		} else if (str.equalsIgnoreCase("eleven")) {
			result += 11;
		} else if (str.equalsIgnoreCase("twelve")) {
			result += 12;
		} else if (str.equalsIgnoreCase("thirteen")) {
			result += 13;
		} else if (str.equalsIgnoreCase("fourteen")) {
			result += 14;
		} else if (str.equalsIgnoreCase("fifteen")) {
			result += 15;
		} else if (str.equalsIgnoreCase("sixteen")) {
			result += 16;
		} else if (str.equalsIgnoreCase("seventeen")) {
			result += 17;
		} else if (str.equalsIgnoreCase("eighteen")) {
			result += 18;
		} else if (str.equalsIgnoreCase("nineteen")) {
			result += 19;
		} else if (str.equalsIgnoreCase("twenty")) {
			result += 20;
		} else if (str.equalsIgnoreCase("thirty")) {
			result += 30;
		} else if (str.equalsIgnoreCase("forty")) {
			result += 40;
		} else if (str.equalsIgnoreCase("fifty")) {
			result += 50;
		} else if (str.equalsIgnoreCase("sixty")) {
			result += 60;
		} else if (str.equalsIgnoreCase("seventy")) {
			result += 70;
		} else if (str.equalsIgnoreCase("eighty")) {
			result += 80;
		} else if (str.equalsIgnoreCase("ninety")) {
			result += 90;
		} else if (str.equalsIgnoreCase("hundred")) {
			result *= 100;
		} else if (str.equalsIgnoreCase("thousand")) {
			result *= 1000;
			finalResult += result;
			result = 0;
		} else if (str.equalsIgnoreCase("million")) {
			result *= 1000000;
			finalResult += result;
			result = 0;
		} else if (str.equalsIgnoreCase("billion")) {
			result *= 1000000000;
			finalResult += result;
			result = 0;
		} else if (str.equalsIgnoreCase("trillion")) {
			result *= 1000000000000L;
			finalResult += result;
			result = 0;
		}
		
		finalResult += result;
		result = 0;
		return finalResult;
	}
	
}
