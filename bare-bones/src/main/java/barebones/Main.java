package barebones;

import barebones.interpreter.Parser;
import barebones.interpreter.ScopeManager;
import barebones.interpreter.instructionset.InstructionSet;
import barebones.interpreter.instructionset.InstructionWithData;

import java.io.BufferedReader;
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

        ScopeManager scope = new ScopeManager();
        scope.addBlankState();

        instructions.forEach((iwd) -> {
            boolean newScope = iwd.getInstruction().hasBlock();
            if (newScope) {
                scope.addBlankState();
            }

            iwd.getInstruction().execute(scope, iwd.getData());

            if (newScope) {
                scope.pop();
            }
        });
    }

    private static String readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        return reader.lines().collect(Collectors.joining("\n"));
    }
}
