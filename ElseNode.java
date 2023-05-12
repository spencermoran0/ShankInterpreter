import java.util.List;

public class ElseNode extends Node {
    private Node condition;
    private List<StatementNode> statements;
    private ElseNode elseNode;

    public ElseNode(Node condition, List<StatementNode> statements) {
        this.condition = condition;
        this.statements = statements;
        this.elseNode = null;
    }

    public Node getCondition() {
        return condition;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }

    public void setElseNode(ElseNode elseNode) {
        this.elseNode = elseNode;
    }

    public ElseNode getElseNode() {
        return elseNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ElseNode: ");
        sb.append(condition);
        sb.append("\n");
        for (StatementNode statement : statements) {
            sb.append(statement.toString());
            sb.append("\n");
        }
        if (elseNode != null) {
            sb.append("Else: ");
            sb.append(elseNode.toString());
        }
        return sb.toString();
    }
}
