/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexer;

import Lexer.Token;
import Lexer.TokenType;

public class Number extends Token{
    private final double value;
    private final TokenType t;
    
    public Number(double content, TokenType t){
        this.value = content;
        this.t = t;
    }
    
    @Override
    public double getValue(){
        return this.value;
    }
    
    @Override
    public String toString(){
        return "\nNumber: " + this.value;
    }

    @Override
    public String getName() {
        return "\0";
    }
    
    public TokenType getSym(){
        return this.t;
    }
}
