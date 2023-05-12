//package shank;
public class Token
{
    public enum TokenType{
        WORD,
        IDENTIFIER,
        NUMBER,
        COMMA,
        SEMICOLON,
        WHILE,
        IF,
        THEN,
        FOR,
        ELSE,
        ELSEIF,
        CONSTANTS,
        VARIABLES,
        VAR,
        WRITE,
        STRING,
        DEFINE,
        REPEAT,
        UNTIL,
        FROM,
        TO,
        INTEGER,
        REAL,
        BOOLEAN,
        ARRAY,
        OF,
        NOT,
        AND,
        OR,
        MOD,
        STRINGLITERAL,
        CHARACTERLITERAL,
        COMMENT,
        ASSIGNMENT,
        COMPARISON,
        DOESNOTEQUAL,
        GREATERTHAN,
        LESSTHAN,
        GTE,        //GREATE THAN OR EQUALS TO
        LTE,        //LESS THAN OR EQUALS TO
        LPAREN,
        RPAREN,
        LBRACKET,
        RBRACKET,
        PLUS,
        MINUS,
        TIMES,
        DIVIDE,
        COLON,
        INDENT,
        DEDENT,
        ENDOFLINE
    }


    private TokenType token;
    private String value;
    public Token(TokenType token){
        this.token = token;
    }
    public Token(TokenType token, String value){
        this.token = token;
        this.value = value;
    }

//public void setTokenType(TokenType newType){
//        this.token = newType;
//}
//
//public void setValue(String newValue){
//        this.value = newValue;
//}


    public TokenType getTokenType(){        //Public accessor for token
        return this.token;
    }

    public String getValue(){               //Public accessor for value
        return this.value;
    }

    @Override
    public String toString(){
        //System.out.println("token.java switch");

        switch(this.token){

            case NUMBER:
                System.out.print("NUMBER" + "(" + this.value + ")");
                break;
            case IDENTIFIER:
                System.out.println("KEYWORD(" + this.value + ")");
                break;
            case LBRACKET:
                System.out.println("LBRACKET");
                break;
            case RBRACKET:
                System.out.println("RBRACKET");
                break;
            case COMMA:
                System.out.println("COMMA");
                break;
            case SEMICOLON:
                System.out.println("SEMICOLON");
                break;
            case WORD:
                System.out.println("IDENTIFIER" +  "(" + this.value + ")");
                break;
            case STRINGLITERAL:
                System.out.println("STRINGLITERAL" +  "(" + this.value + ")");
                break;
            case CHARACTERLITERAL:
                System.out.println("CHARACTERLITERAL" +  "(" + this.value + ")");
                break;
            case WHILE:
                System.out.println("WHILE");
                break;
            case INDENT:
                System.out.println("INDENT");
                break;
            case DEDENT:
                System.out.println("DEDENT");
                break;
            case IF:
                System.out.println("IF");
                break;
            case THEN:
                System.out.println("THEN");
                break;
            case ELSEIF:
                System.out.println("ELSEIF");
                break;
            case FOR:
                System.out.println("FOR");
                break;
            case ELSE:
                System.out.println("ELSE");
                break;
            case VAR:
                System.out.println("VAR");
                break;
            case WRITE:
                System.out.println("WRITE");
                break;
            case DEFINE:
                System.out.println("DEFINE");
                break;
            case STRING:
                System.out.println("STRING");
                break;
            case VARIABLES:
                System.out.println("VARIABLES");
                break;
            case CONSTANTS:
                System.out.println("CONSTANTS");
                break;
            case REPEAT:
                System.out.println("REPEAT");
                break;
            case UNTIL:
                System.out.println("UNTIL");
                break;
            case FROM:
                System.out.println("FROM");
                break;
            case TO:
                System.out.println("TO");
                break;
            case INTEGER:
                System.out.println("INTEGER");
                break;
            case REAL:
                System.out.println("REAL");
                break;
            case ARRAY:
                System.out.println("ARRAY");
                break;
            case BOOLEAN:
                System.out.println("BOOLEAN");
                break;
            case OF:
                System.out.println("OF");
                break;
            case OR:
                System.out.println("OR");
                break;
            case AND:
                System.out.println("AND");
                break;
            case NOT:
                System.out.println("NOT");
                break;
            case MOD:
                System.out.println("MOD");
                break;
            case DOESNOTEQUAL:
                System.out.println("DOESNOTEQUAL");
                break;
            case GREATERTHAN:
                System.out.println("GREATERTHAN");
                break;
            case COMPARISON:
                System.out.println("COMPARISON");
                break;
            case ASSIGNMENT:
                System.out.println("ASSIGNMENT");
                break;
            case LESSTHAN:
                System.out.println("LESSTHAN");
                break;
            case DIVIDE:
                System.out.println("DIVIDE");
                break;
            case TIMES:
                System.out.println("TIMES");
                break;
            case MINUS:
                System.out.println("MINUS");
                break;
            case PLUS:
                System.out.println("PLUS");
                break;
            case LTE:
                System.out.println("LTE");
                break;
            case GTE:
                System.out.println("GTE");
                break;
            case LPAREN:
                System.out.println("LPAREN");
                break;
            case RPAREN:
                System.out.println("RPAREN");
                break;
            case COMMENT:
                System.out.println("COMMENT" + "(" + this.value + ")");
                break;
            case COLON:
                System.out.println("COLON");
                break;
            case ENDOFLINE:
                System.out.print("END OF LINE");

        }
        return super.toString();


        //return getTokenType() + "(" + getValue() + ")";
    }
}
