package barebones.interpreter.instructionset.impl;

import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;

import java.util.List;

public class Print extends Instruction<Print.Data> {
    @Override
    public String getToken() {
        return "print";
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public Data parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        // If tokens are not quoted, i.e. print X; treat as print "{X}";
        if (tokens.length == 1) {
            if (!tokens[0].startsWith("\"") && !tokens[0].endsWith("\"")) {
                return new Data(String.format("{%s}", tokens[0]));
            }
        }

        String joined = String.join(" ", tokens);
        if (!joined.startsWith("\"") && !joined.endsWith("\"")) {
            throw new IllegalArgumentException("illegal print arguments");
        }

        joined = joined.substring(1, joined.length() - 1);
        return new Data(joined);
    }

    @Override
    public void execute(State state, InstructionData iData) {
        Data data = (Data) iData;

        String output = data.getOutput();

        for (String token : output.split(" ")) {
            if (token.startsWith("{") && token.endsWith("}")) {
                String var = token.substring(1, token.length() - 1);
                if (state.exists(var)) {
                    output = output.replace(String.format("{%s}", var), Integer.toString(state.get(var)));
                }
            }
        }

        System.out.println(output);
    }

    public static class Data extends InstructionData {
        private final String output;

        public Data(String output) {
            this.output = output;
        }

        public String getOutput() {
            return output;
        }
    }
}
