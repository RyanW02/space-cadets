package barebones.interpreter.instructionset;

public class InstructionWithData<T extends Instruction<U>, U extends InstructionData> {
    private final T instruction;
    private final U data;

    public InstructionWithData(T instruction, U data) {
        this.instruction = instruction;
        this.data = data;
    }

    public T getInstruction() {
        return instruction;
    }

    public U getData() {
        return data;
    }
}
