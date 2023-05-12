public class BooleanDataType extends InterpreterDataType {

    private boolean value;

    public BooleanDataType() {
        this(false);
    }

    public BooleanDataType(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void fromString(String input) {
        this.value = Boolean.parseBoolean(input);
    }

}
