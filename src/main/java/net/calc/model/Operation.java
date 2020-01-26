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
    },
    SQUARE(1) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 2));
        }
        String calculateSingleLong(long first) {
            return Long.toString((long)Math.pow(first, 2));
        }
    },
    CUBE(1) {
        String calculateSingleDouble(double first) {
            return Double.toString(Math.pow(first, 3));
        }
        String calculateSingleLong(long first) {
            return Long.toString((long)Math.pow(first, 3));
        }
    },
    SQUARE_ROOT(1) {
        String calculateSingleDouble(double first) {
            return removeRedundantDecimals(Double.toString(Math.pow(first, 0.5)));
        }
        String calculateSingleLong(long first) {
            return removeRedundantDecimals(Double.toString(Math.pow(first, 0.5)));
        }
    },
    CUBE_ROOT(1) {
        String calculateSingleDouble(double first) {
            return removeRedundantDecimals(Double.toString(Math.pow(first, 1.0/3)));
        }
        String calculateSingleLong(long first) {
            return removeRedundantDecimals(Double.toString(Math.pow(first, 1.0/3)));
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

    Operation(int operandCount) {
        this.operandCount = operandCount;
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
                result = calculateLong(firstLong, secondLong);
            } else {
                result = calculateDouble(Double.parseDouble(first), Double.parseDouble(second));
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
                result = calculateSingleLong(firstLong);
            } else {
                double firstDouble = Double.parseDouble(first);
                result = calculateSingleDouble(firstDouble);
            }
            numbers.push(result);
        } else {
            throw new IllegalStateException("This should never be executed! (operandCount = "+operandCount+")");
        }

    }
}
