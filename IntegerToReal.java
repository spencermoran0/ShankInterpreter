import java.util.List;

public class IntegerToReal extends FunctionNode {

    public IntegerToReal(List<VariableNode> parameters, List<VariableNode> constant) {
        super("intToReal", parameters, constant, null, null);
    }

    public void execute(List<InterpreterDataType> arguments) {
        IntDataType intValue = (IntDataType) arguments.get(0);
        float floatValue = (float) intValue.getValue();
        VariableNode result = new VariableNode(VariableNode.VarNodeType.PARAMETER, "IntegerToReal","parameter", new RealNode(floatValue), true, null, null);
        //RealDataType realValue = new RealDataType(floatValue);
        //System.out.println("IntegerToReal: " + realValue.toString());
    }
}