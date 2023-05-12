import java.time.temporal.ValueRange;

public class VariableNode extends Node {
    private String name;
    //private Node type;
    private String type;
    private Node value;
    private Node fromNode;
    private Node toNode;
    private boolean isChangeable;

    public enum VarNodeType{
        PARAMETER,
        RANGE,
        DEFINITION
    }

    private VarNodeType varType;

    public VariableNode(VarNodeType varType, String name, String type){
        this.varType = varType;
        this.name = name;
        this.type = type;
    }

    public VariableNode(VarNodeType varType, String name, String type, Node value, boolean isChangeable, Node fromNode, Node toNode) {
        this.varType = varType;
        this.name = name;
        this.type = type;
        //this.value = value;
        this.isChangeable = isChangeable;
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Node getValue() {
        return value;
    }

    public boolean isChangeable() {
        return isChangeable;
    }

    public void setValue(Node value) {
        this.value = value;
    }

    @Override
    public String toString() {

        switch(this.varType){

            case PARAMETER:
                return String.format("VariableNode(name=%s, type=%s)",
                        name, type.toString());
            case DEFINITION:
                return String.format("VariableNode(name=%s, type=%s, value=%s, isChangeable=%s)",
                name, type.toString(), value.toString(), isChangeable);
            case RANGE:
                return String.format("VariableNode(name=%s, type=%s, from=%s, to=%s",
                        name, type.toString(), fromNode.toString(), toNode.toString());
            default:
                return "Unknown token type";

        }
//        return String.format("VariableNode(name=%s, type=%s, value=%s, isChangeable=%s)",
//                name, type.toString(), value.toString(), isChangeable);

//        return String.format("VariableNode(name=%s, type=%s)",
//                name, type.toString());

    }

}
