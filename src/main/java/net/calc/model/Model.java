package net.calc.model;

import net.calc.controller.Controller;

public interface Model {
    String evaluate();
    void pushNumber(String number);
    void pushOperation(Operation operation);
    void clear();
    void setController(Controller controller);
}
