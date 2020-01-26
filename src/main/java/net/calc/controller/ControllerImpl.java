package net.calc.controller;

import net.calc.model.Model;
import net.calc.model.Operation;
import net.calc.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerImpl implements Controller {
    private static final int DEFAULT_LENGTH = 17;
    private static final String SQUARE = "x^2";
    private static final String CUBE = "x^3";
    private static final String SQUARE_ROOT = "x^1/2";
    private static final String CUBE_ROOT = "x^1/3";
    private static final String EQUALS = "=";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String TIMES = "*";
    private static final String DIVIDE = "/";
    private static final String E = "e";
    private static final String RAND = "Rand";
    private static final String PI = "pi";
    private static final String DECIMAL = ".";
    private static final String MEMORY_CLEAR = "MC";
    private static final String MEMORY_PLUS = "M+";
    private static final String MEMORY_MINUS = "M-";
    private static final String MEMORY_RECALL = "MR";
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
        } else if (SQUARE.equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.SQUARE);
            display = model.evaluate();
            isFresh = true;
        } else if (CUBE.equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.CUBE);
            display = model.evaluate();
            isFresh = true;
        } else if (SQUARE_ROOT.equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.SQUARE_ROOT);
            display = model.evaluate();
            isFresh = true;
        } else if (CUBE_ROOT.equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.CUBE_ROOT);
            display = model.evaluate();
            isFresh = true;
        } else if (PLUS.equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.ADD);
            isFresh = true;
        } else if (MINUS.equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.SUBTRACT);
            isFresh = true;
        } else if (TIMES.equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.MULTIPLY);
            isFresh = true;
        } else if (DIVIDE.equals(label)) {
            model.pushNumber(display);
            model.pushOperation(Operation.DIVIDE);
            isFresh = true;
        } else if (EQUALS.equals(label)) {
            model.pushNumber(display);
            display = model.evaluate();
            isFresh = true;
        } else if (PI.equals(label)) {
            String text = Double.toString(Math.PI);
            display = text.substring(0, Math.min(text.length(), DEFAULT_LENGTH));
        } else if (RAND.equals(label)) {
            String text = Double.toString(Math.random());
            display = text.substring(0, Math.min(text.length(), DEFAULT_LENGTH));
        } else if (E.equals(label)) {
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
        } else if (MEMORY_RECALL.equals(label)) {
            display = memory;
        } else if (MEMORY_CLEAR.equals(label)) {
            memory = "0";
        } else if (MEMORY_PLUS.equals(label)) {
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
        } else if (MEMORY_MINUS.equals(label)) {
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
