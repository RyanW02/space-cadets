package barebones.interpreter;

import barebones.interpreter.instructionset.Instruction;
import barebones.interpreter.instructionset.InstructionData;
import barebones.interpreter.instructionset.InstructionSet;
import barebones.interpreter.instructionset.InstructionWithData;

import java.util.*;

public class Parser {
    private final String input;

    public Parser(String input) {
        this.input = input;
    }

    public List<InstructionWithData<?, ?>> parse() {
        Iterator<String> lines = this.input.lines().iterator();
        return parseBlock(lines);
    }

    private List<InstructionWithData<?, ?>> parseBlock(Iterator<String> lines) {
        List<InstructionWithData<?, ?>> instructions = new ArrayList<>();

        while (lines.hasNext()) {
            String line = lines.next();

            // Ignore comments & whitespace
            if (line.startsWith("//") || line.isEmpty()) {
                continue;
            }

            // We don't actually use semicolons
            line = line.trim();
            if (line.endsWith(";")) {
                line = line.substring(0, line.length() - 1);
            }

            String[] tokens = line.split(" ");

            Instruction<?> instruction = getInstruction(tokens[0]);
            if (instruction.getToken().equals("end")) {
                return instructions;
            }

            String[] subTokens = Arrays.copyOfRange(tokens, 1, tokens.length);

            InstructionData data;
            if (!instruction.hasBlock()) {
                data = instruction.parse(subTokens, Collections.emptyList());
            } else {
                data = instruction.parse(subTokens, parseBlock(lines));
            }

            instructions.add(new InstructionWithData(instruction, data));
        }

        return instructions;
    }

    private Instruction<?> getInstruction(String token) {
        return InstructionSet.INSTANCE.instructionSet.get(token);
    }
}
