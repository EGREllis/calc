package net.calc.model;

import java.util.Deque;

public enum Operation {
    RECALL_E(0, "e"),
    RECALL_PI(0, "pi"),
    RECALL_RAND(0, "Rand"),
    MEMORY_CLEAR(0, "MC"),
    MEMORY_ADD(0, "M+"),
    MEMORY_SUBTRACT(0, "M-"),
    MEMORY_RECALL(0, "MR"),
    FACTORIAL(1, "x!") {
        String calculateSingleDouble(double first) {
            return "Not a number";
        }
        String calculateSingleLong(long first) {
            long result = 1;
            for (long i = 2; i <= first; i++) {
                result *= i;
            }
            return Long.toString(result);
        }
    },
    SINH(1, "sinh") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.sinh(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.sinh(first));
        }
    },
    COSH(1, "cosh") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.cosh(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.cosh(first));
        }
    },
    TANH(1, "tanh") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.tanh(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.tanh(first));
        }
    },
    SIN(1, "sin") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.sin(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.sin(first * 1.0));
        }
    },
    COS(1, "cos") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.cos(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.cos(first*1.0));
        }
    },
    TAN(1, "tan") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.tan(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.tan(first*1.0));
        }
    },
    ADD(2, "+") {
        String calculateDouble(double first, double second) {
            return Double.toString(first + second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first + second);
        }
    },
    SUBTRACT(2, "-") {
        String calculateDouble(double first, double second) {
            return Double.toString(first - second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first - second);
        }
    },
    MULTIPLY(2, "*") {
        String calculateDouble(double first, double second) {
            return Double.toString(first * second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first * second);
        }
    },
    DIVIDE(2, "/") {
        String calculateDouble(double first, double second) {
            return Double.toString(first / second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first / second);
        }
    },
    SQUARE(1, "x^2") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 2));
        }
        String calculateSingleLong(long first) {
            return Long.toString((long)Math.pow(first, 2));
        }
    },
    CUBE(1, "x^3") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 3));
        }
        String calculateSingleLong(long first) {
            return Long.toString((long)Math.pow(first, 3));
        }
    },
    SQUARE_ROOT(1, "x^1/2") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 0.5));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.pow(first, 0.5));
        }
    },
    CUBE_ROOT(1, "x^1/3") {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 1.0/3));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.pow(first, 1.0/3));
        }
    };

    private static String removeRedundantDecimals(String text) {
        boolean isLong = true;
        if (text.contains(".")) {
            int index = text.indexOf('.');
            for (int i = index+1; i < text.length(); i++) {
                if (text.charAt(i) != '0') {
                    isLong = false;
                }
            }
            if (isLong) {
                text = text.substring(0, index);
            }
        }
        return text;
    }

    private int operandCount;
    private String label;

    String calculateLong(long first, long second) {
        throw new IllegalStateException("Not implemented");
    }
    String calculateDouble(double first, double second) {
        throw new IllegalStateException("Not implemented");
    }
    String calculateSingleLong(long first) {
        throw new IllegalStateException("Not implemented");
    }
    String calculateSingleDouble(double first) {
        throw new IllegalStateException("Not implemented");
    }

    Operation(int operandCount, String label) {
        this.operandCount = operandCount;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void evaluate(Deque<String> numbers) {
        if (operandCount == 2) {
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

            String result;
            if (isFirstLong && isSecondLong) {
                result = removeRedundantDecimals(calculateLong(firstLong, secondLong));
            } else {
                result = removeRedundantDecimals(calculateDouble(Double.parseDouble(first), Double.parseDouble(second)));
            }
            numbers.push(result);
        } else if (operandCount == 1) {
            String first = numbers.pop();

            boolean isFirstLong = true;
            long firstLong = 0L;
            try {
                firstLong = Long.parseLong(first);
            } catch (NumberFormatException nfe) {
                isFirstLong = false;
            }
            String result;
            if (isFirstLong) {
                result = removeRedundantDecimals(calculateSingleLong(firstLong));
            } else {
                double firstDouble = Double.parseDouble(first);
                result = removeRedundantDecimals(calculateSingleDouble(firstDouble));
            }
            numbers.push(result);
        } else {
            throw new IllegalStateException("This should never be executed! (operandCount = "+operandCount+")");
        }

    }

    public static Operation getOperationFromLabel(String label) {
        Operation result = null;
        for (Operation op : values()) {
            if (op.getLabel().equals(label)) {
                result = op;
                break;
            }
        }
        return result;
    }
}
