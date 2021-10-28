package barebones.interpreter.instructionset.impl;

import barebones.interpreter.ScopeManager;
import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.data.NameData;
import barebones.interpreter.instructionset.impl.data.NoData;

import java.util.List;

public class Quit extends Instruction<NoData> {
    @Override
    public String getToken() {
        return "quit";
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public NoData parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        return new NoData();
    }

    @Override
    public void execute(ScopeManager state, InstructionData iData) {
        NoData data = (NoData) iData;
        System.exit(0);
    }
}
