package net.calc.model;

public interface Mathematic {
    String evaluate();
    void pushNumber(String number);
    void pushOperation(MathematicOperation mathematicOperation);
    void clear();
}
