package barebones.interpreter.instructionset;

import barebones.interpreter.ScopeManager;

import java.util.List;

public abstract class Instruction<T extends InstructionData> {
    public abstract String getToken();
    public abstract boolean hasBlock();
    public abstract T parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions);
    public abstract void execute(ScopeManager state, InstructionData data);
}
