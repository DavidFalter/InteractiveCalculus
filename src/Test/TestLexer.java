/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Lexer.*;
import java.util.*;

public class TestLexer {
    public static void main(String[] args){
        ArrayList<Token> test;
        Lexer lexer = new Lexer();
        //Test Case 1:
        System.out.println("Test Case 1");
        test = lexer.lex("x^4-4*x^3+3*x^2-(x+5)^2");
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).toString());
        }
        //Test Case 2:
        System.out.println("Test Case 2");
        test = lexer.lex("sin x - cos x + x^2");
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).toString());
        }
        //Test Case 3:
        System.out.println("Test Case 3");
        test = lexer.lex("tan x / log x + csc (x-5)");
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).toString());
        }
        //Test Case 4:
        System.out.println("Test Case 4");
        test = lexer.lex("arctan - arccos x + ln (arcsin x)");
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).toString());
        }
        //Test Case 5:
        System.out.println("Test Case 5");
        test = lexer.lex("(1 - arccsc (x-5))/(arcsec x + arccot x)");
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).toString());
        }
    }
}
