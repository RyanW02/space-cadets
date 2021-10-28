package barebones.interpreter.instructionset.operations;

import barebones.interpreter.ScopeManager;
import barebones.utils.Utils;

public abstract class Operand {
    public abstract OperandType getType();
    public abstract int getValue(ScopeManager state);

    public static Operand parse(String token) {
        if (Utils.isInt(token)) {
            return new ConstantOperand(Integer.parseInt(token));
        } else {
            return new VariableOperand(token);
        }
    }
}
