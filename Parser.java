import java.util.ArrayList;
import java.util.List;

public class Parser {

    private ArrayList<Token> tList;
    ProgramNode program = new ProgramNode();


    public Parser(ArrayList<Token> tokens){
        this.tList = tokens;
        //this.lex = tokenList;

    }


    public Node parse(){
        GetRandom getRandom = new GetRandom(null);
        IntegerToReal integerToReal = new IntegerToReal(null, null);
        RealToInteger realToInteger = new RealToInteger(null, null);
        SquareRoot squareRoot = new SquareRoot(null);
        Left left = new Left(null);
        Right right = new Right(null);
        Substring substring = new Substring(null);
        Read read = new Read(null);
        Write write = new Write(null);
        Start start = new Start(null);
        End end = new End(null);
        program.addFunction(read);
        program.addFunction(write);
        program.addFunction(start);
        program.addFunction(end);
        program.addFunction(substring);
        program.addFunction(right);
        program.addFunction(left);
        program.addFunction(squareRoot);
        program.addFunction(realToInteger);
        program.addFunction(getRandom);
        program.addFunction(integerToReal);



//        while (!tList.isEmpty()){
//            GetRandom getRandom = new GetRandom();
//            FunctionNode function = function();
//            program.addFunction(function);
//
//        }

        //variableDecl();
        //functionCall();
        parseWhile();
        parseIf();
        parseFor();
        functionCall();
        variableDecl();
        variableDecl();
        variableDecl();





        System.out.println("parser");
            //expression();
            //function();
            //booleanCompare();

//        matchAndRemove(Token.TokenType.ENDOFLINE);

//        while (expression() != null && expectEndOfLine()) {
//            // continue parsing expressions and end of lines
//        }

        return null;
    }

    public ProgramNode getProgramNode(){
        System.out.println(program);
        return program;
    }

//    public boolean lookupFunctionNode(FunctionNode function){}

//    private void expectEndOfLine() {
//        // Match and remove one or more ENDOFLINE tokens
//        while (matchAndRemove(Token.TokenType.ENDOFLINE)) {
//            // do nothing
//        }
//
//        // Check if there are any remaining tokens
//        if (!tList.isEmpty()) {
//            throw new SyntaxErrorException("Expected end of input, but found additional tokens");
//        }
//    }

    //If the next token in line is of a specific token type that we are looking for, remove it from the list and return it, otherwise return null.

    private Token matchAndRemove(Token.TokenType... types){
        //get first item in list
        Token tokenObj = tList.get(0);

        //check if item type matches param type
        // if matches, remove token from list and return it
        for(Token.TokenType type : types) {
            if (tokenObj.getTokenType() == type) {
                tList.remove(0);
                return tokenObj;
            } else {
                return null;
            }
        }

        //if doesn't match, return null
        return null;
    }

    private Token match(Token.TokenType... types){
        //get first item in list
        Token tokenObj = tList.get(0);

        //check if item type matches param type
        // if matches, remove token from list and return it
        for(Token.TokenType type : types) {
            if (tokenObj.getTokenType() == type) {
                return tokenObj;
            } else {
                return null;
            }
        }

        //if doesn't match, return null
        return null;
    }

    private WhileNode parseWhile(){
        matchAndRemove(Token.TokenType.WHILE);
        Node condition = booleanCompare();
        matchAndRemove(Token.TokenType.ENDOFLINE);
        matchAndRemove(Token.TokenType.INDENT);
        List<StatementNode> statements = statements();

        WhileNode whileNode = new WhileNode(condition, statements);
        System.out.println(whileNode);
        return whileNode;
    }

    private ForNode parseFor(){
        matchAndRemove(Token.TokenType.FOR);
        Token tObj = matchAndRemove(Token.TokenType.WORD);
        String varName = tObj.getValue();
        VariableReferenceNode variable = new VariableReferenceNode(varName, null);
        matchAndRemove(Token.TokenType.FROM);
        Node from = expression();
        matchAndRemove(Token.TokenType.TO);
        Node to = expression();
        matchAndRemove(Token.TokenType.ENDOFLINE);
        List<StatementNode> statements = statements();


        ForNode forNode = new ForNode(variable, from, to, statements);
        System.out.println(forNode);
        return forNode;

    }

    private IfNode parseIf(){
        matchAndRemove(Token.TokenType.IF);
        Node condition = booleanCompare();
        matchAndRemove(Token.TokenType.THEN);
        matchAndRemove(Token.TokenType.ENDOFLINE);
        matchAndRemove(Token.TokenType.ENDOFLINE);
        matchAndRemove(Token.TokenType.INDENT);
        List<StatementNode> statements = statements();


        IfNode ifNode = new IfNode(condition, statements);

        // Parse any 'elseif' clauses
        IfNode currentIfNode = ifNode;
        while (match(Token.TokenType.ELSEIF) != null) {
            matchAndRemove(Token.TokenType.ELSEIF);
            Node elseifCondition = booleanCompare();
            matchAndRemove(Token.TokenType.THEN);
            matchAndRemove(Token.TokenType.ENDOFLINE);
            matchAndRemove(Token.TokenType.ENDOFLINE);
            matchAndRemove(Token.TokenType.INDENT);
            List<StatementNode> elseifStatements = statements();

            IfNode elseifNode = new IfNode(elseifCondition, elseifStatements);
            currentIfNode.setElseIfNode(elseifNode);
            currentIfNode = elseifNode;
        }

        // Parse the 'else' clause, if present
        if (match(Token.TokenType.ELSE) != null) {
            matchAndRemove(Token.TokenType.ELSE);
            matchAndRemove(Token.TokenType.ENDOFLINE);
            matchAndRemove(Token.TokenType.ENDOFLINE);
            matchAndRemove(Token.TokenType.INDENT);
            List<StatementNode> elseStatements = statements();

            currentIfNode.setElseNode(new ElseNode(null, elseStatements));
        }


        System.out.println(ifNode);
        return ifNode;

    }

    public Token peek(int n) {
        if (n >= tList.size()) {
            return null;
        }
        return tList.get(n);
    }

    private FunctionNode function() {
        matchAndRemove(Token.TokenType.DEFINE);
        Token name = matchAndRemove(Token.TokenType.WORD);
        matchAndRemove(Token.TokenType.LPAREN);
        List<VariableNode> parameters = parameterDeclarations();
        matchAndRemove(Token.TokenType.RPAREN);
        matchAndRemove(Token.TokenType.ENDOFLINE);
        List<VariableNode> constants = new ArrayList<>();
        List<VariableNode> variables = new ArrayList<>();
        //List<StatementNode> statements = statements();
        boolean moreDeclarations = true;

        while(moreDeclarations && !tList.isEmpty()) {
            //expression();
            Token tObj = match(Token.TokenType.CONSTANTS);
            if (tObj != null) {
                constants.add(constantDecl());
            } else {
                tObj = match(Token.TokenType.VARIABLES);
                if(tObj != null){
                    variables.add(variableDecl());
                } else {
                    moreDeclarations = false;
                    }
            }

        }

        //List<StatementNode> statements = statements();

        FunctionNode function = new FunctionNode(name.getValue(), parameters, constants, variables, null);

        System.out.println(function.toString());
        return function;
    }


    //functionCll() parses function calls
    private FunctionCallNode functionCall(){
        Token tObj = matchAndRemove(Token.TokenType.WORD);
        String name = tObj.getValue();
        tObj = matchAndRemove(Token.TokenType.ENDOFLINE);
        if (tObj != null){
            FunctionCallNode functionCallNode = new FunctionCallNode(name);
            System.out.println(functionCallNode);
            return functionCallNode;
        } else {

            //parse parameters
            List<ParameterNode> params = new ArrayList<>();
            boolean moreParams = true;

            while(moreParams){
                tObj = match(Token.TokenType.NUMBER);
                if (tObj != null){
                Node exp = expression();
                ParameterNode param = new ParameterNode(null, exp);
                params.add(param);
                }

                tObj = matchAndRemove(Token.TokenType.VAR);
                if (tObj != null){
                    Token varNameToken = matchAndRemove(Token.TokenType.WORD);
                    String varName = varNameToken.toString();
                    VariableReferenceNode varRef = new VariableReferenceNode(varName);
                    ParameterNode varParam = new ParameterNode(varRef, null);
                    params.add(varParam);
                    matchAndRemove(Token.TokenType.ENDOFLINE);
                    matchAndRemove(Token.TokenType.ENDOFLINE);

                }
                //checks for more parameters
                if(matchAndRemove(Token.TokenType.COMMA) == null){
                    moreParams = false;
                }
            }

            FunctionCallNode functionCallNode = new FunctionCallNode(name, params);
            System.out.println(functionCallNode);
            return functionCallNode;

        }
        //VariableReferenceNode varRef = new VariableReferenceNode(name,null);
        //List<ParameterNode> parameters =
    }




    private VariableNode constantDecl(){
        //List<VariableNode> consts = new ArrayList<>();
        //boolean moreConsts = true;

            matchAndRemove(Token.TokenType.CONSTANTS);
            Token name = matchAndRemove(Token.TokenType.WORD);
            matchAndRemove(Token.TokenType.COMPARISON);
            Token value = matchAndRemove(Token.TokenType.NUMBER);
            IntegerNode intNode = new IntegerNode(Integer.parseInt(value.getValue()));
            matchAndRemove(Token.TokenType.ENDOFLINE);
            String type =  "DEFINITION";
            String valType = "INTEGER";
            VariableNode constNode = new VariableNode(VariableNode.VarNodeType.DEFINITION, name.getValue(), valType, intNode, false, null, null);
            //consts.add(constNode);
            return constNode;

        //return constNode;

    }

    private VariableNode variableDecl(){

        matchAndRemove(Token.TokenType.VARIABLES);
        Token name = matchAndRemove(Token.TokenType.WORD);
        matchAndRemove(Token.TokenType.COLON);

        Token type = matchAndRemove(Token.TokenType.INTEGER);

        if (type != null){
             type = matchAndRemove(Token.TokenType.FROM);
             if (type != null){
                 Token from = matchAndRemove(Token.TokenType.NUMBER);
                 IntegerNode fromNode = new IntegerNode(Integer.parseInt(from.getValue()));
                 matchAndRemove(Token.TokenType.TO);
                 Token to = matchAndRemove(Token.TokenType.NUMBER);
                 IntegerNode toNode = new IntegerNode(Integer.parseInt(to.getValue()));
                 matchAndRemove(Token.TokenType.ENDOFLINE);
                 String paramType = "INTEGER";
                 VariableNode varNode = new VariableNode(VariableNode.VarNodeType.RANGE,name.getValue(), paramType, null, true, fromNode, toNode);
                 System.out.println(varNode);
                 return varNode;


             } else {
                 //IntegerNode typeNode = new IntegerNode(type);
                 matchAndRemove(Token.TokenType.ENDOFLINE);
                 String paramType = "INTEGER";
                 VariableNode varNode = new VariableNode(VariableNode.VarNodeType.DEFINITION, name.getValue(), paramType);
                 System.out.println(varNode);
                 return varNode;
             }

        }

        type = matchAndRemove(Token.TokenType.STRING);

        if(type != null){
            type = matchAndRemove(Token.TokenType.FROM);
            if (type != null){
                Token from = matchAndRemove(Token.TokenType.NUMBER);
                IntegerNode fromNode = new IntegerNode(Integer.parseInt(from.getValue()));
                matchAndRemove(Token.TokenType.TO);
                Token to = matchAndRemove(Token.TokenType.NUMBER);
                IntegerNode toNode = new IntegerNode(Integer.parseInt(to.getValue()));
                matchAndRemove(Token.TokenType.ENDOFLINE);
                String paramType = "STRING";
                VariableNode varNode = new VariableNode(VariableNode.VarNodeType.RANGE,name.getValue(), paramType, null, true, fromNode, toNode);
                System.out.println(varNode);
                return varNode;

            }else {
                //IntegerNode typeNode = new IntegerNode(type);
                matchAndRemove(Token.TokenType.ENDOFLINE);
                String paramType = "STRING";
                VariableNode varNode = new VariableNode(VariableNode.VarNodeType.DEFINITION, name.getValue(), paramType);
                return varNode;
            }
        }

        type = matchAndRemove(Token.TokenType.REAL);

        if (type != null){
            //IntegerNode typeNode = new IntegerNode(type);
            type = matchAndRemove(Token.TokenType.FROM);
            if (type != null){
                Token from = matchAndRemove(Token.TokenType.NUMBER);
                RealNode fromNode = new RealNode(Float.parseFloat(from.getValue()));
                matchAndRemove(Token.TokenType.TO);
                Token to = matchAndRemove(Token.TokenType.NUMBER);
                RealNode toNode = new RealNode(Float.parseFloat(to.getValue()));
                matchAndRemove(Token.TokenType.ENDOFLINE);
                String paramType = "REAL";
                VariableNode varNode = new VariableNode(VariableNode.VarNodeType.RANGE,name.getValue(), paramType, null, true, fromNode, toNode);
                System.out.println(varNode);
                return varNode;

            }else {
                //IntegerNode typeNode = new IntegerNode(type);
                matchAndRemove(Token.TokenType.ENDOFLINE);
                String paramType = "REAL";
                VariableNode varNode = new VariableNode(VariableNode.VarNodeType.DEFINITION, name.getValue(), paramType);
                return varNode;
            }
        }

        type = matchAndRemove(Token.TokenType.BOOLEAN);

        if (type != null){
            //IntegerNode typeNode = new IntegerNode(type);
            matchAndRemove(Token.TokenType.ENDOFLINE);
            String paramType = "BOOLEAN";
            VariableNode varNode = new VariableNode(VariableNode.VarNodeType.DEFINITION,name.getValue(), paramType);
            return varNode;

        }

        return null;
    }

    private List<VariableNode> parameterDeclarations(){
        List<VariableNode> params = new ArrayList<>();
        boolean moreParams = true;

        while(moreParams){
            //Token varOrConst = peek(1);

//            if (varOrConst != null && varOrConst.getTokenType() == Token.TokenType.VARIABLES) {
//                matchAndRemove(Token.TokenType.VARIABLES);

                Token identifier = matchAndRemove(Token.TokenType.WORD);
                if (identifier == null){ return null; }     //zero parameters
                matchAndRemove(Token.TokenType.COLON);


                Token type = matchAndRemove(Token.TokenType.INTEGER);

                if (type != null){
                    //IntegerNode typeNode = new IntegerNode(type);
                    String paramType = "INTEGER";
                    VariableNode varNode = new VariableNode(VariableNode.VarNodeType.PARAMETER,identifier.getValue(), paramType);
                    params.add(varNode);
                    if (matchAndRemove(Token.TokenType.SEMICOLON) == null){
                        moreParams = false;
                    }


                }

                type = matchAndRemove(Token.TokenType.REAL);

                if (type != null){
                    //IntegerNode typeNode = new IntegerNode(type);
                    String paramType = "REAL";
                    VariableNode varNode = new VariableNode(VariableNode.VarNodeType.PARAMETER,identifier.getValue(), paramType);
                    params.add(varNode);
                    if (matchAndRemove(Token.TokenType.SEMICOLON) == null){
                        moreParams = false;
                    }

                }

                type = matchAndRemove(Token.TokenType.BOOLEAN);

                if (type != null){
                    //IntegerNode typeNode = new IntegerNode(type);
                    String paramType = "BOOLEAN";
                    VariableNode varNode = new VariableNode(VariableNode.VarNodeType.PARAMETER,identifier.getValue(), paramType);
                    params.add(varNode);
                    if (matchAndRemove(Token.TokenType.SEMICOLON) == null){
                        moreParams = false;
                    }

                }

        }

        return params;
    }

    private List<StatementNode> statements() {
        List<StatementNode> statementNodes = new ArrayList<>();
        matchAndRemove(Token.TokenType.INDENT);

        while (true) {
            StatementNode statementNode = statement();
            Token tObj = matchAndRemove(Token.TokenType.ENDOFLINE);
            if (tObj != null){
                break;
            }
            if (statementNode == null) {
                break;
            }
            statementNodes.add(statementNode);
        }

        //matchAndRemove(Token.TokenType.DEDENT);
        //System.out.println(statementNodes);
        return statementNodes;
    }

    private StatementNode statement() {
        // Call assignment function to parse assignment statement
        StatementNode assignmentStatement = assignment();
        return assignmentStatement;
    }

//    public Token expect(Token.TokenType expectedTokenType) {
//        Token token = tList.peek(1);
//        if (token.getTokenType() != expectedTokenType) {
//            throw new RuntimeException("Expected " + expectedTokenType + ", but found " + token.getTokenType() + " instead.");
//        }
//        token.remove();
//        return token;
//    }


//    Factor will return a FloatNode (NUMBER with a decimal point) or an IntegerNode (NUMBER without a decimal point) OR the return value from the EXPRESSION.
//    Use matchAndRemove to test for the presence of a token

    private Node factor() {
        //System.out.println("factor");

        Token tObj = matchAndRemove(Token.TokenType.NUMBER);
        if (tObj != null) {
            int value = Integer.parseInt(tObj.getValue());
            Node node = new IntegerNode(value);
            System.out.println(node.toString());
            return node;
            //return new IntegerNode(tObj);
        } else if (matchAndRemove(Token.TokenType.LPAREN) != null) {
            Node node = expression();
            if (matchAndRemove(Token.TokenType.RPAREN) != null) {
                return node;
            } else {
                //missing RPAREN
                return null;
            }

        }
            tObj = matchAndRemove(Token.TokenType.WORD);
            if (tObj != null) {
            // Parse a variable reference and return a VariableReferenceNode
            String varName = tObj.getValue();
            Node arrayIndexExpression = null;
            if (matchAndRemove(Token.TokenType.LBRACKET) != null) {
                arrayIndexExpression = expression();
                if (matchAndRemove(Token.TokenType.RBRACKET) == null) {
                    // Missing RBRACKET
                    return null;
                }
            }
            return new VariableReferenceNode(varName, arrayIndexExpression);
        }

    else {
        //unexpected token type
        return null;
    }

//    if (tObj == null){
//        tObj = matchAndRemove(Token.TokenType.LPAREN);
//    } else {
//        return new IntegerNode(tObj);
//    }
//
//    if (tObj == null){
//        //throw exception?
//    } else {
//        Node expression = expression();
//
//        Token close = matchAndRemove(Token.TokenType.RPAREN);
//
//        if (close == null) {
//            //throw exception "expecting closing parenthesis RPAREN
//        }
//        return expression;
//
//    }

    }

    private Node term(){
        //System.out.println("term");
        //call factor
        Node factor1 = factor();
        //matchAndRemove * or /, if not return term 1
        while (true) {
            Token tObj = matchAndRemove(Token.TokenType.TIMES, Token.TokenType.DIVIDE);
            if(tObj == null){
                break;
            }

            Node factor2 = factor();
            MathOpNode.MathOp mathOp;
            if (tObj.getTokenType() == Token.TokenType.TIMES) {
                mathOp = MathOpNode.MathOp.TIMES;
            } else {
                mathOp = MathOpNode.MathOp.DIVIDE;
            }
            factor1 = new MathOpNode(mathOp, factor1, factor2);
            System.out.println(factor1.toString());

        }
        return factor1;
    }

    private Node booleanCompare(){

        Node left = expression();
        while (true) {
            Token tObj = matchAndRemove(Token.TokenType.GREATERTHAN, Token.TokenType.LESSTHAN, Token.TokenType.GTE, Token.TokenType.LTE, Token.TokenType.COMPARISON, Token.TokenType.DOESNOTEQUAL);
            if(tObj == null){
                break;
            }

            Node right = expression();
            BooleanCompareNode.BooleanComp booleanComp;
//            if (tObj.getTokenType() == Token.TokenType.GREATERTHAN){
//                booleanComp  = BooleanCompareNode.BooleanComp.GREATERTHAN;
//            } else if (tObj.getTokenType() == Token.TokenType.LESSTHAN) {
//                booleanComp = BooleanCompareNode.BooleanComp.LESSTHAN;
//            } else if (tObj.getTokenType() == Token.TokenType.GTE) {
//                booleanComp = BooleanCompareNode.BooleanComp.GTE;
//            } else if (tObj.getTokenType() == Token.TokenType.LTE) {
//                booleanComp = BooleanCompareNode.BooleanComp.LTE;
//            } else if (tObj.getTokenType() == Token.TokenType.COMPARISON) {
//                booleanComp = BooleanCompareNode.BooleanComp.EQUALS;
//            } else if (tObj.getTokenType() == Token.TokenType.DOESNOTEQUAL) {
//                booleanComp = BooleanCompareNode.BooleanComp.DOESNOTEQUALS;
//            }
            switch (tObj.getTokenType()) {
                case GREATERTHAN:
                    booleanComp = BooleanCompareNode.BooleanComp.GREATERTHAN;
                    break;
                case LESSTHAN:
                    booleanComp = BooleanCompareNode.BooleanComp.LESSTHAN;
                    break;
                case GTE:
                    booleanComp = BooleanCompareNode.BooleanComp.GTE;
                    break;
                case LTE:
                    booleanComp = BooleanCompareNode.BooleanComp.LTE;
                    break;
                case COMPARISON:
                    booleanComp = BooleanCompareNode.BooleanComp.EQUALS;
                    break;
                case DOESNOTEQUAL:
                    booleanComp = BooleanCompareNode.BooleanComp.DOESNOTEQUALS;
                    break;
                default:
                    // handle unexpected token type
                    return null;
            }

            left = new BooleanCompareNode(booleanComp, left, right);
            System.out.println(left);

        }
        return left;

    }


    //Then expression runs and looks for a + or -.
    //expression should call term

    private Node expression(){
        //System.out.println("exp");
        //call term
        Node term1 = term();
            while (true){
            Token tObj = matchAndRemove(Token.TokenType.PLUS, Token.TokenType.MINUS);
                if(tObj == null){
                    break;
                }
                Node term2 = term();
                MathOpNode.MathOp mathOp;
                if (tObj.getTokenType() == Token.TokenType.PLUS) {
                    mathOp = MathOpNode.MathOp.PLUS;
                } else {
                    mathOp = MathOpNode.MathOp.MINUS;
                }

                term1 = new MathOpNode(mathOp, term1, term2);
                System.out.println(term1);

            }
            return term1;

//        //matchAndRemove + or -, if not return term1
//        Token tObj = matchAndRemove(Token.TokenType.PLUS);
//
//        if (tObj == null) {
//            tObj = matchAndRemove(Token.TokenType.MINUS);
//        }
//
//        if (tObj == null) {
//            return term1;
//        }
//
//        //else get term2
//        Node term2 = term();
//
//        //calc term1 + or - term2
//        return new MathOpNode(tObj.getTokenType(), term1, term2);

        //return result of calculation
        //return answer;
    }

    private AssignmentNode assignment() {
        // Parse the variable name
        Token varNameToken = matchAndRemove(Token.TokenType.WORD);
        if (varNameToken == null) {
            // Missing variable name
            throw new SyntaxErrorException("Syntax error: expected a variable reference");
        }

        // Parse the optional array index expression (if present)
        Node indexExpr = null;
        if (matchAndRemove(Token.TokenType.LBRACKET) != null) {
            indexExpr = expression();
            if (matchAndRemove(Token.TokenType.RBRACKET) == null) {
                // Missing closing bracket
                return null;
            }
        }

        // Create the variable reference node
        VariableReferenceNode varRef = new VariableReferenceNode(varNameToken.getValue(), indexExpr);

        // Expect an assignment operator :=
        if (matchAndRemove(Token.TokenType.ASSIGNMENT) == null) {
            // Missing assignment operator
            throw new SyntaxErrorException("Syntax error: expected the := operator");
        }

        // Parse the value expression
        Node value = booleanCompare();
            if(value == null){
                throw new SyntaxErrorException("Syntax error: expected an expression after the := operator");
            }

        // Create and return the assignment node
        AssignmentNode assignment = new AssignmentNode(varRef, value);
        System.out.println(assignment);
        return assignment;
    }


}
