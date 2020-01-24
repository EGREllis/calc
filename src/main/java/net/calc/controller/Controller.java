package net.calc.controller;

import net.calc.view.View;

public interface Controller {
    String getDisplay();
    void setView(View view);
    void buttonPressed(String label);
    void updateDisplay(String label);
    void shutdown();
}
