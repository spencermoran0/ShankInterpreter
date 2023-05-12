import java.util.List;

public class SquareRoot extends FunctionNode {
    public SquareRoot(List<VariableNode> parameters) {
        super("sqrt", parameters, null, null, null);
    }

    public void execute(List<InterpreterDataType> parameters) {
        if (parameters.size() != 1) {
            throw new RuntimeException("sqrt() function expects 1 parameter");
        }
        //VariableNode valueNode = (RealDataType) parameters.get(0);
        InterpreterDataType param = parameters.get(0);

        RealDataType inputValue = (RealDataType) param;
        float input = inputValue.getValue();

        RealDataType resultValue = new RealDataType((float) Math.sqrt(input));
        VariableNode result = new VariableNode(VariableNode.VarNodeType.PARAMETER, "result", "parameter", new RealNode(resultValue.getValue()), true, null, null);


        //int value = ((IntDataType) param).getValue();
        //int result = (int) Math.sqrt(value);
        //VariableNode result = new VariableNode("result", new IntDataType());
        //result.setValue((int) Math.sqrt(value));

        System.out.println(result.getValue());

        System.out.println(result);
    }
}
