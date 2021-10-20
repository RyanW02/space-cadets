package barebones.interpreter.instructionset.operations;

import java.util.Arrays;

public enum Operator {
    Equal("==", "=", "eq"),
    NotEqual("!=", "not", "ne"),
    GreaterThan(">", "gt"),
    GreaterThanOrEqual(">="),
    LessThan("<", "lt"),
    LessThanOrEqual("<="),
    ;

    private final String[] tokens;

    Operator(String... tokens) {
        this.tokens = tokens;
    }

    public static Operator getByToken(String token) {
        for (Operator op : values()) {
            if (Arrays.asList(op.tokens).contains(token)) {
                return op;
            }
        }

        throw new IllegalArgumentException("Operator not found");
    }

    public boolean compare(int one, int two) {
        switch (this) {
            case Equal -> {
                return one == two;
            }
            case NotEqual -> {
                return one != two;
            }
            case GreaterThan -> {
                return one > two;
            }
            case GreaterThanOrEqual -> {
                return one >= two;
            }
            case LessThan -> {
                return one < two;
            }
            case LessThanOrEqual -> {
                return one <= two;
            }
            default -> { // Impossible
                throw new IllegalArgumentException("Invalid operator type");
            }
        }
    }
}
