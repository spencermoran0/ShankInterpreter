import java.util.HashMap;

public class ProgramNode extends Node {
    private HashMap<String, FunctionNode> functions;

    public ProgramNode() {
        this.functions = new HashMap<>();
    }

    public void addFunction(FunctionNode function) {
        String functionName = function.getName();
        if (functions.containsKey(functionName)) {
            throw new IllegalArgumentException("Function " + functionName + " already exists.");
        }
        functions.put(functionName, function);
    }

    public FunctionNode getFunction(String functionName) {
        return functions.get(functionName);
    }

    public HashMap<String, FunctionNode> getFunctions() {
        return functions;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Program Node:\n");
        for (FunctionNode function : functions.values()) {
            sb.append(function.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
