import java.util.ArrayList;
import java.util.List;

public class ArrayDataType<T extends InterpreterDataType> extends InterpreterDataType {
    private List<T> elements;
    private Class<T> elementType;

    public ArrayDataType(Class<T> elementType) {
        this.elements = new ArrayList<T>();
        this.elementType = elementType;
    }

    public void addElement(T element) {
        this.elements.add(element);
    }

    public List<T> getElements() {
        return this.elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.elements.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.elements.get(i).toString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void fromString(String input) {
        String[] elementStrings = input.split(",");
        for (String elementString : elementStrings) {
            try {
                T element = elementType.getDeclaredConstructor().newInstance();
                element.fromString(elementString.trim());
                this.addElement(element);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
