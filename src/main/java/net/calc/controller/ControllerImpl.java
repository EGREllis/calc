package net.calc.controller;

import net.calc.model.*;
import net.calc.model.Mathematic;
import net.calc.view.View;

public class ControllerImpl implements Controller {
    private View view;
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

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void buttonPressed(String label) {
        MathematicOperation mathematicOperation = MathematicOperation.getOperationFromLabel(label);
        MemoryOperation memoryOperation = MemoryOperation.getOperationFrom(label);
        DisplayOperation displayOperation = DisplayOperation.getOperationFrom(label);
        ControllerOperation controllerOperation = ControllerOperation.getOperationFrom(label);

        if (controllerOperation != null) {
            controllerOperation.evaluate(display, mathematic);
        } else if (displayOperation != null) {
            display.execute(displayOperation);
        } else if (mathematicOperation != null) {
            if (mathematicOperation.getOperandCount() > 0) {
                mathematic.pushNumber(display.getDisplay());
            }
            mathematic.pushOperation(mathematicOperation);
            if (mathematicOperation.getOperandCount() < 2) {
                display.setDisplay(mathematic.evaluate());
            }
            display.setOperationPerformed(true);
        } else if (memoryOperation != null) {
            if (MemoryOperation.MEMORY_RECALL.equals(memoryOperation)) {
                display.setDisplay(memory.getMemory());
                display.setOperationPerformed(true);
            } else {
                memory.execute(memoryOperation, display.getDisplay());
            }
            display.setOperationPerformed(true);
        }
        view.updateDisplay();
    }

    @Override
    public void updateDisplay(String label) {
        view.updateDisplay();
    }

    @Override
    public void shutdown() {
        // This should get called when the window is closed.
    }
}
