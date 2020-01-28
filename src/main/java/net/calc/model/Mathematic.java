package net.calc.model;

import net.calc.controller.Controller;

public interface Mathematic {
    String evaluate();
    void pushNumber(String number);
    void pushOperation(MathematicOperation mathematicOperation);
    void clear();
    void setController(Controller controller);
}
