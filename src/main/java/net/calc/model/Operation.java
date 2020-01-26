package net.calc.model;

import java.util.Deque;

public enum Operation {
    ADD {
        String calculateDouble(double first, double second) {
            return Double.toString(first + second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first + second);
        }
    },
    SUBTRACT {
        String calculateDouble(double first, double second) {
            return Double.toString(first - second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first - second);
        }
    },
    MULTIPLY {
        String calculateDouble(double first, double second) {
            return Double.toString(first * second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first * second);
        }
    },
    DIVIDE {
        String calculateDouble(double first, double second) {
            return Double.toString(first / second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first / second);
        }
    };

    abstract String calculateLong(long first, long second);
    abstract String calculateDouble(double first, double second);

    public void evaluate(Deque<String> numbers) {
        String second = numbers.pop();
        String first = numbers.pop();

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
