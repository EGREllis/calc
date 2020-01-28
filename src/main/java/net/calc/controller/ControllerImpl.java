package net.calc.controller;

import net.calc.model.Memory;
import net.calc.model.MemoryOperation;
import net.calc.model.Model;
import net.calc.model.MathOperation;
import net.calc.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerImpl implements Controller {
    private static final int DEFAULT_LENGTH = 17;
    private static final String EQUALS = "=";
    private static final String DECIMAL = ".";
    private static final String ALL_CLEAR = "AC";
    private static final String PLUS_MINUS = "+/-";
    private static final String ZERO = "0";
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private boolean isFresh = true;
    private View view;
    private volatile String display = "0";
    private Memory memory;

    private final Model model;

    public ControllerImpl(Model model, Memory memory) {
        this.model = model;
        this.memory = memory;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void buttonPressed(String label) {
        MathOperation mathOperation = MathOperation.getOperationFromLabel(label);
        MemoryOperation memoryOperation = MemoryOperation.getOperationFrom(label);

        Matcher digitMatcher = DIGIT.matcher(label);
        if (digitMatcher.matches()) {
            if (isFresh) {
                display = label;
                isFresh = false;
            } else {
                if (display.equals(ZERO)) {
                    display = label;
                } else {
                    display += label;
                }
            }
        } else if (DECIMAL.equals(label)) {
            if (display.contains(".")) {
                //TODO: Add warning beep!
            } else {
                display = display + ".";
            }
        } else if (EQUALS.equals(label)) {
            model.pushNumber(display);
            display = model.evaluate();
            isFresh = true;
        } else if (ALL_CLEAR.equals(label)) {
            //TODO: Toggle to clear (needs to be investigated)
            display = "0";
            model.clear();
        } else if (PLUS_MINUS.equals(label)) {
            if (display.equals(ZERO)) {
                // Do nothing
            } else if (display.charAt(0) == '-') {
                display = display.substring(1);
            } else {
                display = "-"+display;
            }
        } else if (mathOperation != null) {
            if (mathOperation.getOperandCount() > 0) {
                model.pushNumber(display);
            }
            model.pushOperation(mathOperation);
            if (mathOperation.getOperandCount() < 2) {
                display = model.evaluate();
            }
            isFresh = true;
        } else if (memoryOperation != null) {
            if (MemoryOperation.MEMORY_RECALL.equals(memoryOperation)) {
                display = memory.getMemory();
            } else {
                memory.execute(memoryOperation, display);
            }
            isFresh = true;
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
