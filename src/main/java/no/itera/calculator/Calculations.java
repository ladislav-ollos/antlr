package no.itera.calculator;

import java.math.BigInteger;

class Calculations {

    private static final BigInteger MAX_FACTORIAL_ALLOWED = BigInteger.valueOf(100000L);

    private Calculations() {}

    static BigInteger factorial(long n) {
        if (MAX_FACTORIAL_ALLOWED.compareTo(BigInteger.valueOf(n)) < 0) {
            throw new IllegalArgumentException("Can not compute " + n
                    + "! ,factorials of numbers larger than " + MAX_FACTORIAL_ALLOWED + " not supported");
        } else if (BigInteger.ZERO.compareTo(BigInteger.valueOf(n))>0) {
            throw new IllegalArgumentException("Can not compute " + n
                    + "! ,factorials of numbers smaller than " + 0 + " not supported");
        }
        BigInteger factorial = BigInteger.ONE;
        for (long i = 1; i<=n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
