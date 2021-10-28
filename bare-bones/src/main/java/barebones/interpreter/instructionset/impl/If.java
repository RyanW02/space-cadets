package barebones.interpreter.instructionset.impl;

import barebones.interpreter.ScopeManager;
import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.data.ComparisonData;
import barebones.interpreter.instructionset.operations.ConstantOperand;
import barebones.interpreter.instructionset.operations.Operand;
import barebones.interpreter.instructionset.operations.Operator;
import barebones.interpreter.instructionset.operations.VariableOperand;
import barebones.utils.Utils;

import java.util.List;

public class If extends Instruction<ComparisonData> {
    @Override
    public String getToken() {
        return "if";
    }

    @Override
    public boolean hasBlock() {
        return true;
    }

    @Override
    public ComparisonData parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        Operator operator = Operator.getByToken(tokens[1]);
        Operand lhs, rhs;

        // Parse operands
        if (Utils.isInt(tokens[0])) {
            lhs = new ConstantOperand(Integer.parseInt(tokens[0]));
        } else {
            lhs = new VariableOperand(tokens[0]);
        }

        if (Utils.isInt(tokens[2])) {
            rhs = new ConstantOperand(Integer.parseInt(tokens[2]));
        } else {
            rhs = new VariableOperand(tokens[2]);
        }

        return new ComparisonData(blockInstructions, operator, lhs, rhs);
    }

    @Override
    public void execute(ScopeManager state, InstructionData iData) {
        ComparisonData data = (ComparisonData) iData;

        if (data.getOperator().compare(data.getLhs().getValue(state), data.getRhs().getValue(state))) {
            data.getBlockInstructions().forEach((iwd) -> {
                iwd.getInstruction().execute(state, iwd.getData());
            });
        }
    }
}
