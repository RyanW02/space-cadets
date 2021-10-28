package barebones.interpreter.instructionset.impl;

import barebones.interpreter.ScopeManager;
import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.data.NameData;

import java.util.List;

public class Clear extends Instruction<NameData> {
    @Override
    public String getToken() {
        return "clear";
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public NameData parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        return new NameData(tokens[0]);
    }

    @Override
    public void execute(ScopeManager state, InstructionData iData) {
        NameData data = (NameData) iData;
        state.set(data.getName(), 0);
    }
}
