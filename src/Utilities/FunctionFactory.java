/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Lexer.*;
import DataStructureElements.*;

public class FunctionFactory {
    public Expression getFunction(TokenType t){
        if (t == TokenType.IDENTSYM){
            return new Variable();
        }
        else
            return null;
    }
    
    public Expression getFunction(TokenType t, Expression e){
        if (t == TokenType.ARCCOSSYM){
            return new Arccos(e);
        }
        else if (t == TokenType.ARCCOTSYM){
            return new Arccot(e);
        }
        else if (t == TokenType.ARCCSCSYM){
            return new Arccos(e);
        }
        else if (t == TokenType.ARCSECSYM){
            return new Arcsec(e);
        }
        else if (t == TokenType.ARCSINSYM){
            return new Arcsin(e);
        }
        else if (t == TokenType.ARCTANSYM){
            return new Arctan(e);
        }
        else if (t == TokenType.COSSYM){
            return new Cos(e);
        }
        else if (t == TokenType.COTSYM){
            return new Cot(e);
        }
        else if (t == TokenType.CSCSYM){
            return new Csc(e);
        }
        else if (t == TokenType.LNSYM){
            return new Ln(e);
        }
        else if (t == TokenType.LOGSYM){
            return new Log(e);
        }
        else if (t == TokenType.SECSYM){
            return new Sec(e);
        }
        else if (t == TokenType.SINSYM){
            return new Sin(e);
        }
        else if (t == TokenType.TANSYM){
            return new Tan(e);
        }
        else 
            return null;
    }
    
    public Expression getFunction(String s, Expression e, Double d){
        if (s.equalsIgnoreCase("POW")){
            return new Power(d, e);
        }
        else if (s.equalsIgnoreCase("EXP")){
            return new Exponential(d, e);
        }
        else
            return null;
    }
    
    public Expression getFunction(double d){
        return new Constant(d);
    }
}
