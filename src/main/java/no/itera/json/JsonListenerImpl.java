package no.itera.json;

import org.antlr.v4.runtime.tree.TerminalNode;

import static no.itera.json.JsonLexer.NULL;

public class JsonListenerImpl extends JsonBaseListener {

    @Override
    public void exitArray(JsonParser.ArrayContext ctx) {
        System.out.println("Array:" + ctx.getText());
    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        if (terminalNode.getSymbol().getType() == NULL) {
            System.out.println("Null value found");
        }
    }

}
