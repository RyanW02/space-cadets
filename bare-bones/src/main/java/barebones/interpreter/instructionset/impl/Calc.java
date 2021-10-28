package barebones.interpreter.instructionset.impl;

import barebones.interpreter.ScopeManager;
import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.data.ArithmeticData;
import barebones.interpreter.instructionset.impl.data.NameData;
import barebones.interpreter.instructionset.operations.ArithmeticOperator;
import barebones.interpreter.instructionset.operations.Operand;
import barebones.interpreter.instructionset.operations.VariableOperand;

import java.util.List;

public class Calc extends Instruction<ArithmeticData> {
    @Override
    public String getToken() {
        return "calc";
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public ArithmeticData parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        VariableOperand output = new VariableOperand(tokens[0]);

        if (!tokens[1].equals("=")) {
            throw new IllegalArgumentException("CALC operation must be in format CALC X = A + B");
        }

        Operand lhs = Operand.parse(tokens[2]);
        ArithmeticOperator operator = ArithmeticOperator.getByToken(tokens[3]);
        Operand rhs = Operand.parse(tokens[4]);

        return new ArithmeticData(operator, lhs, rhs, output);
    }

    @Override
    public void execute(ScopeManager state, InstructionData iData) {
        ArithmeticData data = (ArithmeticData) iData;
        int output = data.getOperator().calculate(data.getLhs().getValue(state), data.getRhs().getValue(state));
        state.set(data.getOutput().getVariableName(), output);
    }
}
