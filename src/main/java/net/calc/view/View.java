package net.calc.view;

public interface View {
    void start();
    void updateDisplay();
    void stop();
    String getDisplay();
}
