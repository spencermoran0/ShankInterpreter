public class ParameterNode extends Node {
    private VariableReferenceNode varRefNode;
    private Node valueNode;

    public ParameterNode(VariableReferenceNode varRefNode, Node valueNode) {
        this.varRefNode = varRefNode;
        this.valueNode = valueNode;
    }

    public VariableReferenceNode getVarRefNode() {
        return varRefNode;
    }

    public Node getValueNode() {
        return valueNode;
    }


    @Override
    public String toString() {
        if (varRefNode != null) {
            return "VAR " + varRefNode.toString();
        } else {
            return valueNode.toString();
        }
    }
}
