package net.calc.model;

public enum DisplayOperation {
    CLEAR("C", false),
    POINT(".", false) {
        public void evaluate(Display display) {
            String text = display.getDisplay();
            if (text.contains(".")) {
                //TODO: Add warning beep!
            } else {
                display.setDisplay(display.getDisplay()+".");
                display.setOperationPerformed(false);
            }
        }
    },
    PLUS_MINUS("+/-", false) {
        public void evaluate(Display display) {
            String text = display.getDisplay();
            if (text.equals(ZERO.getLabel())) {
                // Do nothing
            } else if (text.charAt(0) == '-') {
                display.setDisplay(text.substring(1));
            } else {
                display.setDisplay("-" + display.getDisplay());
            }
        }
    },
    ZERO("0", true),
    ONE("1", true),
    TWO("2", true),
    THREE("3", true),
    FOUR("4", true),
    FIVE("5", true),
    SIX("6", true),
    SEVEN("7", true),
    EIGHT("8", true),
    NINE("9", true);

    private String label;
    private boolean isDigit;

    DisplayOperation(String label, boolean isDigit) {
        this.label = label;
        this.isDigit = isDigit;
    }

    public String getLabel() {
        return label;
    }

    public void evaluate(Display display) {
        if (isDigit) {
            if (display.isOperationPerformed()) {
                display.setDisplay(label);
                display.setOperationPerformed(false);
            } else {
                if (display.equals(ZERO.getLabel())) {
                    display.setDisplay(label);
                } else {
                    String text = display.getDisplay();
                    display.setDisplay(text+label);
                }
            }
        } else {
            throw new IllegalStateException("This should only be overridden!");
        }
    }

    public static DisplayOperation getOperationFrom(String label) {
        DisplayOperation op = null;
        for (DisplayOperation disp : DisplayOperation.values()) {
            if (disp.getLabel().equals(label)) {
                op = disp;
                break;
            }
        }
        return op;
    }
}
