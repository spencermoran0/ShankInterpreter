import java.util.List;

public class Substring extends FunctionNode {
    public Substring(List<VariableNode> parameters) {
        super("substring", parameters, null, null, null);
    }

    public void execute(List<InterpreterDataType> parameters) throws RuntimeException {
//        if (parameters.size() != 3) {
//            throw new RuntimeException("substring function expects 3 arguments");
//        }

        String s = ((StringDataType) parameters.get(0)).getValue();
        int start = ((IntDataType) parameters.get(1)).getValue();
        int length = ((IntDataType) parameters.get(2)).getValue();

        if (start < 0 || start > s.length() - 1) {
            throw new RuntimeException("substring start index is out of range");
        }

        if (start + length > s.length()) {
            throw new RuntimeException("substring length is out of range");
        }

        String result = s.substring(start, start + length);
        VariableNode resultNode = new VariableNode(VariableNode.VarNodeType.PARAMETER, "Substring","parameter", new StringNode(result), true, null, null);

        //InterpreterDataType resultData = new StringDataType(result);
        //getVariables().put(getName(), resultData);
    }
}
