import java.util.List;

public class ForNode extends Node {
    private VariableReferenceNode variable;
    private Node from;
    private Node to;
    private List<StatementNode> statements;

    public ForNode(VariableReferenceNode variable, Node from, Node to, List<StatementNode> statements) {
        this.variable = variable;
        this.from = from;
        this.to = to;
        this.statements = statements;
    }

    public VariableReferenceNode getVariable() {
        return variable;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }


    @Override
    public String toString() {
        return "FORNODE: " + variable + " FROM " + from + " TO " + to + " DO " + statements;
    }
}
