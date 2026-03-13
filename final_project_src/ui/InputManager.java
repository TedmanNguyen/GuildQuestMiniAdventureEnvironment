package ui;


import java.util.Scanner;
import java.util.function.Predicate;

public class InputManager {
    
    public static final Predicate<String> stringNotEmpty = s -> s.isEmpty();
    
    public static String getLineInput(String prompt, Predicate<String> predicate) {
        String line;
        Scanner lineReader = new Scanner(System.in);
        
        do {
            System.out.println(prompt);
            line = lineReader.nextLine();
        } while (predicate.test(line));
        
        return line;
    }

    public static int getIntInput(String prompt) {
        Scanner intReader = new Scanner(System.in);
        if (!prompt.isEmpty()) System.out.println(prompt);
        while (!intReader.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            intReader.next();
        }
        int result = intReader.nextInt();
        return result;
    }
}
