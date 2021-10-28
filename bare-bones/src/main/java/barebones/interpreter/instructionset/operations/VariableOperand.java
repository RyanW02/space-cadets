package barebones.interpreter.instructionset.operations;

import barebones.interpreter.ScopeManager;

public class VariableOperand extends Operand {

    private final String variableName;

    public VariableOperand(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public OperandType getType() {
        return OperandType.Variable;
    }

    @Override
    public int getValue(ScopeManager state) {
        return state.get(variableName);
    }

    public String getVariableName() {
        return variableName;
    }
}
