package net.calc.model;

import net.calc.controller.Controller;

import java.util.Deque;
import java.util.LinkedList;

public class MathematicImpl implements Mathematic {
    private Controller controller;
    private Deque<String> numbers = new LinkedList<>();
    private MathematicOperation mathematicOperation = null;

    @Override
    public String evaluate() {
        this.mathematicOperation.evaluate(numbers);
        mathematicOperation = null;
        return numbers.pop();
    }

    @Override
    public void pushNumber(String number) {
        numbers.push(number);
    }

    @Override
    public void pushOperation(MathematicOperation mathematicOperation) {
        if (this.mathematicOperation == null) {
            this.mathematicOperation = mathematicOperation;
        } else {
            this.mathematicOperation.evaluate(numbers);
            this.mathematicOperation = mathematicOperation;
            controller.updateDisplay(numbers.peek());
        }
    }

    @Override
    public void clear() {
        numbers.clear();
        mathematicOperation = null;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
