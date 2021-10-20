package barebones.interpreter.instructionset.operations;

import barebones.interpreter.State;

public abstract class Operand {
    public abstract OperandType getType();
    public abstract int getValue(State state);
}
