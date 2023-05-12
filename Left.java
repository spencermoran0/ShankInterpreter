import java.util.List;

public class Left extends FunctionNode {
    public Left(List<VariableNode> parameters) {
        super("LEFT", parameters, null, null, null);
    }

    public void execute(List<InterpreterDataType> args){
//        if (args.size() != 2) {
//            throw new InterpreterException("LEFT function expects two arguments");
//        }

        InterpreterDataType arg1 = args.get(0);
        InterpreterDataType arg2 = args.get(1);

//        if (!(arg1 instanceof StringType)) {
//            throw new InterpreterException("LEFT function expects a string as first argument");
//        }
//
//        if (!(arg2 instanceof IntType)) {
//            throw new InterpreterException("LEFT function expects an integer as second argument");
//        }

        String string = ((StringDataType) arg1).getValue();
        int length = ((IntDataType) arg2).getValue();

//        if (length < 0 || length > string.length()) {
//            throw new InterpreterException("Invalid length argument");
//        }

        String subString = string.substring(0, length);
        VariableNode result = new VariableNode(VariableNode.VarNodeType.PARAMETER, "Left","parameter", new StringNode(subString), true, null, null);

        //InterpreterDataType resultType = new StringDataType(subString);

        //InterpreterDataType.getInstance().getExecutionStack().push(resultType);
    }
}
