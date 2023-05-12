import java.util.List;

public class RepeatNode extends Node {
    private Node condition;
    private List<StatementNode> statements;

    public RepeatNode(BooleanCompareNode condition, List<StatementNode> statements) {
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
        sb.append("REPEAT\n");
        for (StatementNode statement : statements) {
            sb.append(statement.toString());
            sb.append("\n");
        }
        sb.append("UNTIL ");
        sb.append(condition.toString());
        return sb.toString();
    }
}
