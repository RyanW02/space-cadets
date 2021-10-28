package barebones.interpreter.instructionset.impl;

import barebones.interpreter.ScopeManager;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;

import java.util.List;

public class Set extends Instruction<Set.Data> {
    @Override
    public String getToken() {
        return "set";
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public Data parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        return new Data(tokens[0], Integer.parseInt(tokens[1]));
    }

    @Override
    public void execute(ScopeManager state, InstructionData iData) {
        Data data = (Data) iData;
        state.set(data.getVariableName(), data.getValue());
    }

    public static class Data extends InstructionData {
        private final String variableName;
        private final int value;

        public Data(String from, int value) {
            this.variableName = from;
            this.value = value;
        }

        public String getVariableName() {
            return variableName;
        }

        public int getValue() {
            return value;
        }
    }
}
