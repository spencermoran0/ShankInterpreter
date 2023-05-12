public class BooleanCompareNode extends Node{

    private BooleanComp booleanComp;
    private Node left;
    private Node right;

    public enum BooleanComp{
        LESSTHAN,
        GREATERTHAN,
        LTE,
        GTE,
        EQUALS,
        DOESNOTEQUALS;

    }

    BooleanCompareNode (BooleanComp booleanComp, Node left, Node right){
        this.booleanComp = booleanComp;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return this.left;
    }
    public Node getRight() {
        return right;
    }

    public String getComparison(){
        return this.booleanComp.name();
    }

    @Override
    public String toString(){
        return String.format("BooleanComparisonNode(%s,%s,%s)", left, comparisonToString(), right);
    }

    private String comparisonToString(){
        switch (booleanComp){
            case LTE:
                return "<=";
            case GTE:
                return ">=";
            case LESSTHAN:
                return "<";
            case GREATERTHAN:
                return ">";
            case EQUALS:
                return "=";
            case DOESNOTEQUALS:
                return "<>";
            default:
                throw new IllegalArgumentException("Unknown Comparison");
        }
    }

}
