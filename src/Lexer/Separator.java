/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexer;

public class Separator extends Token{
    private final String name;
    private final TokenType t;
    
    public Separator(char content, TokenType t){
       char[] temp = new char[1];
       temp[0] = content;
       this.name = String.valueOf(temp);
       this.t = t;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public String toString(){
        return "\nSeparator type: " + this.name;
    }

    @Override
    public double getValue() {
        return 100;  // TODO: this is arbitrary and stupid
    }
    
    @Override
    public TokenType getSym(){
        return this.t;
    }
}