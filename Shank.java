import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class Shank {

    public static void main (String[] args) {

        Path filePath = Paths.get(args[0]);
// Gets the file path from args array

        Charset charSet = Charset.forName("ISO-8859-1"); // Used in File.readAllLines for decoding file to specified character set

        String content = null;


        if (args.length != 1)
        {
            System.out.println("INVALID ARGUMENT(S)");
            System.out.println(args.length);

        } else {
            try {
                //content = readFile(filePath,charSet);
                List<String> lines = Files.readAllLines(filePath, charSet);
                //System.out.print(lines);
                ArrayList<Token> allTokens = new ArrayList<>();
                int indentLevel = 0;


                for (String line : lines) {
                    //lexercurrentIndentLevel = 0;
                    Lexer lexerInstance = new Lexer();
                    lexerInstance.prevIndentLevel = indentLevel;
                    lexerInstance.currentIndentLevel = 0;
                    lexerInstance.lex(line);
                    lexerInstance.prevIndentLevel = lexerInstance.currentIndentLevel;
                    indentLevel = lexerInstance.prevIndentLevel;
                    List<Token> tokens = lexerInstance.lexedTokens;
                    allTokens.addAll(tokens);

//                    Parser parser = new Parser((ArrayList<Token>) tokens);
//                    parser.parse();
                    //parser.getProgramNode();


                }

                Parser parserInstance = new Parser(allTokens);
                parserInstance.parse();
                parserInstance.getProgramNode();

                //call semantic analysis

                //call interpreter

                //TODO add built-in functions to hashmap in FunctionNode

                //SquareRoot sqrt = new SquareRoot();
//                List<Token> tokens = lexerInstance.lexedTokens;
//                Parser parser = new Parser((ArrayList<Token>) tokens);
//                parser.parse();
//                ProgramNode programNode = parser.parse();
//
//                for (FunctionNode functionNode : programNode.getFunction()) {
//                    System.out.println(functionNode.toString());
//                }

//
                if (lines.isEmpty()){
                    System.out.print("isEmpty");
                }

                //System.out.print(lines);

                //System.out.println("main");

            } catch (IOException e) {

                System.out.format("I/O error: %s%n", e);

            }

        }

    }
}
