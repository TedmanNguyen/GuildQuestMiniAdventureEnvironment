package hw2.ui;

import java.util.Scanner;
import java.util.function.Predicate;

public class InputManager {
	
	public static final Predicate<String> stringNoWhiteSpace = s -> s.contains(" ") || s.isEmpty();
	public static final Predicate<String> stringNotEmpty = s -> s.isEmpty();
	public static final Predicate<String> stringNotContainsNorY = s -> !(s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n"));
	
	public static String getStringInput(String prompt, Predicate<String> predicate) {
		String input;
		
		Scanner inputReader = new Scanner(System.in);
		
		do {
			System.out.println(prompt);
			input = inputReader.next();
		} while (predicate.test(input));
		
		return input;
	}
	
	public static int getIndexInput(String prompt, int lowerBound, int upperBound) {
		int i;
		
		Scanner indexReader = new Scanner(System.in);
		
		do {
			System.out.println(prompt);
			i = indexReader.nextInt();
		} while (i < lowerBound || i >= upperBound);	// cannot be lower than lower bound or higher than upper bound
		
		return i;
	}
	
	public static String getLineInput(String prompt, Predicate<String> predicate) {
		String line;
		Scanner lineReader = new Scanner(System.in);
		
		do {
			System.out.println(prompt);
			line = lineReader.nextLine();
		} while (predicate.test(line));
		
		return line;
	}
		
}
