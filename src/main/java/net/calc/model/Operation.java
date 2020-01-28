package net.calc.model;

import java.util.Deque;

public enum Operation {
    RECALL_E(0, "e", true, false) {
        String calculate() {
            return Double.toString(Math.E);
        }
    },
    RECALL_PI(0, "pi", true, false) {
        String calculate() {
            return Double.toString(Math.PI);
        }
    },
    RECALL_RAND(0, "Rand", true, false) {
        String calculate() {
            return Double.toString(Math.random());
        }
    },

    PERCENTAGE(1, "%", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(first/100);
        }
        String calculateSingleLong(long first) {
            return removeRedundantDecimals(Double.toString(first/100.0));
        }
    },
    LOG_10(1, "log", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.log10(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.log10(first));
        }
    },
    NATURAL_LOG(1, "ln", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.log(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.log(first));
        }
    },
    RECIPROCAL(1, "1/x", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(1/first);
        }
        String calculateSingleLong(long first) {
            return Double.toString(1.0/first);
        }
    },
    FACTORIAL(1, "x!", true, false) {
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
    SINH(1, "sinh", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.sinh(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.sinh(first));
        }
    },
    COSH(1, "cosh", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.cosh(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.cosh(first));
        }
    },
    TANH(1, "tanh", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.tanh(first));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.tanh(first));
        }
    },
    SIN(1, "sin", true, false) {
        String calculateSingleDouble(double first) {
            double radians = convertDegreesToRadians(first);
            return Double.toString(Math.sin(radians));
        }
        String calculateSingleLong(long first) {
            double radians = convertDegreesToRadians(first);
            return Double.toString(Math.sin(radians));
        }
    },
    COS(1, "cos", true, false) {
        String calculateSingleDouble(double first) {
            double radians = convertDegreesToRadians(first);
            return Double.toString(Math.cos(radians));
        }
        String calculateSingleLong(long first) {
            double radians = convertDegreesToRadians(first);
            return Double.toString(Math.cos(radians));
        }
    },
    TAN(1, "tan", true, false) {
        String calculateSingleDouble(double first) {
            double radians = convertDegreesToRadians(first);
            return Double.toString(Math.tan(radians));
        }
        String calculateSingleLong(long first) {
            double radians = convertDegreesToRadians(first);
            return Double.toString(Math.tan(radians));
        }
    },
    ADD(2, "+", true, false) {
        String calculateDouble(double first, double second) {
            return Double.toString(first + second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first + second);
        }
    },
    SUBTRACT(2, "-", true, false) {
        String calculateDouble(double first, double second) {
            return Double.toString(first - second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first - second);
        }
    },
    MULTIPLY(2, "*", true, false) {
        String calculateDouble(double first, double second) {
            return Double.toString(first * second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first * second);
        }
    },
    DIVIDE(2, "/", true, false) {
        String calculateDouble(double first, double second) {
            return Double.toString(first / second);
        }

        String calculateLong(long first, long second) {
            return Long.toString(first / second);
        }
    },
    SQUARE(1, "x^2", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 2));
        }
        String calculateSingleLong(long first) {
            return Long.toString((long)Math.pow(first, 2));
        }
    },
    CUBE(1, "x^3", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 3));
        }
        String calculateSingleLong(long first) {
            return Long.toString((long)Math.pow(first, 3));
        }
    },
    SQUARE_ROOT(1, "x^1/2", true, false) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 0.5));
        }
        String calculateSingleLong(long first) {
            return Double.toString(Math.pow(first, 0.5));
        }
    },
    CUBE_ROOT(1, "x^1/3", true, false) {
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
    private boolean isStackOperation;
    private boolean isMemoryOperation;

    public boolean isStackOperation() {
        return isStackOperation;
    }

    public boolean isMemoryOperation() {
        return isMemoryOperation;
    }

    String calculate() {
        throw new IllegalStateException("Not implemented");
    }

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

    Operation(int operandCount, String label, boolean isStackOperation, boolean isMemoryOperation) {
        this.operandCount = operandCount;
        this.label = label;
        this.isStackOperation = isStackOperation;
        this.isMemoryOperation = isMemoryOperation;
    }

    public String getLabel() {
        return label;
    }

    public int getOperandCount() {
        return operandCount;
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
        } else if (operandCount == 0) {
            numbers.push(calculate());
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

    private static double convertDegreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }
}
