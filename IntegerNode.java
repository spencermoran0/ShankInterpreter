public class IntegerNode extends Node {


    public IntegerNode(int value){
        this.value = value;

    }

    private int value;

    public int getValue(){
        return this.value;
    }


    @Override
    public String toString()
    {
        return "IntegerNode(" + String.valueOf(this.value) + ")";
    }
}
