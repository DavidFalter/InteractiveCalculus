
package Lexer;

public class Identifier extends Token{
    private final TokenType t;
    private final String name;
    
    public Identifier(String content, TokenType t){
        this.name = content;
        this.t = t;
    }

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

