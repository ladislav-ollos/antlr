package antlr.example.json;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.List;
import java.util.stream.Collectors;

public class JsonListenerImpl extends JsonBaseListener {

    @Override
    public void exitArray(JsonParser.ArrayContext ctx) {
        System.out.println("Array:" + ctx.getText());
    }

    @Override
    public void exitObject(JsonParser.ObjectContext ctx) {
        System.out.println("Exiting object...");
        List<String> labelNames = ctx.labels.stream().map(Token::getText).collect(Collectors.toList());
        System.out.println("Labels: " + labelNames);
    }

    @Override
    public void exitNull(JsonParser.NullContext ctx) {
        System.out.println("Null value found");
    }


    @Override
    public void exitNum(JsonParser.NumContext ctx) {
        System.out.println("Number found: " + ctx.getText());
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        // do whatever...
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        // deal with parsing errors
        super.visitErrorNode(node);
    }
}
