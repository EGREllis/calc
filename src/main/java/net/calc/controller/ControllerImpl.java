package net.calc.controller;

import net.calc.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerImpl implements Controller {
    private static final String ALL_CLEAR = "AC";
    private static final String PLUS_MINUS = "+/-";
    private static final String ZERO = "0";
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private View view;
    private volatile String display = "0";

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
        }
        view.updateDisplay();
    }
}
