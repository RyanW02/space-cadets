package barebones.interpreter.instructionset.impl;

import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.data.NoData;

import java.util.List;

public class End extends Instruction<NoData> {
    @Override
    public String getToken() {
        return "end";
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public NoData parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        throw new IllegalStateException("end cannot be parsed");
    }

    @Override
    public void execute(State state, InstructionData iData) {
        throw new IllegalStateException("end cannot be executed");
    }
}
