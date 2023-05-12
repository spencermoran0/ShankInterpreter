public class ArrayNode extends Node {
    private int fromNode;
    private int toNode;

    public ArrayNode(int from, int to) {
        this.fromNode = from;
        this.toNode = to;
    }

    public int getFromNode() {
        return fromNode;
    }

    public int getToNode() {
        return toNode;
    }

    @Override
    public String toString() {
        return "ArrayNode[from=" + fromNode + ", to=" + toNode + "]";
    }
}
