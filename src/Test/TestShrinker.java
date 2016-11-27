/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DataStructureElements.Expression;
import Lexer.Lexer;
import Lexer.Token;
import Parser.Parser;
import Utilities.ShrinkTree;
import Utilities.Stringifier;
import java.util.ArrayList;

/**
 *
 * @author rthec
 */
public class TestShrinker {
    
    public static void main(String[] args){
        ArrayList<Token> test;
        Lexer lexer = new Lexer();
        String testString;
        //test case 1
        System.out.println("Test Case 1");
        test = lexer.lex("x + 2 * x");
        Parser parser = new Parser();
        Expression e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        e = ShrinkTree.shrink(e);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        
        //test case 2
        System.out.println("Test Case 2");
        test = lexer.lex("3*(3*(x+1))");
        e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        e = ShrinkTree.shrink(e);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        
        //test case 3
        System.out.println("Test Case 3");
        test = lexer.lex("4+3*((x-1)*(x+4)^2)+(x-7)");
        e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        e = ShrinkTree.shrink(e);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        
        //test case 4
        System.out.println("Test Case 4");
        test = lexer.lex("sec(2*(3*(x-4)))+(tan x - csc x)^2+(sin x - 2^x)");
        e = parser.parse(test);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
        e = ShrinkTree.shrink(e);
        testString = Stringifier.stringify(e);
        System.out.println(testString);
    }
    
}
