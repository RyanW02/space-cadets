package barebones.interpreter.instructionset.impl;

import barebones.interpreter.ScopeManager;
import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.data.NameData;

import java.util.List;
import java.util.Map;

public class Increment extends Instruction<NameData> {
    @Override
    public String getToken() {
        return "incr";
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
        int current = state.get(data.getName());
        state.set(data.getName(), current + 1);
    }
}
