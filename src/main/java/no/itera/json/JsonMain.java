package no.itera.json;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Scanner;

public class JsonMain {

    private static JsonListener listener = new JsonListenerImpl(); // listener

    public static void main(String[] args) {
        try (Scanner scanIn = new Scanner(System.in)) {
            String json;
            do  {
                System.out.println("Enter json: ");
                json = scanIn.nextLine();
                if (json.isEmpty()) {
                    break;
                }
                walk(json);
            } while (true);
        }
    }

    // {"":null, "":"", "":{ "x":null},"":""}
    // { "array":[]}
    // { "array":["x"]}
    private static void walk(String json) {
        CharStream inputStream = CharStreams.fromString(json);
        JsonLexer lexer = new JsonLexer(inputStream); // lexer
        CommonTokenStream stream = new CommonTokenStream(lexer);
        JsonParser parser = new JsonParser(stream); // parser
        ParseTree tree = parser.object();
        ParseTreeWalker walker = new ParseTreeWalker(); // walker
        walker.walk(listener, tree); // listener
    }
}
