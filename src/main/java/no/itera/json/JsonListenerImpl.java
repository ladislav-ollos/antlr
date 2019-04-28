package no.itera.json;

import org.antlr.v4.runtime.tree.ErrorNode;

public class JsonListenerImpl extends JsonBaseListener {

    @Override
    public void exitArray(JsonParser.ArrayContext ctx) {
        System.out.println("Array:" + ctx.getText());
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
    public void visitErrorNode(ErrorNode node) {
        // deal with parsing errors
        super.visitErrorNode(node);
    }

}
