/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexer;

public class Identifier extends Token{
    private final String name;
    private final TokenType t;
    
    public Identifier(String content, TokenType t){
        this.name = content;
        this.t = t;
    }
    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){ 
        return "\nIdentifier: " + this.name;
    }

    @Override
    public double getValue() {
        return '\0';
    }
    
    public TokenType getSym(){
        return this.t;
    }
}