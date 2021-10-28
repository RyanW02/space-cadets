package barebones.interpreter;

import barebones.interpreter.instructionset.impl.Procedure;

import java.util.HashMap;
import java.util.Map;

public class State {
    private Map<String, Integer> memory = new HashMap<String, Integer>();
    private Map<String, Procedure.Data> procedures = new HashMap<>();

    public Integer get(String name) {
        return memory.get(name);
    }

    public void set(String name, int value) {
        memory.put(name, value);
    }

    public boolean exists(String name) {
        return memory.containsKey(name);
    }

    public Procedure.Data getProcedure(String name) {
        return procedures.get(name);
    }

    public void createProcedure(Procedure.Data procedure) {
        procedures.put(procedure.getName(), procedure);
    }
}
