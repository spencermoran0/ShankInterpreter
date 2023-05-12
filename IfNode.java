import java.util.ArrayList;
import java.util.List;

public class IfNode extends Node {
    private Node condition;
    private List<StatementNode> statements;
    private IfNode elseIfNode;
    private ElseNode elseNode;

    public IfNode(Node condition, List<StatementNode> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    public Node getCondition() {
        return condition;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }

    public IfNode getElseIfNode() {
        return elseIfNode;
    }

    public void setElseIfNode(IfNode elseIfNode) {
        this.elseIfNode = elseIfNode;
    }

    public ElseNode getElseNode() {
        return elseNode;
    }

    public void setElseNode(ElseNode elseNode) {
        this.elseNode = elseNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IfNode: ");
        sb.append(condition);
        sb.append("\n");
        for (StatementNode statement : statements) {
            sb.append(statement.toString());
            sb.append("\n");
        }
        if (elseIfNode != null) {
            sb.append("ElseIfNode: ");
            sb.append(elseIfNode);
            sb.append("\n");
        }
        if (elseNode != null) {
            sb.append("ElseNode: ");
            sb.append(elseNode);
            sb.append("\n");
        }
        return sb.toString();
    }
}
