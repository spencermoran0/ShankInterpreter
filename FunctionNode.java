import java.util.List;
import java.util.HashMap;


public class FunctionNode extends Node {
    private static final HashMap<String, FunctionNode> functions = new HashMap<>();

    private String name;
    private List<VariableNode> parameters;
    private List<VariableNode> constants;
    private List<VariableNode> variables;
    private List<StatementNode> statements;
    private List<AssignmentNode> assignments;
    private HashMap<String, InterpreterDataType> localVariables;
    private boolean isVariadic = false;


    public FunctionNode(String name, List<VariableNode> parameters, List<VariableNode> constants, List<VariableNode> variables, List<StatementNode> statements) {
        this.name = name;
        this.parameters = parameters;
        this.constants = constants;
        this.variables = variables;
        this.statements = statements;
    }

    public FunctionNode(String name, List<VariableNode> parameters, List<VariableNode> constants, List<VariableNode> variables, List<StatementNode> statements, boolean isVariadic) {
        this.name = name;
        this.parameters = parameters;
        this.constants = constants;
        this.variables = variables;
        this.statements = statements;
        this.isVariadic = isVariadic;
    }

    public FunctionNode(String name, List<VariableNode> parameters, List<VariableNode> constants, List<VariableNode> variables, List<StatementNode> statements, boolean isVariadic, List<AssignmentNode> assignments) {
        this.name = name;
        this.parameters = parameters;
        this.constants = constants;
        this.variables = variables;
        this.statements = statements;
        this.isVariadic = isVariadic;
        this.assignments = assignments;
    }

    public FunctionNode(String name, List<VariableNode> parameters, List<VariableNode> constants, List<VariableNode> variables, List<StatementNode> statements, boolean isVariadic, List<AssignmentNode> assignments, HashMap<String, InterpreterDataType> localVariables) {
        this.name = name;
        this.parameters = parameters;
        this.constants = constants;
        this.variables = variables;
        this.statements = statements;
        this.isVariadic = isVariadic;
        this.assignments = assignments;
        this.localVariables = localVariables;
    }

    public String getName() {
        return name;
    }

    public List<VariableNode> getParameters() {
        return parameters;
    }

    public List<VariableNode> getConstants() {
        return constants;
    }

    public List<VariableNode> getVariables() {
        return variables;
    }
    public boolean isVariadic() {return isVariadic; }

    public List<StatementNode> getStatements() {
        return statements;
    }
    public List<AssignmentNode> getAssignments() { return assignments; }
    public HashMap<String, InterpreterDataType> getLocalVariables() {
        return localVariables;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FunctionNode{name='").append(name).append("', parameters=").append(parameters);
        sb.append(", constants=").append(constants).append(", variables=").append(variables);
        sb.append(", statements=").append(statements).append('}');
        sb.append('}');
        return sb.toString();
    }
}
