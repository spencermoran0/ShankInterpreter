import java.util.List;
import java.util.Random;

public class GetRandom extends FunctionNode {

    public GetRandom(List<VariableNode> parameters) {
        super("get_random", parameters, null, null, null);
    }


    public void execute(List<InterpreterDataType> args) {
//        if (args.size() != 2) {
//            throw new RuntimeException("get_random requires exactly 2 arguments");
//        }

        int min = ((IntDataType) args.get(0)).getValue();
        int max = ((IntDataType) args.get(1)).getValue();

        Random random = new Random();
        int resultData = random.nextInt((max - min) + 1) + min;
        Token intToken = new Token(Token.TokenType.INTEGER, String.valueOf(resultData));
        VariableNode result = new VariableNode(VariableNode.VarNodeType.PARAMETER, "GetRandom","parameter", new IntegerNode(resultData), true, null, null);



        //System.out.println(result);
    }
}
