public class StringNode extends Node {

    private String string;

    public StringNode(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }

}

