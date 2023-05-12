public class CharacterDataType extends InterpreterDataType {

    private char value;

    public CharacterDataType(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void fromString(String input) {
        if (input.length() > 0) {
            this.value = input.charAt(0);
        } else {
            throw new IllegalArgumentException("Invalid input string for CharacterDataType");
        }
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

}
