
package Lexer;

public class Separator extends Token{
    private final char name;
    private final TokenType t;
    
    public Separator(char content, TokenType t){
       this.name = content;
       this.t = t;
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

