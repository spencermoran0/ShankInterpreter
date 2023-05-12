public class VariableReferenceNode extends Node {
    private String name;
    private Node arrayIndexExpression;

    public VariableReferenceNode(String name) {
        this.name = name;}
    public VariableReferenceNode(String name, Node arrayIndexExpression) {
        this.name = name;
        this.arrayIndexExpression = arrayIndexExpression;
    }

    public String getName() {
        return name;
    }

    public Node getArrayIndexExpression() {
        return arrayIndexExpression;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (arrayIndexExpression != null) {
            sb.append("[");
            sb.append(arrayIndexExpression);
            sb.append("]");
        }
        return sb.toString();
    }
}
