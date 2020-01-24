package net.calc.model;

import net.calc.controller.Controller;

import java.util.Deque;
import java.util.LinkedList;

public class ModelImpl implements Model {
    private Controller controller;
    private Deque<String> numbers = new LinkedList<>();
    private Operation operation = null;

    @Override
    public String evaluate() {
        this.operation.evaluate(numbers);
        operation = null;
        return numbers.pop();
    }

    @Override
    public void pushNumber(String number) {
        numbers.push(number);
    }

    @Override
    public void pushOperation(Operation operation) {
        if (this.operation == null) {
            this.operation = operation;
        } else {
            this.operation.evaluate(numbers);
            this.operation = operation;
            controller.updateDisplay(numbers.peek());
        }
    }

    @Override
    public void clear() {
        numbers.clear();
        operation = null;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
