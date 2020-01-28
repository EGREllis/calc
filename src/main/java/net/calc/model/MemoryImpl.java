package net.calc.model;

import java.util.Deque;
import java.util.LinkedList;

public class MemoryImpl implements Memory {
    private String memory = "0";

    @Override
    public String getMemory() {
        return memory;
    }

    @Override
    public void execute(MemoryOperation operation, String display) {
        switch (operation) {
            case MEMORY_CLEAR:
                memory = "0";
                break;
            case MEMORY_ADD:
            case MEMORY_SUBTRACT:
                Deque<String> stack = new LinkedList<>();
                stack.add(display);
                stack.add(memory);
                operation.getMathematicOperation().evaluate(stack);
                memory = stack.pop();
                break;
            case MEMORY_RECALL:
                throw new IllegalStateException("This should never be called");
            default:
                throw new IllegalStateException("This shoudl never be called");
        }
    }
}
