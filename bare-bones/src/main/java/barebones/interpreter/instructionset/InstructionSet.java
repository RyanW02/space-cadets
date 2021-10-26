package barebones.interpreter.instructionset;

import barebones.interpreter.instructionset.impl.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum InstructionSet {
    INSTANCE;

    public Map<String, Instruction<?>> instructionSet = new HashMap<>();

    public void init() {
        Arrays.asList(
                new Calc(),
                new Call(),
                new Clear(),
                new Copy(),
                new Decrement(),
                new End(),
                new If(),
                new Increment(),
                new Input(),
                new Print(),
                new Procedure(),
                new Quit(),
                new While()
        ).forEach((i) -> instructionSet.put(i.getToken(), i));
    }
}
