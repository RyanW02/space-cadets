package barebones.interpreter.instructionset.operations;

import java.util.Arrays;

public enum ArithmeticOperator {
    Add("+"),
    Subtract("-"),
    Multiply("*"),
    Divide("/"),
    Modulus("%"),
    Power("^"),
    ;

    private final String[] tokens;

    ArithmeticOperator(String... tokens) {
        this.tokens = tokens;
    }

    public static ArithmeticOperator getByToken(String token) {
        for (ArithmeticOperator op : values()) {
            if (Arrays.asList(op.tokens).contains(token)) {
                return op;
            }
        }

        throw new IllegalArgumentException("Operator not found");
    }

    public int calculate(int one, int two) {
        switch (this) {
            case Add -> {
                return one + two;
            }
            case Subtract -> {
                return one - two;
            }
            case Multiply -> {
                return one * two;
            }
            case Divide -> {
                return one / two;
            }
            case Modulus -> {
                return one % two;
            }
            case Power -> {
                return (int) Math.pow((double) one, (double) two);
            }
            default -> { // Impossible
                throw new IllegalArgumentException("Invalid operator type");
            }
        }
    }
}
