import java.util.List;
public class FunctionCallNode extends Node {
    private String functionName;
    private List<ParameterNode> arguments;

    public FunctionCallNode(String functionName){
        this.functionName = functionName;
    }
    public FunctionCallNode(String functionName, List<ParameterNode> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<ParameterNode> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FunctionCallNode: ").append(functionName).append("(");

        if (this.arguments != null) {
            for (int i = 0; i < arguments.size(); i++) {
                sb.append(arguments.get(i));
                if (i < arguments.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(")");

        return sb.toString();
    }

}
