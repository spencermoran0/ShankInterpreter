public class RealNode extends Node{

    public RealNode(float value){

        this.value = value;
    }

    private float value;

    public float getValue(){
        return value;
    }

    @Override
    public String toString()
    {
        return "float" + String.valueOf(this.value);
    }
}
