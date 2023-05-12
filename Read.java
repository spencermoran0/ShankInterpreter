import java.util.List;
import java.util.Scanner;

public class Read extends FunctionNode {
    public Read(List<VariableNode> parameters) {
        super("READ", parameters, null, null, null);
    }

    public void execute(List<InterpreterDataType> arguments) {
        Scanner scanner = new Scanner(System.in);

        for (InterpreterDataType arg : arguments) {
            if (arg instanceof StringDataType) {
                String value = scanner.next();
                ((StringDataType) arg).setValue(value);
            } else {
                throw new RuntimeException("Invalid argument type for Read function.");
            }
        }
    }

    @Override
    public boolean isVariadic() {
        return true;
    }
}