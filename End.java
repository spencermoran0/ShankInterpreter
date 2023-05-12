import java.util.List;

public class End extends FunctionNode{

    public End(List<VariableNode> parameters){
        super("end", parameters, null, null, null);
    }

    public void execute(List<InterpreterDataType> args){
        int end = ((IntDataType) args.get(0)).getValue();
        Token intToken = new Token(Token.TokenType.INTEGER, String.valueOf(end));
        VariableNode endNode = new VariableNode(VariableNode.VarNodeType.PARAMETER, "EndNode", "parameter", new IntegerNode(end), true, null, null);
    }
}
