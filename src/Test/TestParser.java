/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DataStructureElements.*;
import Utilities.*;
import Lexer.Lexer;
import Lexer.Token;
import java.util.ArrayList;
import Parser.*;

public class TestParser {
    
    public static void main(String[] args){
        ArrayList<Token> test;
        Lexer lexer = new Lexer();
        String testString;
        //test case 1
        System.out.println("Test Case 1");
        test = lexer.lex("3*x^4*x^3*x^2");
        Parser parser = new Parser();
        Expression e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        //test case 2
        System.out.println("Test Case 2");
        test = lexer.lex("x^4 + x^3 - x^2");
        e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        //test case 3
        System.out.println("Test Case 3");
        test = lexer.lex("sin(x)");
        e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        //test case 4
        System.out.println("Test Case 4");
        test = lexer.lex("2^(x)");
        e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
    }
    
    
    public static void test(String input){
	ArrayList<Token> test;
        Lexer lexer = new Lexer();
        String testString;
        test = lexer.lex("3*x^4*x^3*x^2");
        Parser parser = new Parser();
        Expression e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
    }
}
