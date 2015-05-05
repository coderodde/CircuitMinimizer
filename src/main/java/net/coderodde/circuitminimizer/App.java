package net.coderodde.circuitminimizer;

import java.util.Scanner;

/**
 * This class implements a program for minimizing boolean algebra expressions.
 * 
 * @author Rodion Efremov
 * @version 1.6
 */
public class App {
    
    public static void main(final String... args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                final String expression = 
                        scanner.nextLine().trim().toLowerCase();
                
                if (expression.equals("quit")) {
                    break;
                }
                
                
            }
            
            System.out.println("Bye!");
        }
    }
}
