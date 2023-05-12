import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Lexer {

    enum State {           //State Names

        START,
        WORD,
        COMMA,
        SEMICOLON,
        IDENTIFIER,
        NUMBER,
        DECIMAL,
        SPACE,
        STRINGLITERAL,
        CHARACTERLITERAL,
        COMMENT,
        PLUS,
        MINUS,
        TIMES,
        DIVIDE,
        COMPARISON,
        GREATERTHAN,
        LESSTHAN,
        ASSIGNMENT,
        LPAREN,
        RPAREN,
        INDENT,
        DEDENT,
        DOESNOTEQUAL,
        GTE,
        LTE,
        LBRACKET,
        RBRACKET,
        COLON,
        ENDOFLINE

    }

    State currentState = State.START;       //Initializing State to 'Start'
    List<Token> lexedTokens = new ArrayList<>();    //List for tokens
    StringBuilder val = new StringBuilder();        //current/temp/value being created for token
    HashMap<String, Token.TokenType> keywords = new HashMap<>();
    public int prevIndentLevel;
    public int currentIndentLevel;




    public void lex(String exp) {

        //System.out.print("lex start");           //debugging
        keywords.put("while", Token.TokenType.WHILE);
        keywords.put("for", Token.TokenType.FOR);
        keywords.put("else", Token.TokenType.ELSE);
        keywords.put("elseif", Token.TokenType.ELSEIF);
        keywords.put("if", Token.TokenType.IF);
        keywords.put("constants", Token.TokenType.CONSTANTS);
        keywords.put("variables", Token.TokenType.VARIABLES);
        keywords.put("var", Token.TokenType.VAR);
        keywords.put("write", Token.TokenType.WRITE);
        keywords.put("define", Token.TokenType.DEFINE);
        keywords.put("repeat", Token.TokenType.REPEAT);
        keywords.put("until", Token.TokenType.UNTIL);
        keywords.put("from", Token.TokenType.FROM);
        keywords.put("to", Token.TokenType.TO);
        keywords.put("integer", Token.TokenType.INTEGER);
        keywords.put("real", Token.TokenType.REAL);
        keywords.put("boolean", Token.TokenType.BOOLEAN);
        keywords.put("array", Token.TokenType.ARRAY);
        keywords.put("of", Token.TokenType.OF);
        keywords.put("or", Token.TokenType.OR);
        keywords.put("not", Token.TokenType.NOT);
        keywords.put("and", Token.TokenType.AND);
        keywords.put("mod", Token.TokenType.MOD);
        keywords.put("then", Token.TokenType.THEN);
        keywords.put("string", Token.TokenType.STRING);
        boolean doWeHave = keywords.containsKey(val);
        Token.TokenType whileType = keywords.get("while");
        int spaceCount = 0;
        boolean insideWord = false;

        //prevIndentLevel = currentIndentLevel;
//        currentIndentLevel = 0;


//        if (currentIndentLevel < prevIndentLevel){
//            for (int i = 0; i < prevIndentLevel; i++) {
//                lexedTokens.add(new Token(Token.TokenType.DEDENT));
//            }
//        }



        for (int i = 0; i < exp.length(); i++) {        //going through each character
            //System.out.print("for" + i);      //debugging
            char current = exp.charAt(i);



            switch (currentState) {
                case START:
                    //System.out.println(" current" + current + "state" + currentState + " ");       //debugging and checking state
                    if (Character.isDigit(current)) {       //char is digit
                        val.append(current);                //add digit to val string
                        //System.out.print("append" + val);
                        //i++;
                        currentState = State.NUMBER;
                    } else if (Character.isAlphabetic(current)) {       //char is letter
                        val.append(current);                            // add to val string
                        //i++;
                        currentState = State.WORD;
                    } else if (current == ' ') {         //char is space
                        spaceCount++;
                        currentState = State.SPACE;
                        //val.append(current);
                        //i++;
                        //currentState = State.START;
                    } else if (current == '.') {         //char is period                        val.append(current);
                        val.append(current);            //add to val string
                        //i++;
                        currentState = State.DECIMAL;   //change state
                    } else if (current == '"') {
                        currentState = State.STRINGLITERAL;
                    } else if (current == '\'') {
                        currentState = State.CHARACTERLITERAL;
                    } else if (current == '{') {
                        currentState = State.COMMENT;
                    } else if (current == '<') {
                        currentState = State.LESSTHAN;
                    } else if (current == '>') {
                        currentState = State.GREATERTHAN;
                    } else if (current == ')') {
                        currentState = State.RPAREN;
                    } else if (current == '(') {
                        currentState = State.LPAREN;
                    } else if (current == '+') {
                        currentState = State.PLUS;
                    } else if (current == '-') {
                        val.append(current);
                        currentState = State.NUMBER;
                    } else if (current == '*') {
                        currentState = State.TIMES;
                    } else if (current == '/') {
                        currentState = State.DIVIDE;
                    } else if (current == '=') {
                        currentState = State.COMPARISON;
                    } else if (current == ':') {
                        currentState = State.COLON;
                    } else if (current == ',') {
                        currentState = State.COMMA;
                    } else if (current == ';') {
                        currentState = State.SEMICOLON;
                    } else if (current == '[') {
                        currentState = State.LBRACKET;
                    } else if (current == ']') {
                        currentState = State.RBRACKET;
                    }
                    break;

                case WORD:
                    if (Character.isAlphabetic(current) || Character.isDigit(current)) {     //letter or digit
                        val.append(current);        // add to val string
                        //i++;

                        currentState = State.WORD;     //change state


                    } else {
                        if (keywords.containsKey(val.toString())) {
                            Token.TokenType tokenType = keywords.get(val.toString());
                            lexedTokens.add(new Token(tokenType));
                            val.setLength(0);
                            currentState = State.START;
                        }
                        if (val.length() > 0) {
                            lexedTokens.add(new Token(Token.TokenType.WORD, val.toString()));
                            val.setLength(0);
                            currentState = State.START;
                        }
                    }
//                    else {
//                        Token.TokenType tokenType = keywords.get(val.toString());
//                        lexedTokens.add(new Token(tokenType));
//                        //val.append(current);
//                        //lexedTokens.add(new Token(Token.TokenType.WORD, val.toString()));   //Create WORD Token
//                        val.setLength(0);       //clear val string
//                        //i++;
//                        currentState = State.START;     //change state
//                    }


                    break;

                case IDENTIFIER:
                    currentState = State.IDENTIFIER;
                    break;


                case SPACE:

                    if (current == ' ') {
                        spaceCount++;
                    } else {
                        insideWord = true;
                        spaceCount = 0;
                        currentState = State.START;
                    }
                    if (spaceCount == 4) {
                            //lexedTokens.add(new Token(Token.TokenType.INDENT));
                            //spaceCount = 0;
                            currentState = State.INDENT;

                    }

//                    else if (spaceCount < 4) {
//                        int dedentCount = currentIndentLevel - (spaceCount / 4);
//                        currentIndentLevel = spaceCount / 4;
//                        for (int j = 0; j < dedentCount; j++) {
//                            lexedTokens.add(new Token(Token.TokenType.DEDENT));
//                        }
//                        currentState = State.START;
//                    }




                    break;

                case NUMBER:
                    //System.out.println(" current" + current + "state" + currentState + " ");       //debugging and checking state
                    if (!Character.isDigit(current) && current != '.'){
                        currentState = State.MINUS;
                    }
                    if (Character.isDigit(current)){        //char is digit
                        val.append(current);                //add to val string
                        //System.out.print("append" + val);
                        //i++;
                        currentState = State.NUMBER;        //change state
                    } else if (current == '.'){             // char is period
                        val.append(current);                //add to val string
                        //i++;
                        currentState = State.DECIMAL;       //change state
                    } else {
                        //val.append(current);
                        lexedTokens.add(new Token(Token.TokenType.NUMBER, val.toString()));         //new token "number"
                        val.setLength(0);               //clear val string
                        //i++;
                        currentState = State.START;    //change state
                    }
                    break;

                case DECIMAL:
                    if (Character.isDigit(current)){        //char is digit
                        val.append(current);                //add to val string
                        //i++;
                        //currentState = State.NUMBER;       //change state
                    } else {
                        //create token
                        lexedTokens.add(new Token(Token.TokenType.NUMBER, val.toString()));
                        val.setLength(0);           //clear val string
                        //i++;
                        currentState = State.START;     //change state
                    }
                    break;


                case INDENT:
                    currentIndentLevel++;
                    spaceCount = 0;
                    if (current == ' '){
                        spaceCount++;
                        lexedTokens.add(new Token(Token.TokenType.INDENT));
                        currentState = State.SPACE;
                        break;
                    }

                    if (currentIndentLevel < prevIndentLevel){
                        for (int j = 1; j < prevIndentLevel; j++) {
                            lexedTokens.add(new Token(Token.TokenType.DEDENT));
                            currentState = State.START;

                        }
                    } else {
                        lexedTokens.add(new Token(Token.TokenType.INDENT));
                    }
                    //currentState = State.START;
//                    if (current == ' '){
//                        spaceCount++;
//                        currentState = State.SPACE;
//                    }
                    if (Character.isAlphabetic(current)){
                        val.append(current);
                        currentState = State.WORD;
                        break;
                    }
                    if (Character.isDigit(current)){
                        val.append(current);
                        currentState = State.NUMBER;
                        break;
                    }
                        currentState = State.START;

                    break;



                case SEMICOLON:
                    lexedTokens.add(new Token(Token.TokenType.SEMICOLON));
                    currentState = State.START;
                    break;


                case ASSIGNMENT:
                    lexedTokens.add(new Token(Token.TokenType.ASSIGNMENT));
                    currentState = State.START;
                    break;


                case DOESNOTEQUAL:

                    break;


                case COLON:
                    if (current == '='){
                        currentState = State.ASSIGNMENT;
//                        lexedTokens.add(new Token(Token.TokenType.ASSIGNMENT));
//                        currentState = State.START;
                    } else {
                        lexedTokens.add(new Token(Token.TokenType.COLON));
                        currentState = State.START;
                    }
                    break;




                case STRINGLITERAL:
                    if (current != '"'){
                        val.append(current);
                        currentState = State.STRINGLITERAL;
                    } else if (current == '"'){
                        lexedTokens.add(new Token(Token.TokenType.STRINGLITERAL, val.toString()));
                        val.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case CHARACTERLITERAL:
                    if (current != '\''){
                        val.append(current);
                        currentState = State.CHARACTERLITERAL;
                    } else if (current == '\''){
                        lexedTokens.add(new Token(Token.TokenType.CHARACTERLITERAL, val.toString()));
                        val.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case COMMENT:
                    if(current != '}'){
                        val.appendCodePoint(current);
                        currentState = State.COMMENT;
                    } else if (current == '}'){
                        lexedTokens.add(new Token(Token.TokenType.COMMENT, val.toString()));
                        val.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case LESSTHAN:
                    if(current == '='){
                        lexedTokens.add(new Token(Token.TokenType.LTE));
                        currentState = State.START;
                    }
                    if(current == '>'){
                        lexedTokens.add(new Token(Token.TokenType.DOESNOTEQUAL));
                        currentState = State.START;
                    } else {
                        lexedTokens.add(new Token(Token.TokenType.LESSTHAN));
                        val.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case GREATERTHAN:
                    lexedTokens.add(new Token(Token.TokenType.GREATERTHAN));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case COMPARISON:
                    lexedTokens.add(new Token(Token.TokenType.COMPARISON));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case TIMES:
                    lexedTokens.add(new Token(Token.TokenType.TIMES));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case DIVIDE:
                    lexedTokens.add(new Token(Token.TokenType.DIVIDE));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case COMMA:
                    lexedTokens.add(new Token(Token.TokenType.COMMA));
                    currentState = State.START;
                    break;

                case PLUS:
                    lexedTokens.add(new Token(Token.TokenType.PLUS));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case MINUS:
                    lexedTokens.add(new Token(Token.TokenType.MINUS));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case LPAREN:
                    lexedTokens.add(new Token(Token.TokenType.LPAREN));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case RPAREN:
                    lexedTokens.add(new Token(Token.TokenType.RPAREN));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case RBRACKET:
                    lexedTokens.add(new Token(Token.TokenType.RBRACKET));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case LBRACKET:
                    lexedTokens.add(new Token(Token.TokenType.LBRACKET));
                    val.setLength(0);
                    currentState = State.START;
                    break;

                case ENDOFLINE:

                    break;
            }
            // end of switch


        }
        // end of for
//        if (currentState != State.START) {
            //System.out.print("first");        //debugging
        if (currentState == State.WORD){

            if (keywords.containsKey(val.toString())) {
                Token.TokenType tokenType = keywords.get(val.toString());
                lexedTokens.add(new Token(tokenType));
                val.setLength(0);
                currentState = State.START;
            } else  {
                lexedTokens.add(new Token(Token.TokenType.WORD, val.toString()));

            }



            //val.append(current);
            //lexedTokens.add(new Token(Token.TokenType.WORD, keywords.get(val.toString())));
            //currentState = State.START;
            //lexedTokens.add(new Token(Token.TokenType.WORD, val.toString()));
            lexedTokens.add(new Token(Token.TokenType.ENDOFLINE));
        } else if (currentState == State.NUMBER){
                lexedTokens.add(new Token(Token.TokenType.NUMBER, val.toString()));
                val.setLength(0);
        } else if (currentState == State.TIMES){
            lexedTokens.add(new Token(Token.TokenType.TIMES));
        } else if (currentState == State.DIVIDE){
            lexedTokens.add(new Token(Token.TokenType.DIVIDE));
        } else if (currentState == State.PLUS){
            lexedTokens.add(new Token(Token.TokenType.PLUS));
        } else if (currentState == State.MINUS){
            lexedTokens.add(new Token(Token.TokenType.MINUS));
        } else if (currentState == State.TIMES){
            lexedTokens.add(new Token(Token.TokenType.TIMES));
        } else if (currentState == State.INDENT){
            lexedTokens.add(new Token(Token.TokenType.INDENT));         //new token "number"
        } else if (currentState == State.IDENTIFIER){
            lexedTokens.add(new Token(Token.TokenType.WORD, val.toString()));
        } else if (currentState == State.LPAREN){
            lexedTokens.add(new Token(Token.TokenType.LPAREN));
        } else if (currentState == State.RPAREN){
            lexedTokens.add(new Token(Token.TokenType.RPAREN));
        } else if (currentState == State.SEMICOLON){
            lexedTokens.add(new Token(Token.TokenType.SEMICOLON));
        } else if (currentState == State.COLON){
            lexedTokens.add(new Token(Token.TokenType.COLON));
        } else if (currentState == State.COMMA){
            lexedTokens.add(new Token(Token.TokenType.COMMA));
        } else if (currentState == State.COMPARISON){
            lexedTokens.add(new Token(Token.TokenType.COMPARISON));
        } else if (currentState == State.RBRACKET){
            lexedTokens.add(new Token(Token.TokenType.RBRACKET));
        } else if (currentState == State.LBRACKET){
            lexedTokens.add(new Token(Token.TokenType.LBRACKET));
        } else if (currentState == State.ASSIGNMENT){
            lexedTokens.add(new Token(Token.TokenType.ASSIGNMENT));
        }

//        else if (currentState == State.STRINGLITERAL){
//            lexedTokens.add(new Token(Token.TokenType.STRINGLITERAL, val.toString()));
//        }
        lexedTokens.add(new Token(Token.TokenType.ENDOFLINE));
            //System.out.print("end");      //debugging
 //       }

        System.out.print(lexedTokens);
        //return(lexedTokens);
    }
    // end of method

}
