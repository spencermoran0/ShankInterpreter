import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Interpreter {
    private HashMap<String, FunctionNode> functions;
    HashMap<String, InterpreterDataType> variableMap = new HashMap<>();
    HashMap<String, InterpreterDataType> constantMap = new HashMap<>();



    public Interpreter(HashMap<String, FunctionNode> functions) {
        this.functions = functions;
    }

//    public boolean lookupFunctionNode(FunctionNode function){
//        String functionName = function.getName();
//        if (functions.containsKey(functionName)){
//            return true;
//        } else {
//            return false;
//        }
//    }



    public FunctionNode lookupFunctionNode(FunctionCallNode functionCallNode) {
        String functionName = functionCallNode.getFunctionName();
        if (functions.containsKey(functionName)) {
            FunctionNode functionNode = functions.get(functionName);
            List<ParameterNode> arguments = functionCallNode.getArguments();
            int numParams = functionNode.getParameters().size();
            boolean isVariadic = functionNode.isVariadic();

            if (isVariadic) {
                // function definition is variadic, so any number of arguments is allowed
                return functionNode;
            } else if (numParams == arguments.size()) {
                // number of arguments matches the number of parameters in the function definition
                return functionNode;
            } else {
                // number of arguments does not match the number of parameters in the function definition
                throw new RuntimeException("Incorrect number of arguments passed to function " + functionName);
            }
        } else {
            throw new RuntimeException("Function " + functionName + " not defined");
        }
    }


    public void executeFunction(FunctionNode functionNode, List<InterpreterDataType> arguments, HashMap<String, InterpreterDataType> variables, HashMap<String, InterpreterDataType> constants) {
        List<VariableNode> parameters = functionNode.getParameters();
        HashMap<String, InterpreterDataType> localVariables = new HashMap<String, InterpreterDataType>(variables);

        // Assign values to function parameters
        int numParams = parameters.size();
        int numArgs = arguments.size();
        if (numParams != numArgs && !functionNode.isVariadic()) {
            throw new RuntimeException("Incorrect number of arguments passed to function " + functionNode.getName());
        }
        for (int i = 0; i < numParams; i++) {
            localVariables.put(parameters.get(i).getName(), arguments.get(i));
        }
//        if (functionNode.isVariadic()) {
//            List<InterpreterDataType> varargs = new ArrayList<>();
//            for (int i = numParams; i < numArgs; i++) {
//                varargs.add(arguments.get(i));
//            }
//            localVariables.put("varargs", varargs.toArray(new InterpreterDataType[varargs.size()]));
//        }

//        if (functionNode instanceof SquareRoot || functionNode instanceof GetRandom || functionNode instanceof Read || functionNode instanceof Write
//                || functionNode instanceof Left || functionNode instanceof Right || functionNode instanceof Substring || functionNode instanceof Start
//                || functionNode instanceof End){}
        // Call the function
        String functionName = functionNode.getName();
        switch (functionName) {
            case "SquareRoot":
                SquareRoot squareRoot = new SquareRoot(parameters);
                List<InterpreterDataType> argsSQRT = new ArrayList<>();
                for (InterpreterDataType value : localVariables.values()) {
                    argsSQRT.add(value);
                }
                squareRoot.execute(argsSQRT);
                break;

            case "GetRandom":
                GetRandom getRandom = new GetRandom(parameters);
                List<InterpreterDataType> argsRand = new ArrayList<>();
                for (InterpreterDataType value : localVariables.values()){
                    argsRand.add(value);
                }
                getRandom.execute(argsRand);
                break;

            case "Read":
                Read read = new Read(parameters);
                List<InterpreterDataType> argsRead = new ArrayList<>();
                for (InterpreterDataType value : localVariables.values()){
                    argsRead.add(value);
                }
                read.execute(argsRead);
                break;

            case "Write":
                Write write = new Write(parameters);
                List<InterpreterDataType> argsWrite = new ArrayList<>();
                for (InterpreterDataType value : localVariables.values()){
                    argsWrite.add(value);
                }
                write.execute(argsWrite);
                break;

            case "Left":
                Left left = new Left(parameters);
                List<InterpreterDataType> argsLeft = new ArrayList<>();
                for(InterpreterDataType value : localVariables.values()){
                    argsLeft.add(value);
                }
                left.execute(argsLeft);
                break;

            case "Right":
                Right right = new Right(parameters);
                List<InterpreterDataType> argsRight = new ArrayList<>();
                for(InterpreterDataType value : localVariables.values()){
                    argsRight.add(value);
                }
                right.execute(argsRight);
                break;

            case "Substring":
                Substring substring = new Substring(parameters);
                List<InterpreterDataType> argsSubstring = new ArrayList<>();
                for(InterpreterDataType value : localVariables.values()){
                    argsSubstring.add(value);
                }
                substring.execute(argsSubstring);
                break;

            case "Start":
                Start start = new Start(parameters);
                List<InterpreterDataType> argsStart = new ArrayList<>();
                for(InterpreterDataType value : localVariables.values()){
                    argsStart.add(value);
                }
                start.execute(argsStart);
                break;

            case "End":
                End end = new End(parameters);
                List<InterpreterDataType> argsEnd = new ArrayList<>();
                for(InterpreterDataType value : localVariables.values()){
                    argsEnd.add(value);
                }
                end.execute(argsEnd);
                break;

            default:
                // If it's not a built-in function, interpret the function body
                interpretFunction(functionNode, localVariables);
               //return null;
        }
    }



    private List<InterpreterDataType> evaluateParameters(List<Node> parameters) {
        List<InterpreterDataType> parameterValues = new ArrayList<>();

        for (Node parameter : parameters) {
            Node value = expression(parameter);

            if(value instanceof IntegerNode) {
                int val = ((IntegerNode) value).getValue();
                IntDataType copy = new IntDataType(val);
                parameterValues.add(copy);
            }
            if (value instanceof RealNode){
                float val = ((RealNode) value).getValue();
                RealDataType copy = new RealDataType(val);
                parameterValues.add(copy);
            }
            if (value instanceof StringNode){
                String val = ((StringNode) value).getString();
                StringDataType copy = new StringDataType(val);
                parameterValues.add(copy);
            }
            if (value instanceof CharacterNode){
                char val = ((CharacterNode) value).getValue();
                CharacterDataType copy = new CharacterDataType(val);
                parameterValues.add(copy);
            }
//            if (value instanceof ArrayNode){
//                int to = ((ArrayNode)value).getToNode();
//                int from = ((ArrayNode)value).getFromNode();
//
//
//            }
        }
        return parameterValues;
    }


//    public void executeFunction(FunctionNode function, List<InterpreterDataType> parameters){
//        if (function instanceof RealToInteger){
//            //RealToInteger realToInteger = new RealToInteger(null, null);
//        }
//
//    }


    public void interpretFunction(FunctionNode functionNode, HashMap<String, InterpreterDataType> parameters) {
        // Create the local variable hash map

        // Create IDTs for constants and add them to the hash map
        for (VariableNode constant : functionNode.getConstants()) {
            if (constant.getValue() instanceof IntegerNode) {
                String val = constant.getValue().toString();
                IntDataType idt = new IntDataType(Integer.parseInt(val));
                constantMap.put(constant.getName(), idt);
            }
            if (constant.getValue() instanceof StringNode){
                StringDataType idt = new StringDataType(constant.getValue().toString());
                constantMap.put(constant.getName(), idt);
            }
            if (constant.getValue() instanceof BooleanNode) {
                BooleanDataType idt = new BooleanDataType();
                constantMap.put(constant.getName(), idt);
            }

            if (constant.getValue() instanceof RealNode){
                String value = constant.getValue().toString();
                RealDataType idt = new RealDataType(Float.parseFloat(value));
                constantMap.put(constant.getName(), idt);
            }
            if (constant.getValue() instanceof CharacterNode){
                String val = constant.getValue().toString();
                CharacterDataType idt = new CharacterDataType(val.charAt(0));
                constantMap.put(constant.getName(), idt);
            }

        }

        // Create IDTs for local variables and add them to the hash map
        for (VariableNode variable : functionNode.getVariables()) {
            if (variable.getValue() instanceof IntegerNode){
                String val = variable.getValue().toString();
                IntDataType idt = new IntDataType(Integer.parseInt(val));
                variableMap.put(variable.getName(), idt);
            }
            if (variable.getValue() instanceof RealNode){
                String val = variable.getValue().toString();
                RealDataType idt = new RealDataType(Float.parseFloat(val));
                variableMap.put(variable.getName(), idt);
            }
            if (variable.getValue() instanceof StringNode){
                String val = variable.getValue().toString();
                StringDataType idt = new StringDataType(val);
                variableMap.put(variable.getName(), idt);
            }
            if (variable.getValue() instanceof BooleanNode) {
                BooleanDataType idt = new BooleanDataType();
                variableMap.put(variable.getName(), idt);
            }
            if (variable.getValue() instanceof CharacterNode){
                String val = variable.getValue().toString();
                CharacterDataType idt = new CharacterDataType(val.charAt(0));
                variableMap.put(variable.getName(), idt);
            }

            //variableMap.put(variable.getName(), idt);
        }

        // Interpret the statements in the function
        interpretBlock(functionNode.getStatements());

        // Loop over the set of parameters and update variable values if necessary
//        for (String paramName : parameters.keySet()) {
//            InterpreterDataType paramValue = parameters.get(paramName);
//
//            // Check if the function is variadic or the invocation is marked as VAR
//            if (functionNode.isVariadic() || functionNode.isVariadic() && paramValue.isVar()) {
//                InterpreterDataType result = null;
//
//                // Update the variable value with the result
//                variableMap.put(paramName, result);
//            } else {
//                // If the parameter is not variadic, simply update the variable value
//                variableMap.put(paramName, paramValue);
//            }
//        }
    }


    public void interpretBlock(List<StatementNode> statements) {
        for (Node statement : statements) {
            // process statement and update variables as needed
            if (statement instanceof VariableReferenceNode){
                VariableReferenceNode varRefNode = (VariableReferenceNode) statement;
                try {
                    interpretVariableReferenceNode(varRefNode);
                }
                catch (Exception x){
                    x.printStackTrace();
                }
            }
            if (statement instanceof MathOpNode){
                MathOpNode mathOpNode = (MathOpNode) statement;
                try{
                    interpretMathOpNode(mathOpNode);
                }
                catch (Exception x){
                    x.printStackTrace();
                }
            }
            if (statement instanceof  BooleanNode){
                BooleanCompareNode boolNode = (BooleanCompareNode) statement;
                try{
                    interpretBooleanCompareNode(boolNode);
                }
                catch (Exception x){
                    x.printStackTrace();
                }
            }
            if (statement instanceof IfNode){
                IfNode ifNode = (IfNode) statement;
                try{
                    interpretIfNode(ifNode);
                }
                catch (Exception x){
                    x.printStackTrace();
                }
            }
            if (statement instanceof WhileNode){
                WhileNode whileNode = (WhileNode) statement;
                try{
                    interpretWhileNode(whileNode);
                }
                catch (Exception x){
                    x.printStackTrace();
                }
            }
            if (statement instanceof ForNode){
                ForNode forNode = (ForNode) statement;
                try{
                    interpretForNode(forNode);
                }
                catch (Exception x){
                    x.printStackTrace();
                }
            }
        }
    }

    public InterpreterDataType interpretVariableReferenceNode(VariableReferenceNode node) throws Exception {
        String variableName = node.getName();
        if (!variableMap.containsKey(variableName)) {
            throw new Exception("Variable " + variableName + " has not been defined");
        }
        return variableMap.get(variableName);
    }



    public InterpreterDataType interpretMathOpNode(MathOpNode node) throws Exception {
        Node left = node.getLeft();
        Node right = node.getRight();

        Node leftValue = expression(left);
        Node rightValue = expression(right);

        if (!(leftValue.getClass().equals(rightValue.getClass()))) {
            throw new Exception("Type mismatch");
        }

        if (leftValue instanceof IntegerNode) {
            int leftInt = ((IntegerNode) leftValue).getValue();
            int rightInt = ((IntegerNode) rightValue).getValue();

            switch (node.getOp()) {
                case "PLUS":
                    return new IntDataType(leftInt + rightInt);
                case "MINUS":
                    return new IntDataType(leftInt - rightInt);
                case "TIMES":
                    return new IntDataType(leftInt * rightInt);
                case "DIVIDE":
                    return new IntDataType(leftInt / rightInt);
                case "MODULO":
                    return new IntDataType(leftInt % rightInt);
                default:
                    throw new Exception("Invalid operation");
            }
        } else if (leftValue instanceof RealNode) {
            float leftReal = ((RealNode) leftValue).getValue();
            float rightReal = ((RealNode) rightValue).getValue();

            switch (node.getOp()) {
                case "PLUS":
                    return new RealDataType(leftReal + rightReal);
                case "MINUS":
                    return new RealDataType(leftReal - rightReal);
                case "TIMES":
                    return new RealDataType(leftReal * rightReal);
                case "DIVIDE":
                    return new RealDataType(leftReal / rightReal);
                default:
                    throw new Exception("Invalid operation");
            }
        } else if (leftValue instanceof StringNode) {
            String leftString = ((StringNode) leftValue).getString();
            String rightString = ((StringNode) rightValue).getString();

            switch (node.getOp()) {
                case "PLUS":
                    return new StringDataType(leftString + rightString);
                default:
                    throw new Exception("Invalid operation");
            }
        }

        throw new Exception("Invalid operation");
    }

    private Node expression(Node node){
        if (node instanceof MathOpNode) {
            MathOpNode mathOpNode = (MathOpNode) node;
            try {
                interpretMathOpNode(mathOpNode);
            }
            catch (Exception x){
                x.printStackTrace();
            }
        } else if (node instanceof VariableReferenceNode) {
            String name = ((VariableReferenceNode) node).getName();
            if (!variableMap.containsKey(name)) {
                return null;
            }
            //return (Node) variableMap.get(name);
        } else if (node instanceof IntegerNode) {
            return new IntegerNode(((IntegerNode) node).getValue());
        } else if (node instanceof RealNode) {
            return new RealNode(((RealNode) node).getValue());
        } else if (node instanceof StringNode) {
            return new StringNode(((StringNode) node).getString());
        }
        return null;

    }


    private InterpreterDataType interpretBooleanCompareNode(BooleanCompareNode node) throws Exception {
        Node left = expression(node.getLeft());
        Node right = expression(node.getRight());
        String op = node.getComparison();

        // Ensure that left and right sides are of the same type
        if (left.getClass() != right.getClass()) {
            // Allow comparison between IntegerNode and RealNode
            if (!((left instanceof IntegerNode && right instanceof RealNode)
                    || (left instanceof RealNode && right instanceof IntegerNode))) {
                throw new Exception("Boolean comparison between different data types");
            }
        }

        // Perform the comparison
        switch (op) {
            case "=":
                if (left instanceof IntegerNode && right instanceof IntegerNode) {
                    return new BooleanDataType(((IntegerNode) left).getValue() == ((IntegerNode) right).getValue());
                } else if (left instanceof RealNode && right instanceof RealNode) {
                    return new BooleanDataType(((RealNode) left).getValue() == ((RealNode) right).getValue());
                } else if (left instanceof IntegerNode && right instanceof RealNode) {
                    return new BooleanDataType(((IntegerNode) left).getValue() == ((RealNode) right).getValue());
                } else if (left instanceof RealNode && right instanceof IntegerNode) {
                    return new BooleanDataType(((RealNode) left).getValue() == ((IntegerNode) right).getValue());
                }
                break;
            case "<>":
                if (left instanceof IntegerNode && right instanceof IntegerNode) {
                    return new BooleanDataType(((IntegerNode) left).getValue() != ((IntegerNode) right).getValue());
                } else if (left instanceof RealNode && right instanceof RealNode) {
                    return new BooleanDataType(((RealNode) left).getValue() != ((RealNode) right).getValue());
                } else if (left instanceof IntegerNode && right instanceof RealNode) {
                    return new BooleanDataType(((IntegerNode) left).getValue() != ((RealNode) right).getValue());
                } else if (left instanceof RealNode && right instanceof IntegerNode) {
                    return new BooleanDataType(((RealNode) left).getValue() != ((IntegerNode) right).getValue());
                }
                break;
            case "<":
                if (left instanceof IntegerNode && right instanceof IntegerNode) {
                    return new BooleanDataType(((IntegerNode) left).getValue() < ((IntegerNode) right).getValue());
                } else if (left instanceof RealNode && right instanceof RealNode) {
                    return new BooleanDataType(((RealNode) left).getValue() < ((RealNode) right).getValue());
                } else {
                    throw new Exception("Invalid comparison operation");
                }
            case "<=":
                if (left instanceof IntegerNode && right instanceof IntegerNode) {
                    return new BooleanDataType(((IntegerNode) left).getValue() <= ((IntegerNode) right).getValue());
                } else if (left instanceof RealNode && right instanceof RealNode) {
                    return new BooleanDataType(((RealNode) left).getValue() <= ((RealNode) right).getValue());
                } else {
                    throw new Exception("Invalid comparison operation");
                }
            case ">":
                if (left instanceof IntegerNode && right instanceof IntegerNode) {
                    return new BooleanDataType(((IntegerNode) left).getValue() > ((IntegerNode) right).getValue());
                } else if (left instanceof RealNode && right instanceof RealNode) {
                    return new BooleanDataType(((RealNode) left).getValue() > ((RealNode) right).getValue());
                } else {
                    throw new Exception("Invalid comparison operation");
                }
            case ">=":
                if (left instanceof IntegerNode && right instanceof IntegerNode) {
                    return new BooleanDataType(((IntegerNode) left).getValue() >= ((IntegerNode) right).getValue());
                } else if (left instanceof RealNode && right instanceof RealNode) {
                    return new BooleanDataType(((RealNode) left).getValue() >= ((RealNode) right).getValue());
                } else {
                    throw new Exception("Invalid comparison operation");
                }
            default:
                throw new Exception("Invalid boolean comparison operator");
        }

        throw new Exception("Boolean comparison between different data types");
    }


    private InterpreterDataType interpretConstantNode(VariableNode constant)throws Exception{
        if (constant.getValue() instanceof IntegerNode) {
            String val = constant.getValue().toString();
            IntDataType idt = new IntDataType(Integer.parseInt(val));
            constantMap.put(constant.getName(), idt);
        }
        if (constant.getValue() instanceof StringNode){
            StringDataType idt = new StringDataType(constant.getValue().toString());
            constantMap.put(constant.getName(), idt);
        }
        if (constant.getValue() instanceof BooleanNode) {
            BooleanDataType idt = new BooleanDataType();
            constantMap.put(constant.getName(), idt);
        }
        if (constant.getValue() instanceof RealNode){
            String value = constant.getValue().toString();
            RealDataType idt = new RealDataType(Float.parseFloat(value));
            constantMap.put(constant.getName(), idt);
            return idt;
        }
        if (constant.getValue() instanceof CharacterNode){
            String val = constant.getValue().toString();
            CharacterDataType idt = new CharacterDataType(val.charAt(0));
            constantMap.put(constant.getName(), idt);
            return idt;
        }
            throw new Exception("Invalid type");


    }

    private void interpretIfNode(IfNode node) throws Exception {
        BooleanCompareNode boolNode = (BooleanCompareNode) node.getCondition();
        BooleanDataType condition = (BooleanDataType) interpretBooleanCompareNode(boolNode);
        if (condition.getValue()) {
            interpretBlock(node.getStatements());
        } else if (node.getElseIfNode() != null){
            BooleanCompareNode elseIfBool = (BooleanCompareNode) node.getElseIfNode().getCondition();
            BooleanDataType elseIfCondition = (BooleanDataType) interpretBooleanCompareNode(elseIfBool);
                if (elseIfCondition.getValue()) {
                    interpretBlock(node.getElseIfNode().getStatements());
                }
        } else if (node.getElseNode() != null){
            interpretBlock(node.getStatements());
        }
    }



    private void interpretWhileNode(WhileNode node) throws Exception {
        while (true) {
            BooleanCompareNode boolNode = (BooleanCompareNode) node.getCondition();
            BooleanDataType condition = (BooleanDataType) interpretBooleanCompareNode(boolNode);
            if (!condition.getValue()) {
                break;
            }
            interpretBlock(node.getBody());
        }
    }



    private void interpretForNode(ForNode node) throws Exception {
        String variableName = node.getVariable().getName();
        Node from = expression(node.getFrom());
        Node to = expression(node.getTo());

        // Ensure that the range values are integers or floats
        if (!(from instanceof IntegerNode || from instanceof RealNode) ||
                !(to instanceof IntegerNode || to instanceof RealNode)) {
            throw new Exception("Invalid range values for for loop");
        }

        // Initialize the loop variable
        float start = Float.parseFloat(from.toString());
        float end = Float.parseFloat(to.toString());
        float step = start <= end ? 1 : -1;

        // Loop over the range
        while ((step > 0 && start <= end) || (step < 0 && start >= end)) {
            // Execute the statements in the block
            interpretBlock(node.getStatements());

            // Update the loop variable
            start += step;
        }
    }



    private void interpretRepeatNode(RepeatNode node) throws Exception {
        while (true) {
            interpretBlock(node.getStatements());
            BooleanCompareNode boolNode = (BooleanCompareNode) node.getCondition();
            BooleanDataType condition = (BooleanDataType) interpretBooleanCompareNode(boolNode);
            if (condition.getValue()) {
                break;
            }
        }
    }



    private void interpretAssignmentNode(AssignmentNode node) throws Exception {
        // Evaluate the expression on the right-hand side of the assignment
        Node value = expression(node.getValue());
        // Get the name of the variable being assigned
        String variableName = node.getTarget().getName();

        //Check if Integer
        if (value instanceof IntegerNode){
            IntegerNode intNode = (IntegerNode) value;
            IntDataType intValue =  new IntDataType(intNode.getValue());

            // Look up the variable in the symbol table
            if (variableMap.containsKey(variableName)) {
                // If the variable already exists in the symbol table, update its value
                variableMap.put(variableName, intValue);
            } else {
                // If the variable does not exist in the symbol table, add it
                throw new Exception("Variable not defined: " + variableName);
            }
        }

        //Check if Real
        if (value instanceof RealNode){
            RealNode floatNode = (RealNode) value;
            RealDataType floatValue =  new RealDataType(floatNode.getValue());

            // Look up the variable in the symbol table
            if (variableMap.containsKey(variableName)) {
                // If the variable already exists in the symbol table, update its value
                variableMap.put(variableName, floatValue);
            } else {
                // If the variable does not exist in the symbol table, add it
                throw new Exception("Variable not defined: " + variableName);
            }
        }

        //Check if String
        if (value instanceof StringNode){
            StringNode stringNode = (StringNode) value;
            StringDataType stringValue =  new StringDataType(stringNode.getString());

            // Look up the variable in the symbol table
            if (variableMap.containsKey(variableName)) {
                // If the variable already exists in the symbol table, update its value
                variableMap.put(variableName, stringValue);
            } else {
                // If the variable does not exist in the symbol table, add it
                throw new Exception("Variable not defined: " + variableName);
            }
        }




    }






}
