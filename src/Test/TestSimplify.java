/*
 *  University of Central Florida
 *  COP 3330
 *  Author: Jonathan Gillis
 */
package Test;

import DataStructureElements.Expression;
import Lexer.Lexer;
import Lexer.Token;
import Parser.Parser;
import Utilities.Stringifier;
import java.util.ArrayList;

/**
 *
 * @author gillis
 */
public class TestSimplify {
    public static void main(String[] args){
	test("x+x");
	test("x+2*x");
	test("x+x*2");
	test("2*x+x");
	test("2*x+2*x");
	test("2*x+x*3");
	test("(x+2)+(x+2)^2");
	
	test("x*x");
	test("x*x^2");
	test("x^2*x");
	test("x^2*x^3");
	test("x*(x+1)*x^2");
        test("sin x * sin x");
        test("(sin x)^2 * sin x");
	test("sin x * cos x");
	
	test("sin x + sin x");
        test("(x-5)^2 + 3*(x-5)^2");
        test("1");
        test("1+x");
        
	
    }
    
    static int testNum = 1;
    
    public static void test(String s){
	System.out.println("\nTest Case #" + testNum++);
	System.out.println("Input: " + s);
	ArrayList<Token> test;
        Expression e = Parser.parseString(s);
        String testString = Stringifier.stringify(e);
        System.out.println(testString);
    }
    
    
}
