
package Lexer;

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
    public TokenType getSym(){
        return this.t;
    }
}