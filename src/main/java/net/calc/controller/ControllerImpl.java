package net.calc.controller;

import net.calc.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerImpl implements Controller {
    private static final String MEMORY_CLEAR = "MC";
    private static final String MEMORY_PLUS = "M+";
    private static final String MEMORY_MINUS = "M-";
    private static final String MEMORY_RECALL = "MR";
    private static final String ALL_CLEAR = "AC";
    private static final String PLUS_MINUS = "+/-";
    private static final String ZERO = "0";
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private View view;
    private volatile String display = "0";
    private String memory = "0";

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public void buttonPressed(String label) {
        Matcher digitMatcher = DIGIT.matcher(label);
        if (digitMatcher.matches()) {
            if (display.equals(ZERO) && label.equals(ZERO)) {
                // Do nothing
            } else if(display.equals(ZERO)) {
                display = label;
            } else {
                display += label;
            }
        } else if (ALL_CLEAR.equals(label)) {
            display = "0";
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
    public void shutdown() {
        // This should get called when the window is closed.
    }
}
