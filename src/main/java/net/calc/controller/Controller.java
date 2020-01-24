package net.calc.controller;

import net.calc.view.View;

public interface Controller {
    void setView(View view);
    String getDisplay();
    void buttonPressed(String label);
    void shutdown();
}
