
package Lexer;

public abstract class Token {
    @Override
    public abstract String toString();
    
    public abstract double getValue();
    public abstract TokenType getSym();
}

