package net.calc.controller;

import net.calc.model.*;
import net.calc.view.View;

public class ControllerImpl implements Controller {
    private static final String EQUALS = "=";
    private static final String ALL_CLEAR = "AC";
    private View view;
    private Display display;
    private Memory memory;

    private final Model model;

    public ControllerImpl(Model model, Memory memory, Display display) {
        this.model = model;
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
        MathOperation mathOperation = MathOperation.getOperationFromLabel(label);
        MemoryOperation memoryOperation = MemoryOperation.getOperationFrom(label);
        DisplayOperation displayOperation = DisplayOperation.getOperationFrom(label);


        if (EQUALS.equals(label)) {
            model.pushNumber(display.getDisplay());
            display.setDisplay(model.evaluate());
            display.setOperationPerformed(true);
        } else if (ALL_CLEAR.equals(label)) {
            //TODO: Toggle to clear (needs to be investigated)
            display.clear();
            model.clear();
        } else if (displayOperation != null) {
            display.execute(displayOperation);
        } else if (mathOperation != null) {
            if (mathOperation.getOperandCount() > 0) {
                model.pushNumber(display.getDisplay());
            }
            model.pushOperation(mathOperation);
            if (mathOperation.getOperandCount() < 2) {
                display.setDisplay(model.evaluate());
            }
            display.setOperationPerformed(true);
        } else if (memoryOperation != null) {
            if (MemoryOperation.MEMORY_RECALL.equals(memoryOperation)) {
                display.setDisplay(memory.getMemory());
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
