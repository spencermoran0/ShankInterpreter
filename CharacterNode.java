public class CharacterNode extends Node{

    private char value;

    public CharacterNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CharacterNode: " + value;
    }
}
