package net.calc.controller;

import net.calc.model.*;
import net.calc.model.Mathematic;

public class ControllerImpl implements Controller {
    private Display display;
    private Memory memory;

    private final Mathematic mathematic;

    public ControllerImpl(Mathematic mathematic, Memory memory, Display display) {
        this.mathematic = mathematic;
        this.memory = memory;
        this.display = display;
    }

    @Override
    public String getDisplay() {
        return display.getDisplay();
    }

    @Override
    public String buttonPressed(String label) {
        MathematicOperation mathematicOperation = MathematicOperation.getOperationFromLabel(label);
        MemoryOperation memoryOperation = MemoryOperation.getOperationFrom(label);
        DisplayOperation displayOperation = DisplayOperation.getOperationFrom(label);
        ControllerOperation controllerOperation = ControllerOperation.getOperationFrom(label);

        if (controllerOperation != null) {
            controllerOperation.evaluate(display, mathematic);
        } else if (displayOperation != null) {
            display.execute(displayOperation);
        } else if (mathematicOperation != null) {
            if (mathematicOperation.isCalculable()) {
                if (mathematicOperation.getOperandCount() > 0) {
                    mathematic.pushNumber(display.getDisplay());
                }
                mathematic.pushOperation(mathematicOperation);
                if (mathematicOperation.getOperandCount() < 2) {
                    display.setDisplay(mathematic.evaluate());
                }
                display.setOperationPerformed(true);
            } else {
                mathematic.pushOperation(mathematicOperation);
            }
        } else if (memoryOperation != null) {
            if (MemoryOperation.MEMORY_RECALL.equals(memoryOperation)) {
                display.setDisplay(memory.getMemory());
                display.setOperationPerformed(true);
            } else {
                memory.execute(memoryOperation, display.getDisplay());
            }
            display.setOperationPerformed(true);
        }
        return display.getDisplay();
    }
}
