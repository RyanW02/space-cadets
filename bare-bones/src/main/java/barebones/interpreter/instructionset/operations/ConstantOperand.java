package barebones.interpreter.instructionset.operations;

import barebones.interpreter.State;

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
    public int getValue(State state) {
        return this.value;
    }
}
