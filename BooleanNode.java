public class BooleanNode extends Node {

    private boolean bool;

//    public enum Boolean{
//
//        NOT,
//        AND,
//        OR
//
//    }

    public BooleanNode(boolean bool){
        this.bool = bool;
    }

    public boolean getBoolean(){
        return this.bool;
    }

    public String toString() {
        return String.valueOf(bool);
    }


//    @Override
//    public String toString(){
//        return String.format("Boolean(%s)", booleanToString());
//
//    }
//
//    private String booleanToString(){
//        switch(bool){
//            case NOT:
//                return "NOT";
//            case OR:
//                return "OR";
//            case AND:
//                return "AND";
//            default:
//                throw new IllegalArgumentException("Unknown operation");
//
//        }
//
//        }
}
