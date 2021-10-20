package barebones.interpreter.instructionset.impl.data;

import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.operations.Operand;
import barebones.interpreter.instructionset.operations.Operator;

import java.util.List;

public class ComparisonData extends InstructionData {
    private final List<InstructionWithData<?, ?>> blockInstructions;
    private final Operator operator;
    private final Operand lhs, rhs;

    public ComparisonData(List<InstructionWithData<?, ?>> blockInstructions, Operator operator, Operand lhs, Operand rhs) {
        this.blockInstructions = blockInstructions;
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public List<InstructionWithData<?, ?>> getBlockInstructions() {
        return blockInstructions;
    }

    public Operator getOperator() {
        return operator;
    }

    public Operand getLhs() {
        return lhs;
    }

    public Operand getRhs() {
        return rhs;
    }
}