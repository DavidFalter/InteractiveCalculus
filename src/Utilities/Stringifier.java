/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import DataStructureElements.*;
import java.util.*;

public class Stringifier {
    public static String string;
    public static String stringify(Expression e){
        string = "";
        printExpression(e);
        return string;
    }
        
    private static void printExpression(Expression e){
        if (e instanceof Sum){
            printSum((Sum) e);
        }
        else if (e instanceof Product){
            printProduct((Product) e);
        }
        else if (e instanceof Power){
            printPower((Power) e);
        }
        else if (e instanceof Variable){
            string += "x";
        }
        else if (e instanceof Constant){
            string += ((Constant) e).getValue();
        }
        else if (e instanceof Sin){
            string += "sin(";
            printExpression(e.getExpression());
            string += ")";
        }
        else if (e instanceof Cos){
            string += "cos(";
            printExpression(e.getExpression());
            string += ")";
        }
        else if (e instanceof Tan){
            string += "tan(";
            printExpression(e.getExpression());
            string += ")";
        }
        else if (e instanceof Sec){
            string += "sec(";
            printExpression(e.getExpression());
            string += ")";
        }
        else if (e instanceof Csc){
            string += "csc(";
            printExpression(e.getExpression());
            string += ")";
        }
        else if (e instanceof Cot){
            string += "cot(";
            printExpression(e.getExpression());
            string += ")";
        }
        else if (e instanceof Exponential){
            string += ((Exponential)e).getBase();
            string += "^(";
            printExpression(e.getExpression());
            string += ")";
        }
        else if (e instanceof Quotient){
            string += "(";
            printExpression(((Quotient)e).getNumerator());
            string += ")/(";
            printExpression(((Quotient)e).getDenominator());
            string += ")";
        }
        else if (e instanceof Log){
            string += "log(";
            printExpression(e.getExpression());
            string += ")";
        }
        else if (e instanceof Ln){
            string += "ln(";
            printExpression(e.getExpression());
            string += ")";
        }
    }
    
    private static void printSum(Sum s){
        ArrayList<Expression> list = s.getList();        
        
        if (s.getList().isEmpty()) return;
        printExpression(list.get(0));
        
        for(int i = 1; i < list.size(); i++){
            string += " + ";
            printExpression(list.get(i));            
        }
    }
    
    private static void printProduct(Product p){
        ArrayList<Expression> list = p.getList();
        Sum s = new Sum(list);
        if (list.get(0).getClass() == s.getClass()){
            s = (Sum) list.get(0);
            if (s.getList().size() > 1){
                string += "(";
                printExpression(list.get(0));
                string += ")";
            }
        }
        else{
            printExpression(list.get(0));
        }
        
        for (int i = 1; i < list.size(); i++){
            string += " * ";
            if (list.get(i).getClass() == s.getClass()){
                s = (Sum) list.get(i);
                if (s.getList().size() > 1){
                    string += "(";
                    printExpression(list.get(i));
                    string += ")";
                }
            }
            else{
                printExpression(list.get(i));   
            }    
                     
        }
    }
    
    private static void printPower(Power p){
        if (p.getExpression() instanceof Variable){
            string += "x";
        }
        else {
            string += "(";
            printExpression(p.getExpression());
            string += ")";
        }
        string += "^";
        string += p.getPower();
    }
    
    
    public static String stringifyu(Expression e){
        string = "";
        printExpressionu(e);
        return string;
    }
    
    private static void printExpressionu(Expression e){
        if (e instanceof Sum){
            printSumu((Sum) e);
        }
        else if (e instanceof Product){
            printProductu((Product) e);
        }
        else if (e instanceof Power){
            printPoweru((Power) e);
        }
        else if (e instanceof Variable){
            string += "u";
        }
        else if (e instanceof Constant){
            string += ((Constant) e).getValue();
        }
        else if (e instanceof Sin){
            string += "sin(";
            printExpressionu(e.getExpression());
            string += ")";
        }
        else if (e instanceof Cos){
            string += "cos(";
            printExpressionu(e.getExpression());
            string += ")";
        }
        else if (e instanceof Tan){
            string += "tan(";
            printExpressionu(e.getExpression());
            string += ")";
        }
        else if (e instanceof Sec){
            string += "sec(";
            printExpressionu(e.getExpression());
            string += ")";
        }
        else if (e instanceof Csc){
            string += "csc(";
            printExpressionu(e.getExpression());
            string += ")";
        }
        else if (e instanceof Cot){
            string += "cot(";
            printExpressionu(e.getExpression());
            string += ")";
        }
        else if (e instanceof Exponential){
            string += ((Exponential)e).getBase();
            string += "^(";
            printExpressionu(e.getExpression());
            string += ")";
        }
        else if (e instanceof Quotient){
            string += "(";
            printExpressionu(((Quotient)e).getNumerator());
            string += ")/(";
            printExpressionu(((Quotient)e).getDenominator());
            string += ")";
        }
    }
    
    private static void printSumu(Sum s){
        ArrayList<Expression> list = s.getList();        
        
        printExpressionu(list.get(0));
        
        for(int i = 1; i < list.size(); i++){
            string += " + ";
            printExpressionu(list.get(i));            
        }
    }
    
    private static void printProductu(Product p){
        ArrayList<Expression> list = p.getList();
        Sum s = new Sum(list);
        if (list.get(0).getClass() == s.getClass()){
            s = (Sum) list.get(0);
            if (s.getList().size() > 1){
                string += "(";
                printExpressionu(list.get(0));
                string += ")";
            }
        }
        else{
            printExpressionu(list.get(0));
        }
        
        for (int i = 1; i < list.size(); i++){
            string += " * ";
            if (list.get(i).getClass() == s.getClass()){
                s = (Sum) list.get(i);
                if (s.getList().size() > 1){
                    string += "(";
                    printExpressionu(list.get(i));
                    string += ")";
                }
            }
            else{
                printExpressionu(list.get(i));   
            }    
                     
        }
    }
    
    private static void printPoweru(Power p){
        if (p.getExpression() instanceof Variable){
            string += "u";
        }
        else {
            string += "(";
            printExpressionu(p.getExpression());
            string += ")";
        }
        string += "^";
        string += p.getPower();
    }
}
