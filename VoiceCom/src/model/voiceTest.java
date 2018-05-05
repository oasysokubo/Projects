package model;

import javax.script.ScriptException;
import java.util.Scanner;

public class voiceTest {
	
	// voiceListener object
	private voiceListener p;
	
	// Store expression
	private String s;
	
	// User input
	private Scanner scnr;
	
	public voiceTest() {
		scnr = new Scanner(System.in);
		
		System.out.println("Enter an expression: ");
		this.s = scnr.nextLine();
		
		testCase();
	}

	public voiceTest(String s) {
		this.s = s;
		
		testCase();
	}
	
	public void testCase() {
		
		// Test of voiceListener.
		new voiceListener(s);
		System.out.println();
		if(!s.equals("0")) {
			new voiceTest();
		}
		else {
			System.out.println("Test ended");
			System.exit(0);
		}
	}
	
	public void testCase2() {
		System.out.println(p.numParse(s));
	}
	
}
