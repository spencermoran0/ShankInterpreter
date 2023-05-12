public class RealDataType extends InterpreterDataType {
    private float value;

    public RealDataType(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Float.toString(value);
    }

    @Override
    public void fromString(String input) {
        this.value = Float.parseFloat(input);
    }
}
