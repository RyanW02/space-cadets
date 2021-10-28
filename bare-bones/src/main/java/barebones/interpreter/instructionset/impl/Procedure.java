package barebones.interpreter.instructionset.impl;

import barebones.interpreter.ScopeManager;
import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.data.ComparisonData;
import barebones.interpreter.instructionset.impl.data.NameData;
import barebones.interpreter.instructionset.operations.ConstantOperand;
import barebones.interpreter.instructionset.operations.Operand;
import barebones.interpreter.instructionset.operations.Operator;
import barebones.interpreter.instructionset.operations.VariableOperand;
import barebones.utils.Utils;

import java.util.List;

public class Procedure extends Instruction<Procedure.Data> {
    @Override
    public String getToken() {
        return "procedure";
    }

    @Override
    public boolean hasBlock() {
        return true;
    }

    @Override
    public Data parse(String[] tokens, List<InstructionWithData<?, ?>> blockInstructions) {
        return new Data(blockInstructions, tokens[0]);
    }

    @Override
    public void execute(ScopeManager state, InstructionData iData) {
        Data data = (Data) iData;
        state.createProcedure(data);
    }

    public class Data extends InstructionData {
        private final List<InstructionWithData<?, ?>> blockInstructions;
        private final String name;

        public Data(List<InstructionWithData<?, ?>> blockInstructions, String name) {
            this.blockInstructions = blockInstructions;
            this.name = name;
        }

        public void execute(ScopeManager state) {
            getBlockInstructions().forEach((iwd) -> {
                iwd.getInstruction().execute(state, iwd.getData());
            });
        }

        public List<InstructionWithData<?, ?>> getBlockInstructions() {
            return blockInstructions;
        }

        public String getName() {
            return name;
        }
    }
}
