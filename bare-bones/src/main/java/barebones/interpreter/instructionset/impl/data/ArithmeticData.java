package barebones.interpreter.instructionset.impl.data;

import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.operations.ArithmeticOperator;
import barebones.interpreter.instructionset.operations.Operand;
import barebones.interpreter.instructionset.operations.Operator;
import barebones.interpreter.instructionset.operations.VariableOperand;

import java.util.List;

public class ArithmeticData extends InstructionData {
    private final ArithmeticOperator operator;
    private final Operand lhs, rhs;
    private final VariableOperand output;

    public ArithmeticData(ArithmeticOperator operator, Operand lhs, Operand rhs, VariableOperand output) {
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
        this.output = output;
    }

    public ArithmeticOperator getOperator() {
        return operator;
    }

    public Operand getLhs() {
        return lhs;
    }

    public Operand getRhs() {
        return rhs;
    }

    public VariableOperand
    getOutput() {
        return output;
    }
}