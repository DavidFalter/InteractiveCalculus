/*
 *  University of Central Florida
 *  COP 3330
 *  Author: Jonathan Gillis
 */
package Test;

import DataStructureElements.*;
import DataStructureElements.Visitor.*;
import static DataStructureElements.Visitor.Compare.cmp;
import Parser.Parser;

/**
 *
 * @author gillis
 */
public class TestCompareExpression {
    public static void main(String[] args){
	expect(test("x+x", "x+x"), true);
	expect(test("x+2*x", "x+2*x"), true);
	expect(test("x+x*2", "x+x*2"), true);
	expect(test("2*x+x", "2*x+x"), true);
	expect(test("2*x+2*x", "2*x+2*x"), true);
	expect(test("2*x+x*3", "2*x+x*3"), true);
	expect(test("(x+1)+(x+1)^2", "(x+1)+(x+1)^2"), true);
	
	expect(test("x*x", "x*x"), true);
	expect(test("x*x^2", "x*x^2"), true);
	expect(test("x^2*x", "x^2*x"), true);
	expect(test("x^2*x^3", "x^2*x^3"), true);
	expect(test("x*(x+1)*x^2", "x*(x+1)*x^2"), true);
    }
	   
    static int testNum = 1;
    
    public static void expect(boolean got, boolean expected){
	if(got == expected){
	    System.out.println("Correct! " + (++num_good) + " so far");
	} else {
	    System.out.println("~~INCORECT!~~~ ");
	    System.out.print(++num_bad + " incorrect so far");
	}
    }
    
    
    static int num_good =0, num_bad = 0;
    
    public static boolean test(String a, String b){
	System.out.println("\nTest Case #" + testNum++);
	System.out.println("Input: (a: " + a + ") (b: " + b + ")");
        Expression e1 = Parser.parseString(a);
	Expression e2 = Parser.parseString(b);
	
        int result = Compare.cmp(e1,e2);
	System.out.printf("Result is %sequal", result ==0 ? "" : "NOT");
	return result ==0;
    }
    
    
    
}
