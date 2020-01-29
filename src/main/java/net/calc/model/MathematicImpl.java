package net.calc.model;

import java.util.Deque;
import java.util.LinkedList;

public class MathematicImpl implements Mathematic {
    private Deque<String> numbers = new LinkedList<>();
    private Deque<MathematicOperation> mathematicOperations = new LinkedList<>();

    @Override
    public String evaluate() {
        MathematicOperation operation = mathematicOperations.pop();
        operation.evaluate(numbers);
        return numbers.pop();
    }

    @Override
    public void pushNumber(String number) {
        numbers.push(number);
    }

    @Override
    public void pushOperation(MathematicOperation mathematicOperation) {
        if (mathematicOperation.isStackable()) {
            mathematicOperations.push(mathematicOperation);
        }
    }

    @Override
    public void clear() {
        numbers.clear();
        mathematicOperations.clear();
    }
}
