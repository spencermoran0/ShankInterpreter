public class MathOpNode extends Node{

    private MathOp mathOp;
    private Node left;
    private Node right;

    public enum MathOp{
        PLUS,
        MINUS,
        DIVIDE,
        MODULO,
        TIMES;

    }



//    MathOpNode(Token.TokenType mathOp, Node left, Node right) {
//        this.mathOp = mathOp;
//        this.left = left;
//        this.right = right;
//    }

    MathOpNode(MathOp mathOp, Node left, Node right) {
        this.mathOp = mathOp;
        this.left = left;
        this.right = right;
    }
    public Node getLeft() {
        return this.left;
    }
    public Node getRight() {
        return right;
    }
    public String getOp() {
        return this.mathOp.name();
    }

    @Override
    public String toString() {
        return String.format("MathOpNode(%s %s %s)", left, operationToString(), right);
    }

    private String operationToString() {
        switch (mathOp) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case TIMES:
                return "*";
            case DIVIDE:
                return "/";
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }
}


