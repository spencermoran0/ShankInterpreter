import java.util.List;

public class Start extends FunctionNode{

    public Start(List<VariableNode> parameters){
        super("start", parameters, null, null, null);
    }

    public void execute(List<InterpreterDataType> args){
        int start = ((IntDataType) args.get(0)).getValue();
        Token intToken = new Token(Token.TokenType.INTEGER, String.valueOf(start));
        VariableNode startNode = new VariableNode(VariableNode.VarNodeType.PARAMETER, "StartNode", "parameter", new IntegerNode(start), true, null, null);
    }
}
