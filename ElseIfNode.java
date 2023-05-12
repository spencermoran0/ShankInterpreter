import java.util.List;
public class ElseIfNode extends Node {
    private Node condition;
    private List<StatementNode> statements;

    public ElseIfNode(Node condition, List<StatementNode> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    public Node getCondition() {
        return condition;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ElseIfNode: ");
        sb.append(condition);
        sb.append("\n");
        for (StatementNode statement : statements) {
            sb.append(statement.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}

