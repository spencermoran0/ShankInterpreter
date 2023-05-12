import java.util.List;

public class WhileNode extends Node {
    private Node condition;
    private List<StatementNode> body;

    public WhileNode(Node condition, List<StatementNode> body) {
        this.condition = condition;
        this.body = body;
    }

    public Node getCondition() {
        return condition;
    }

    public List<StatementNode> getBody() {
        return body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("whileNode ").append(condition).append(" {\n");
        for (StatementNode statement : body) {
            sb.append("\t").append(statement).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
