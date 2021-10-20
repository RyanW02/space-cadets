package barebones.interpreter.instructionset.impl.data;

import barebones.interpreter.instructionset.InstructionData;

public class NameData extends InstructionData {
    private final String name;

    public NameData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}