package net.calc.controller;

import net.calc.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerImpl implements Controller {
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
        }
        view.updateDisplay();
    }
}
