public class IntDataType extends InterpreterDataType {
    private int value;

    public IntDataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public void fromString(String input) {
        value = Integer.parseInt(input);
    }
}
