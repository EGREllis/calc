package net.calc.controller;

import net.calc.model.Model;
import net.calc.model.Operation;
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
    private String memory = "0";

    private final Model model;

    public ControllerImpl(Model model) {
        this.model = model;
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
        } else if (Operation.SINH.getLabel().equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.SINH);
            display = model.evaluate();
            isFresh = true;
        } else if (Operation.COSH.getLabel().equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.COSH);
            display = model.evaluate();
            isFresh = true;
        } else if (Operation.TANH.getLabel().equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.TANH);
            display = model.evaluate();
            isFresh = true;
        } else if (EQUALS.equals(label)) {
            model.pushNumber(display);
            display = model.evaluate();
            isFresh = true;
        } else if (Operation.RECALL_PI.getLabel().equals(label)) {
            String text = Double.toString(Math.PI);
            display = text.substring(0, Math.min(text.length(), DEFAULT_LENGTH));
        } else if (Operation.RECALL_RAND.getLabel().equals(label)) {
            String text = Double.toString(Math.random());
            display = text.substring(0, Math.min(text.length(), DEFAULT_LENGTH));
        } else if (Operation.RECALL_E.getLabel().equals(label)) {
            String text = Double.toString(Math.E);
            display = text.substring(0, Math.min(text.length(), DEFAULT_LENGTH));
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
        } else if (Operation.MEMORY_RECALL.getLabel().equals(label)) {
            display = memory;
        } else if (Operation.MEMORY_CLEAR.getLabel().equals(label)) {
            memory = "0";
        } else if (Operation.MEMORY_ADD.getLabel().equals(label)) {
            boolean isDisplayLong = true;
            long displayLong = 0L;
            try {
                displayLong = Long.parseLong(display);
            } catch (NumberFormatException nfe) {
                isDisplayLong = false;
            }
            boolean isMemoryLong = true;
            long memoryLong = 0L;
            try {
                memoryLong = Long.parseLong(memory);
            } catch(NumberFormatException nfe) {
                isMemoryLong = false;
            }

            if (isMemoryLong && isDisplayLong) {
                memory = ""+(memoryLong + displayLong);
            } else {
                double memoryDouble = Double.parseDouble(memory);
                double displayDouble = Double.parseDouble(display);
                memory = "" + (memoryDouble + displayDouble);
            }
        } else if (Operation.MEMORY_SUBTRACT.getLabel().equals(label)) {
            boolean isDisplayLong = true;
            long displayLong = 0L;
            try {
                displayLong = Long.parseLong(display);
            } catch (NumberFormatException nfe) {
                isDisplayLong = false;
            }
            boolean isMemoryLong = true;
            long memoryLong = 0L;
            try {
                memoryLong = Long.parseLong(memory);
            } catch(NumberFormatException nfe) {
                isMemoryLong = false;
            }

            if (isMemoryLong && isDisplayLong) {
                memory = ""+(memoryLong - displayLong);
            } else {
                double memoryDouble = Double.parseDouble(memory);
                double displayDouble = Double.parseDouble(display);
                memory = "" + (memoryDouble - displayDouble);
            }
        } else {
            Operation operation = Operation.getOperationFromLabel(label);
            if (operation.isStackOperation()) {
                model.pushNumber(display);
                model.pushOperation(operation);
                if (operation.getOperandCount() == 1) {
                    display = model.evaluate();
                }
                isFresh = true;
            }
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
