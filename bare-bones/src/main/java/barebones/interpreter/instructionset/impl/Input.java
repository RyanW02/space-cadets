package barebones.interpreter.instructionset.impl;

import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.data.NameData;

import java.util.List;
import java.util.Scanner;

public class Input extends Instruction<NameData> {
    @Override
    public String getToken() {
        return "input";
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
    public void execute(State state, InstructionData iData) {
        NameData data = (NameData) iData;

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        state.set(data.getName(), input);
    }
}
