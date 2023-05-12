import java.util.List;

public class RealToInteger extends FunctionNode {

    public RealToInteger(List<VariableNode> parameters, List<VariableNode> constant) {
        super("intToReal", parameters, constant, null, null);
    }

    public void execute(List<InterpreterDataType> arguments) {
        RealDataType realValue = (RealDataType) arguments.get(0);
        int intValue = (int) realValue.getValue();
        Token intData = new Token(Token.TokenType.INTEGER, String.valueOf(intValue));
        VariableNode result = new VariableNode(VariableNode.VarNodeType.PARAMETER, "RealToInteger","parameter", new IntegerNode(intValue), true, null, null);
        //System.out.println("RealToInteger: " + intData.toString());
    }
}