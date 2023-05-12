import java.util.List;

public class Right extends FunctionNode {
    public Right(List<VariableNode> parameters) {
        super("Right", parameters, null, null, null);
    }

    public void execute(List<InterpreterDataType> parameters) {
        IntDataType length = (IntDataType) parameters.get(0);
        StringDataType string = (StringDataType) parameters.get(1);

        String value = string.getValue();
        int strLength = value.length();
        int startIndex = Math.max(0, strLength - length.getValue());
        String subString = value.substring(startIndex);
        VariableNode result = new VariableNode(VariableNode.VarNodeType.PARAMETER, "Right","parameter", new StringNode(subString), true, null, null);


        // variableMap.put(getName(), new StringDataType(result));
    }
}
