/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.*;
import Lexer.*;
import DataStructureElements.*;
import Utilities.FunctionFactory;
import Utilities.ShrinkTree;
import Utilities.Simplify;

public class Parser {
    private Expression e;
    private final ArrayDeque<Token> stack = new ArrayDeque<>();
    private ErrorCode errorCode = ErrorCode.NOERR;
    
    public static Expression parseString(String s){
	Lexer l = new Lexer();
	Parser p = new Parser();
	return p.parse(l.lex(s));
    }
    
    public ErrorCode getErrorCode(){
        return errorCode;
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
        Token t;
        
        sum.add(gatherFactors());
        while ((t = eat(TokenType.PLUSSYM)) != null || (t = eat(TokenType.MINUSSYM)) != null){
            if (t.getSym() == TokenType.MINUSSYM){
                stack.push(t);
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
        Expression simple = Simplify.simplify(s);
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
        
        p = Simplify.simplify(p);
        p = ShrinkTree.shrink(p);
        p = Simplify.simplify(p);        
        
        return p;
    }
    
    @SuppressWarnings("UnusedAssignment")
    private void gatherer(ArrayList<Expression> product){
        Token t;
        FunctionFactory f = new FunctionFactory();
        // if there's a negative symbol in front of a factor turn it into -1
        if ((t = eat(TokenType.MINUSSYM)) != null)
            product.add(f.getFunction(-1));
        
        
        // check if the next token is a unary function
        if (addUnaryFunction(product, TokenType.SINSYM));        
        else if (addUnaryFunction(product, TokenType.COSSYM));            
        else if (addUnaryFunction(product, TokenType.TANSYM));
        else if (addUnaryFunction(product, TokenType.SECSYM));
        else if (addUnaryFunction(product, TokenType.CSCSYM));
        else if (addUnaryFunction(product, TokenType.COTSYM));
	else if (addUnaryFunction(product, TokenType.ARCSINSYM));
	else if (addUnaryFunction(product, TokenType.ARCCOSSYM));
        else if (addUnaryFunction(product, TokenType.ARCTANSYM));
        else if (addUnaryFunction(product, TokenType.ARCSECSYM));
	else if (addUnaryFunction(product, TokenType.ARCCSCSYM));
	else if (addUnaryFunction(product, TokenType.ARCCOTSYM));               
        else if (addUnaryFunction(product, TokenType.LNSYM));
        else if (addUnaryFunction(product, TokenType.LOGSYM));
        
        
        else if ((t = eat(TokenType.NUMBERSYM)) != null){
            double val = t.getValue();
            if (eat(TokenType.POWSYM) != null){
                if (eat(TokenType.LPARENTSYM) != null){
                    Expression temp = gatherTerms();
                    if (eat(TokenType.RPARENTSYM) != null){
                        product.add(f.getFunction("EXP", temp, val));
                    }
                    else {
                        addError(product, ErrorCode.MRPARENTERR);
                    }
                }
                else if (eat(TokenType.IDENTSYM) != null){
                    product.add(f.getFunction("EXP", f.getFunction(TokenType.IDENTSYM), val));
                }
                else {
                    addError(product, ErrorCode.MLPARENTERR);
                }
            }
            else{
                product.add(f.getFunction(val));
            }
        }
        
        
        else if (eat(TokenType.IDENTSYM) != null){
            boolean isNeg;
            isNeg = false;
            
            if (eat(TokenType.POWSYM) != null){
                if (eat(TokenType.MINUSSYM) != null){
                    isNeg = true;
                }                
                if ((t = eat(TokenType.NUMBERSYM)) != null){
                    if (isNeg){
                        product.add(f.getFunction("POW", f.getFunction(TokenType.IDENTSYM), -t.getValue()));
                    }
                    else{
                        product.add(f.getFunction("POW", f.getFunction(TokenType.IDENTSYM), t.getValue()));
                    }                    
                }
                else {
                    addError(product, ErrorCode.POWFUNCERR);
                }                
            }
            else {
                product.add(f.getFunction(TokenType.IDENTSYM));
            }
        }     
	
        else if (eat(TokenType.LPARENTSYM) != null){
            Expression temp = gatherTerms();
            if (!(eat(TokenType.RPARENTSYM) != null)){
                addError(product, ErrorCode.MRPARENTERR);
            }
            if (eat(TokenType.POWSYM) != null){
                if (eat(TokenType.MINUSSYM) != null){
                    product.add(f.getFunction("POW", temp, -stack.pop().getValue()));
                }
                else {
                    product.add(f.getFunction("POW", temp, stack.pop().getValue()));
                }
            }
            else if (eat(TokenType.DIVSYM) != null){
                Expression num = temp;
                if (eat(TokenType.LPARENTSYM) != null){
                    Expression denom = gatherTerms();
                    //insert error if no rparent
                    if (eat(TokenType.RPARENTSYM) != null){
                        product.add(new Quotient(num, denom));
                    }
                    else {
                        addError(product, ErrorCode.MRPARENTERR);
                    }                    
                }
                else if ((t = eat(TokenType.NUMBERSYM)) != null){
                    product.add(new Quotient(num, f.getFunction(t.getValue())));
                }
                else if (eat(TokenType.IDENTSYM) != null){
                    product.add(new Quotient(num, new Variable()));
                }
                else {
                    addError(product, ErrorCode.MLPARENTERR);
                }
            }
            else {
                product.add(temp);
            }
        }
    }
    
    private Token eat(TokenType t){
        if (!stack.isEmpty() && stack.peek().getSym() == t){            
            return stack.pop();
        }
        else return null;
    }
    
    private boolean addUnaryFunction(ArrayList<Expression> product, TokenType t){
        FunctionFactory f = new FunctionFactory();
        
        if (eat(t) != null){
            if (eat(TokenType.LPARENTSYM) != null){
                Expression temp = gatherTerms();
                if (eat(TokenType.RPARENTSYM) != null){
                    product.add(f.getFunction(t, temp));
                    return true;
                }
                else {
                    addError(product, ErrorCode.MRPARENTERR);
                    return true;
                }
            }
            else {
                addError(product, ErrorCode.MLPARENTERR);
                return true;
            }
        }
        else {
            return false;
        }
    }
    
    
    private void addError(ArrayList<Expression> product, ErrorCode e){
        product.clear();
        product.add(new Constant(0));
        stack.clear();
        errorCode = e;
    }
}
