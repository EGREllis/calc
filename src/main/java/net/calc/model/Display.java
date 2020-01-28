package net.calc.model;

public interface Display {
    String getDisplay();
    void setDisplay(String display);
    void execute(DisplayOperation operation);
    void clear();
    void setOperationPerformed(boolean performed);
    boolean isOperationPerformed();
}
