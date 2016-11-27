/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.*;
import Lexer.*;
import DataStructureElements.*;
import Utilities.ShrinkTree;
import Utilities.Simplify;

public class Parser {
    private Expression e;
    private ArrayDeque<Token> stack = new ArrayDeque<>();
    public static Expression parseString(String s){
	Lexer l = new Lexer();
	Parser p = new Parser();
	return p.parse(l.lex(s));
    }
    
    public Expression parse(ArrayList<Token> list){
        for (int i = list.size()-1; i >= 0; i--){
            stack.push(list.get(i));
        }
        
        e = gatherTerms();
        
        return e;
    }
    
    private Expression gatherTerms(){
        ArrayList<Expression> sum = new ArrayList<>();
        
        sum.add(gatherFactors());
        while (!stack.isEmpty() && (stack.peek().getSym() == TokenType.PLUSSYM || stack.peek().getSym() == TokenType.MINUSSYM)){
            if (stack.peek().getSym() == TokenType.PLUSSYM){
                stack.pop();
            }
            sum.add(gatherFactors());
        }
        if (sum.size() == 1){
            return sum.get(0);
        }
        Expression s = new Sum(sum);
        if (sum.size() == 1){
            return sum.get(0);
        }
        s = ShrinkTree.shrink(s);
        Expression simple = Simplify.simplifySum((Sum) s);
        return simple;
    }
    
    private Expression gatherFactors(){
        ArrayList<Expression> product = new ArrayList<>();        
        
        gatherer(product);
        
        while (!stack.isEmpty() && stack.peek().getSym() == TokenType.MULTSYM){
            stack.pop();
            gatherer(product);
        }        
        
        if (product.size() == 1){
            return product.get(0);
        }
        
        Expression p = new Product(product);
        p = ShrinkTree.shrink(p);
        p = Simplify.simplifyProduct((Product) p);        
        
        return p;
    }
    
    private void gatherer(ArrayList<Expression> product){
        if (stack.peek().getSym() == TokenType.MINUSSYM){
            product.add(new Constant(-1));
            stack.pop();
        }
        if (stack.peek().getSym() == TokenType.NUMBERSYM){
            double val = stack.pop().getValue();
            if (!stack.isEmpty() && stack.peek().getSym() == TokenType.POWSYM){
                stack.pop();
                if (stack.peek().getSym() == TokenType.LPARENTSYM){
                    stack.pop();
                    Expression temp = gatherTerms();
                //insert error if no rparent
                    stack.pop();
                    product.add(new Exponential(val, temp));
                }
                else {
                    //insert error if no x
                    stack.pop();
                    product.add(new Exponential(val, new Variable()));
                }
            }
            else{
                product.add(new Constant(val));
            }
        }
        else if (stack.peek().getSym() == TokenType.SINSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Sin(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Sin(new Variable()));
            }
        }
        else if (stack.peek().getSym() == TokenType.COSSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Cos(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Cos(new Variable()));
            }
        }
        else if (stack.peek().getSym() == TokenType.TANSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Tan(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Tan(new Variable()));
            }
        }
        else if (stack.peek().getSym() == TokenType.SECSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Sec(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Sec(new Variable()));
            }
        }
        else if (stack.peek().getSym() == TokenType.CSCSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Csc(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Csc(new Variable()));
            }
        }
        else if (stack.peek().getSym() == TokenType.ARCSINSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Arcsin(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Arcsin(new Variable()));
            }
        }
        else if (stack.peek().getSym() == TokenType.ARCCOTSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Arccot(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Arccot(new Variable()));
            }
        }
	else if (stack.peek().getSym() == TokenType.CSCSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Csc(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Csc(new Variable()));
            }
        } else if (stack.peek().getSym() == TokenType.ARCSECSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Arcsec(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Arcsec(new Variable()));
            }
            }
        
		else if (stack.peek().getSym() == TokenType.ARCCOSSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Arccos(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Arccos(new Variable()));
            }
        }
	else if (stack.peek().getSym() == TokenType.ARCCSCSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Arccsc(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Arccsc(new Variable()));
            }
        }
        else if (stack.peek().getSym() == TokenType.COTSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Cot(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Cot(new Variable()));
            }
        } else if (stack.peek().getSym() == TokenType.ARCTANSYM){
	    
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Arctan(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Arctan(new Variable()));
            }
            
	}
        else if (stack.peek().getSym() == TokenType.LNSYM){
            stack.pop();
 
            if (stack.peek().getSym() == TokenType.LPARENTSYM){
                stack.pop();
                Expression temp = gatherTerms();
                //insert error if no rparent
                stack.pop();
                product.add(new Ln(temp));
            }
            else {
                //insert error if no x
                stack.pop();
                product.add(new Ln(new Variable()));
            }
        }
        else if (stack.peek().getSym() == TokenType.IDENTSYM){
            stack.pop();
            if (!stack.isEmpty() && stack.peek().getSym() == TokenType.POWSYM){
                stack.pop();
                if (stack.peek().getSym() == TokenType.MINUSSYM){
                    stack.pop();
                    product.add(new Power(-stack.pop().getValue(), new Variable()));
                }
                else{
                    product.add(new Power(stack.pop().getValue(), new Variable()));
                }
                
            }
            else {
                product.add(new Variable());
            }
        }     
	
        else if (stack.peek().getSym() == TokenType.LPARENTSYM){
            stack.pop();
            Expression temp = gatherTerms();
            //insert error if no rparent
            stack.pop();
            if (!stack.isEmpty() && stack.peek().getSym() == TokenType.POWSYM){
                stack.pop();
                if (stack.peek().getSym() == TokenType.MINUSSYM){
                    stack.pop();
                    product.add(new Power(-stack.pop().getValue(), temp));
                }
                product.add(new Power(stack.pop().getValue(), temp));
            }
            else if (!stack.isEmpty() && stack.peek().getSym() == TokenType.DIVSYM){
                Expression num = temp;
                stack.pop();
                if (stack.peek().getSym() == TokenType.LPARENTSYM){
                    stack.pop();
                    Expression denom = gatherTerms();
                    //insert error if no rparent
                    stack.pop();
                    product.add(new Quotient(num, denom));
                }
                else {
                    if (stack.peek().getSym() == TokenType.NUMBERSYM){
                        product.add(new Quotient(num, new Constant(stack.pop().getValue())));
                    }
                    else if (stack.peek().getSym() == TokenType.IDENTSYM){
                        stack.pop();
                        product.add(new Quotient(num, new Variable()));
                    }
                    //insert stuff for other unary operations
                }
            }
            else {
                product.add(temp);
            }
        }
    }
}
