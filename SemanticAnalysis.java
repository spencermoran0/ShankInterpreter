import java.util.List;

public class SemanticAnalysis {

    public static void checkAssignments(ProgramNode program) {
        for (FunctionNode function : program.getFunctions().values()) {
            checkFunctionAssignments(function);
        }
    }

    private static void checkFunctionAssignments(FunctionNode function) {
        List<AssignmentNode> assignments = function.getAssignments();
        for (AssignmentNode assignment : assignments) {
            checkAssignment(assignment, function);
        }
    }

    private static void checkAssignment(AssignmentNode assignment, FunctionNode function) {
        String varName = assignment.getTarget().getName();
        InterpreterDataType varType = function.getLocalVariables().get(varName);
        if (varType == null) {
            throw new RuntimeException("Variable " + varName + " is not declared in function " + function.getName());
        }
        InterpreterDataType rhsType = getType(assignment.getValue(), function);
        if (!varType.getClass().equals(rhsType.getClass())) {
            throw new RuntimeException("Type mismatch in assignment to variable " + varName + " in function " + function.getName()
                    + ". Expected type " + varType.getClass().getSimpleName() + ", but got type " + rhsType.getClass().getSimpleName());
        }
    }

    private static InterpreterDataType getType(Node expression, FunctionNode function) {
        //Look to see if variable has been declared
        if (expression instanceof VariableNode) {
            String varName = ((VariableNode) expression).getName();
            InterpreterDataType varType = function.getLocalVariables().get(varName);
            if (varType == null) {
                throw new RuntimeException("Variable " + varName + " is not declared in function " + function.getName());
            }
            //return varType;
        } else if (expression instanceof IntegerNode) {
            int val = ((IntegerNode) expression).getValue();
            IntDataType intDataType = new IntDataType(val);
            return intDataType;
        } else if(expression instanceof RealNode){
            float val = ((RealNode) expression).getValue();
            RealDataType realDataType =  new RealDataType(val);
            return realDataType;
        } else if (expression instanceof StringNode){
            String val = ((StringNode) expression).getString();
            StringDataType stringDataType = new StringDataType(val);
            return stringDataType;
        } else if (expression instanceof CharacterNode) {
            char val = ((CharacterNode) expression).getValue();
            CharacterDataType characterDataType = new CharacterDataType(val);
            return characterDataType;
        } else if (expression instanceof ArrayNode){
            ArrayDataType arrayDataType = new ArrayDataType(null);
            return arrayDataType;
        } else if (expression instanceof BooleanNode) {
            boolean val = ((BooleanNode) expression).getBoolean();
            BooleanDataType booleanDataType = new BooleanDataType(val);
            return booleanDataType;
        }else if (expression instanceof MathOpNode) {
            MathOpNode operator = (MathOpNode) expression;
            InterpreterDataType lhsType = getType(operator.getLeft(), function);
            InterpreterDataType rhsType = getType(operator.getRight(), function);
        }
//        else if (expression instanceof UnaryOperatorNode) {
//            UnaryOperatorNode operator = (UnaryOperatorNode) expression;
//            InterpreterDataType operandType = getType(operator.getOperand(), function);
//            return operandType.applyUnaryOperator(operator.getOperator());
//        }
        else {
            throw new RuntimeException("Invalid expression type: " + expression.getClass().getSimpleName());
        }
        return null;
    }
}
