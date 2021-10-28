package barebones.interpreter;

import barebones.interpreter.instructionset.impl.Procedure;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ScopeManager {
    private final List<State> scopes = new ArrayList<>();

    public void addState(State state) {
        this.scopes.add(state);
    }

    public void addBlankState() {
        addState(new State());
    }

    public void pop() {
        this.scopes.remove(this.scopes.size() - 1);
    }

    public int get(String name) {
        State state = findState(name);
        if (state != null) {
            return state.get(name);
        } else {
            throw new NoSuchElementException(String.format("Variable %s not found in scope", name));
        }
    }

    public void set(String name, int value) {
        State state = findState(name);
        if (state == null) {
            state = getLastState();
        }

        state.set(name, value);
    }

    public boolean exists(String name) {
        return findState(name) != null;
    }

    public Procedure.Data getProcedure(String name) {
        State state = findState(name);
        if (state != null) {
            return state.getProcedure(name);
        } else {
            throw new NoSuchElementException(String.format("Variable %s not found in scope", name));
        }
    }

    public void createProcedure(Procedure.Data procedure) {
        getLastState().createProcedure(procedure);
    }

    private State getLastState() {
        return this.scopes.get(this.scopes.size() - 1);
    }

    private State findState(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            State scope = scopes.get(i);
            if (scope.exists(name)) {
                return scope;
            }
        }

        return null;
    }
}
