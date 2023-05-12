public class AssignmentNode extends StatementNode {
    private VariableReferenceNode target;
    private Node value;

    public AssignmentNode(VariableReferenceNode target, Node value) {
        this.target = target;
        this.value = value;
    }

    public VariableReferenceNode getTarget(){
        return this.target;
    }

    public Node getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return target.toString() + " = " + value.toString();
    }
}
