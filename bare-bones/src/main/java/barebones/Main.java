package barebones;

import barebones.interpreter.Parser;
import barebones.interpreter.State;
import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionSet;
import barebones.interpreter.instructionset.InstructionWithData;
import barebones.interpreter.instructionset.impl.Clear;
import barebones.interpreter.instructionset.impl.Increment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read code
        if (args.length == 0) {
            System.err.println("No file specified");
            System.exit(0);
            return;
        }

        String code = readFile(args[0]);

        // Run code
        InstructionSet.INSTANCE.init();

        Parser parser = new Parser(code);
        List<InstructionWithData<?, ?>> instructions = parser.parse();

        State state = new State();
        instructions.forEach((iwd) -> {
            iwd.getInstruction().execute(state, iwd.getData());
        });
    }

    private static String readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        return reader.lines().collect(Collectors.joining("\n"));
    }
}
