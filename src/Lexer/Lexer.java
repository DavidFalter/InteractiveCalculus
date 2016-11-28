package Lexer;

import java.util.ArrayList;

public class Lexer {
    private final String separators = "+-*/^()";
    private final String[] reservedWords = {"sin", "cos", "tan", "ln", "log", 
        "csc", "sec", "cot", "arcsin", "arccos", "arctan", "arccsc", "arcsec", "arccot"};
    public ArrayList<Token> lex(String expression){
        char[] content = expression.toCharArray();
        char[] buffer = new char[expression.length()];
        String[] separated = new String[expression.length()];
        ArrayList<Token> holder = new ArrayList<>();
        int bufferCount = 0;
        int numWords = 0;
        
        for(int i = 0; i < expression.length(); i++){
            if (content[i] == ' ' | separators.indexOf(content[i]) >= 0){
                if (bufferCount > 0){
                    separated[numWords] = "";
                    for (int j = 0; j < bufferCount; j++)
                        separated[numWords] = separated[numWords] + buffer[j];
                    numWords++;
                    bufferCount = 0;
                    buffer = new char[expression.length()];
                }
                if (separators.indexOf(content[i]) >= 0){
                    separated[numWords] = String.valueOf(content[i]);
                    numWords++;
                }
            }
            else{
                buffer[bufferCount] = content[i];
                bufferCount++;
            }
        }
        
        if (bufferCount > 0){
            separated[numWords] = "";
            for (int j = 0; j < bufferCount; j++)
                        separated[numWords] = separated[numWords] + buffer[j];
            numWords++;
        }
        
        for (int i = 0; i < numWords; i++){
            if (separators.contains(separated[i])){
                int e = separators.indexOf(separated[i]);
                switch (separators.charAt(e)){
                    case '+':
                        holder.add(new Separator(separators.charAt(e), TokenType.PLUSSYM));
                        break;
                    case '-':
                        holder.add(new Separator(separators.charAt(e), TokenType.MINUSSYM));
                        break;
                    case '*':
                        holder.add(new Separator(separators.charAt(e), TokenType.MULTSYM));
                        break;
                    case '/':
                        holder.add(new Separator(separators.charAt(e), TokenType.DIVSYM));
                        break;
                    case '^':
                        holder.add(new Separator(separators.charAt(e), TokenType.POWSYM));
                        break;
                    case '(':
                        holder.add(new Separator(separators.charAt(e), TokenType.LPARENTSYM));
                        break;
                    case ')':
                        holder.add(new Separator(separators.charAt(e), TokenType.RPARENTSYM));
                        break;
                }
                
            }            
            else if (separated[i].compareToIgnoreCase("x") == 0){
                holder.add(new Identifier("x", TokenType.IDENTSYM));
            }
            else if (isNumeric(separated[i])){
                holder.add(new Number(Double.parseDouble(separated[i]), TokenType.NUMBERSYM));
            }
            else {
                for (int j = 0; j < reservedWords.length; j++){
                    if (separated[i].compareToIgnoreCase(reservedWords[j]) == 0){
                        if (j == 0)
                            holder.add(new Identifier(reservedWords[j], TokenType.SINSYM));
                        else if (j == 1)
                            holder.add(new Identifier(reservedWords[j], TokenType.COSSYM));
                        else if (j == 2)
                            holder.add(new Identifier(reservedWords[j], TokenType.TANSYM));
                        else if (j == 3)
                            holder.add(new Identifier(reservedWords[j], TokenType.LNSYM));
                        else if (j == 4)
                            holder.add(new Identifier(reservedWords[j], TokenType.LOGSYM));
                        else if (j == 5)
                            holder.add(new Identifier(reservedWords[j], TokenType.CSCSYM));
                        else if (j == 6)
                            holder.add(new Identifier(reservedWords[j], TokenType.SECSYM));
                        else if (j == 7)
                            holder.add(new Identifier(reservedWords[j], TokenType.COTSYM));
                        else if (j == 8)
                            holder.add(new Identifier(reservedWords[j], TokenType.ARCSINSYM));
                        else if (j == 9)
                            holder.add(new Identifier(reservedWords[j], TokenType.ARCCOSSYM));
                        else if (j == 10)
                            holder.add(new Identifier(reservedWords[j], TokenType.ARCTANSYM));
                        else if (j == 11)
                            holder.add(new Identifier(reservedWords[j], TokenType.ARCCSCSYM));
                        else if (j == 12)
                            holder.add(new Identifier(reservedWords[j], TokenType.ARCSECSYM));
                        else if (j == 13)
                            holder.add(new Identifier(reservedWords[j], TokenType.ARCCOTSYM));
                    }
                }
            }
        }
        return holder;
    }
    
    private static boolean isNumeric(String str){
        try{
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
