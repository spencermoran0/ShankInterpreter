import java.util.List;

public class Write extends FunctionNode {
    public Write(List<VariableNode> parameters) {
        super("write", parameters, null, null, null);
    }

//    @Override
//    public DataType execute(List<DataType> argValues) {
//        StringBuilder sb = new StringBuilder();
//        for (DataType arg : argValues) {
//            sb.append(arg.toString());
//        }
//        System.out.print(sb.toString());
//        return null;
//    }

    public void execute(List<InterpreterDataType> parameters){
        for (InterpreterDataType param : parameters){
            System.out.println(param);
        }
    }

    @Override
    public boolean isVariadic() {
        return true;
    }

}
