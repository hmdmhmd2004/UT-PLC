import main.ast.core.Program;
import java.io.IOException;
import main.grammar.SimpleLangLexer;
import main.grammar.SimpleLangParser;
import main.visitor.TestVisitor;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class SimpleLang {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a path to a .mol file.");
            return;
        }

        CharStream reader = CharStreams.fromFileName(args[0]);

        SimpleLangLexer lexer = new SimpleLangLexer(reader);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimpleLangParser parser = new SimpleLangParser(tokens);

        Program program = parser.program().programRet;

        if (program != null) {
            TestVisitor testVisitor = new TestVisitor();
            testVisitor.visit(program);
        }
    }
}