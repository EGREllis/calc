package net.calc.model;

public class DisplayImpl implements Display {
    private String display = "0";
    private boolean isOperationPerformed = true;

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public void execute(DisplayOperation operation) {
        operation.evaluate(this);
    }

    @Override
    public void clear() {
        display = "0";
        isOperationPerformed = true;
    }

    @Override
    public void setOperationPerformed(boolean performed) {
        this.isOperationPerformed = performed;
    }

    @Override
    public boolean isOperationPerformed() {
        return isOperationPerformed;
    }
}
