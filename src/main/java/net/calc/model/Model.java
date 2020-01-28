package net.calc.model;

import net.calc.controller.Controller;

public interface Model {
    String evaluate();
    String getMemory();
    void pushNumber(String number);
    void pushOperation(MathOperation mathOperation);
    void clear();
    void setController(Controller controller);
}
