package net.calc.model;

import net.calc.controller.Controller;

import java.util.Deque;
import java.util.LinkedList;

public class ModelImpl implements Model {
    private Controller controller;
    private Deque<String> numbers = new LinkedList<>();
    private MathOperation mathOperation = null;
    private String memory = "0";

    @Override
    public String evaluate() {
        this.mathOperation.evaluate(numbers);
        mathOperation = null;
        return numbers.pop();
    }

    @Override
    public String getMemory() {
        return memory;
    }

    @Override
    public void pushNumber(String number) {
        numbers.push(number);
    }

    @Override
    public void pushOperation(MathOperation mathOperation) {
        if (this.mathOperation == null) {
            this.mathOperation = mathOperation;
        } else {
            this.mathOperation.evaluate(numbers);
            this.mathOperation = mathOperation;
            controller.updateDisplay(numbers.peek());
        }
    }

    @Override
    public void clear() {
        numbers.clear();
        mathOperation = null;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
