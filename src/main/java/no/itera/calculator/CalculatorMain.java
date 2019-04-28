package no.itera.calculator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.math.BigInteger;
import java.util.Scanner;

public class CalculatorMain {

    private static CalculatorVisitorImpl visitor = new CalculatorVisitorImpl(); // the visitor

    public static void main(String[] args) {
        try (Scanner scanIn = new Scanner(System.in)) {
            String expression;
            do {
                System.out.println("Enter expression: ");
                expression = scanIn.nextLine();
                if (expression.isEmpty()) {
                    break;
                }
                System.out.println(calculate(expression));
            } while (true);
        }
    }

    // (3 << 2) + gcd(12,16) - 8*0x2
    private static BigInteger calculate(String expression) {
        CharStream inputStream = CharStreams.fromString(expression);
        CalculatorLexer lexer = new CalculatorLexer(inputStream); // lexer
        CommonTokenStream stream = new CommonTokenStream(lexer);
        CalculatorParser calculatorParser = new CalculatorParser(stream); // parser
        return visitor.visit(calculatorParser.expression()); //visitor
    }
}
