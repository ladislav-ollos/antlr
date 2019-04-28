package no.itera.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

public class CalculatorVisitorImpl extends CalculatorBaseVisitor<BigInteger> {

    private final Logger logger = LoggerFactory.getLogger(CalculatorVisitorImpl.class);

    @Override
    public BigInteger visitProd(CalculatorParser.ProdContext ctx) {
        if (ctx.op == null) {
            return visitChildren(ctx);
        }
        switch (ctx.op.getText()) {
            case "/":
                return visit(ctx.left).divide(visit(ctx.right));
            case "%":
                return visit(ctx.left).mod(visit(ctx.right));
            case "*":
                return visit(ctx.left).multiply(visit(ctx.right));
            default:
                throw new IllegalArgumentException("Unknown operator '" + ctx.op.getText() + "'");
        }
    }

    @Override
    public BigInteger visitParenthesis(CalculatorParser.ParenthesisContext ctx) {
        return visit(ctx.children.get(1));
    }

    @Override
    public BigInteger visitSum(CalculatorParser.SumContext ctx) {
        BigInteger sum = visit(ctx.expr.get(0));
        for (int i = 1; i < ctx.expr.size(); i++) {
            BigInteger right = visit(ctx.expr.get(i));
            int type = ctx.op.get(i - 1).getType();
            if (type == CalculatorLexer.PLUS) {
                sum = sum.add(right);
            } else if (type == CalculatorLexer.MINUS) {
                sum = sum.subtract(right);
            } else {
                throw new IllegalArgumentException("Unknown operator '" + ctx.op.get(i - 1).getText() + "'");
            }
        }
        return sum;
    }

    @Override
    public BigInteger visitBitwise(CalculatorParser.BitwiseContext ctx) {
        BigInteger left = visit(ctx.left);
        BigInteger right = visit(ctx.right);
        switch (ctx.op.getType()) {
            case CalculatorLexer.SHIFT_L:
                return left.shiftLeft(right.intValueExact());
            case CalculatorLexer.SHIFT_R:
                return left.shiftRight(right.intValueExact());
            case CalculatorLexer.CARET:
                return left.xor(right);
            case CalculatorLexer.AMP:
                return left.and(right);
            case CalculatorLexer.VERTI_BAR:
                return left.or(right);
            default:
                throw new IllegalArgumentException("Unknown operator '" + ctx.op.getText() + "'");
        }
    }

    @Override
    public BigInteger visitFactorial(CalculatorParser.FactorialContext ctx) {
        return Calculations.factorial(visit(ctx.exp).longValueExact());
    }

    @Override
    public BigInteger visitBinaryFunction(CalculatorParser.BinaryFunctionContext ctx) {
        switch (ctx.f.getType()) {
            case CalculatorLexer.EXP:
                return visit(ctx.first).pow(visit(ctx.second).intValueExact());
            case CalculatorLexer.GCD:
                return visit(ctx.first).gcd(visit(ctx.second));
            default:
                throw new IllegalArgumentException("Unknown function '" + ctx.f.getText() + "'");
        }
    }

    @Override
    public BigInteger visitError(CalculatorParser.ErrorContext ctx) {
        logger.error("this really should not happen" + ctx);
        throw new RuntimeException("wrong input");
    }

    @Override
    public BigInteger visitDecimal(CalculatorParser.DecimalContext ctx) {
        return new BigInteger(ctx.getText());
    }

    @Override
    public BigInteger visitHexadecimal(CalculatorParser.HexadecimalContext ctx) {
        return new BigInteger(ctx.getText().substring(2), 16);
    }

    @Override
    public BigInteger visitOctal(CalculatorParser.OctalContext ctx) {
        return new BigInteger(ctx.getText().substring(1), 8);
    }
}
