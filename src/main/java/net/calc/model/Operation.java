package net.calc.model;

import java.util.Deque;

public enum Operation {
    ADD(2) {
        String calculateDouble(double first, double second) {
            return Double.toString(first + second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first + second);
        }
    },
    SUBTRACT(2) {
        String calculateDouble(double first, double second) {
            return Double.toString(first - second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first - second);
        }
    },
    MULTIPLY(2) {
        String calculateDouble(double first, double second) {
            return Double.toString(first * second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first * second);
        }
    },
    DIVIDE(2) {
        String calculateDouble(double first, double second) {
            return Double.toString(first / second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first / second);
        }
    };

    Operation(int operationCount) {
        this.operationCount = operationCount;
    }

    private final int operationCount;

    public int getOperandCount() {
        return operationCount;
    }

    abstract String calculateLong(long first, long second);
    abstract String calculateDouble(double first, double second);

    public void evaluate(Deque<String> numbers) {
        String first = numbers.pop();
        String second = numbers.pop();

        boolean isFirstLong = true;
        long firstLong = 0L;
        try {
            firstLong = Long.parseLong(first);
        } catch (NumberFormatException nfe) {
            isFirstLong = false;
        }

        boolean isSecondLong = true;
        long secondLong = 0L;
        try {
            secondLong = Long.parseLong(second);
        } catch (NumberFormatException nfe) {
            isSecondLong = false;
        }

        String result = null;
        if (isFirstLong && isSecondLong) {
            result = calculateLong(firstLong, secondLong);
        } else {
            result = calculateDouble(Double.parseDouble(first), Double.parseDouble(second));
        }
        numbers.push(result);
    }
}
