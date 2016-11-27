/*
 *  University of Central Florida
 *  COP 3330
 *  Author: Jonathan Gillis
 */
package Test;

import DataStructureElements.Expression;
import Lexer.Token;
import Parser.Parser;
import static Test.TestSimplify.testNum;
import Utilities.Integrate;
import Utilities.ShrinkTree;
import Utilities.Stringifier;
import java.util.ArrayList;

/**
 *
 * @author gillis
 */
public class TestIntegral {
    
    public static void main(String[] args){
	String[] test_vals = { 
//            "x",
//            "x^2 + 3*x",
//            "0.5*x*(x^2+1)^2",
//	    "cos x * sin x",
//	    "x*(x^2+1)^3",
//            "x*cos(x^2)",
//            "5*x^2*cos(x^3)",
//            "5*x^2*sin(x^3)",
//            "2^x",
            "5*(x+3)^5"
            
	};
	
	for(String s : test_vals){
	    test(s);
	}
	
    }
    static int testNum =0;
        public static void test(String s){
//	    try{
		System.out.println("\nTest Case #" + ++testNum);
		System.out.println("Input:             " + s);
		ArrayList<Token> test;
		Expression e = Parser.parseString(s);
		String testString = Stringifier.stringify(e);
		System.out.printf("Parsed Expression: %s\n", testString);
		Expression integral = ShrinkTree.shrink(Integrate.integrate(e));
		System.out.printf("Integral:          %s\n", Stringifier.stringify(integral));
//	    } catch( Throwable e){
//		System.err.println("Message: " + e.getMessage());
//		System.err.println("Cause: "+ e.getCause());
//		System.err.println(e.getStackTrace());
//	    }
    }
}
