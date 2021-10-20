package barebones.interpreter.instructionset;

import barebones.interpreter.State;

import java.util.List;
import java.util.Map;

public abstract class Instruction<T extends InstructionData> {
    public abstract String getToken();
    public abstract boolean hasBlock();
    public abstract T parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions);
    public abstract void execute(State state, InstructionData data);
}
