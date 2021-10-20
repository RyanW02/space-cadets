package barebones.interpreter.instructionset.impl;

import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;

import java.util.List;

public class Copy extends Instruction<Copy.Data> {
    @Override
    public String getToken() {
        return "copy";
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public Data parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        return new Data(tokens[0], tokens[1]);
    }

    @Override
    public void execute(State state, InstructionData iData) {
        Data data = (Data) iData;
        state.set(data.getTo(), state.get(data.getFrom()));
    }

    public static class Data extends InstructionData {
        private final String from, to;

        public Data(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }
    }
}
