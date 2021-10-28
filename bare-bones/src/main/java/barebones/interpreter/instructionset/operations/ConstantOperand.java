package barebones.interpreter.instructionset.operations;

import barebones.interpreter.ScopeManager;

public class ConstantOperand extends Operand {

    private final int value;

    public ConstantOperand(int value) {
        this.value = value;
    }

    @Override
    public OperandType getType() {
        return OperandType.Constant;
    }

    @Override
    public int getValue(ScopeManager state) {
        return this.value;
    }
}
