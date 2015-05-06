package net.coderodde.circuitminimizer.parser;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import net.coderodde.circuitminimizer.domain.AbstractGate;

/**
 * This class implements a parser that can build a logical circuit from a 
 * Boolean expression. The possible tokens for an expression are keywords
 * <ol>
 *  <li><b>not</b></li>
 *  <li><b>and</b></li>
 *  <li><b>or</b></li>
 * </ol>
 * with the precedence <b>not</b>, <b>and</b>, <b>or</b>, 
 * constants
 * <ul>
 *  <li><b>0</b></li>
 *  <li><b>1</b></li>
 * </ul>
 * parentheses <b>(</b> and <b>)</b> for changing default evaluation order.
 * Any other strings without whitespace will be considered to be boolean 
 * variables.
 * 
 * @author Rodion Efremov
 * @version 1.6
 */
public class BooleanExpressionParser {
   
    private static final String NOT = "not";
    private static final String AND = "and";
    private static final String OR = "or";
    
    public List<AbstractGate> parse(final String text) {
        final Deque<String> stack = new LinkedList<>();
        final Queue<String> output = new ArrayDeque<>();
        
        int index = 0;
        
        while (index < text.length()) {
            if (text.startsWith(AND, index)) {
                index = skipWhitespace(text, index + AND.length());
                
            } else if (text.startsWith(OR, index)) {
                index = skipWhitespace(text, index + OR.length());
                
            } else if (text.startsWith(NOT, index)) {
                index = skipWhitespace(text, index + NOT.length());
                
            } else if (text.charAt(index) == '(') {
                index = skipWhitespace(text, index + 1);
                
            } else if (text.charAt(index) == ')') {
                index = skipWhitespace(text, index + 1);
                
            } else if (text.charAt(index) == '0') {
                index = skipWhitespace(text, index + 1);
                output.add("0");
            } else if (text.charAt(index) == '1') {
                index = skipWhitespace(text, index + 1);
                output.add("1");
            } else {
                final String variableName = getVariableName(text, index);
                index += variableName.length();
                index = skipWhitespace(text, index);
                output.add(variableName);
            } 
        }
        
        return null;
    }
    
    private int skipWhitespace(final String text, int index) {
        final char[] chars = text.toCharArray();
        
        while (Character.isWhitespace(chars[index])) {
            ++index;
        }
        
        return index;
    }
    
    private String getVariableName(final String text, int index) {
        final StringBuilder sb = new StringBuilder(text.length());
        
        while (index < text.length()) {
            if (!Character.isWhitespace(text.charAt(index))) {
                sb.append(text.charAt(index++));
            }
        }
        
        return sb.toString();
    }
}
