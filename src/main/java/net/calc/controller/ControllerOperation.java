package net.calc.controller;

import net.calc.model.Display;
import net.calc.model.Mathematic;

public enum ControllerOperation {
    ALL_CLEAR("AC") {
        public void evaluate(Display display, Mathematic mathematic) {
            //TODO: Mac calculator turns to C after AC pressed...
            display.clear();
            mathematic.clear();
        }
    },
    EQUALS("=") {
        public void evaluate(Display display, Mathematic mathematic) {
            mathematic.pushNumber(display.getDisplay());
            display.setDisplay(mathematic.evaluate());
            display.setOperationPerformed(true);
        }
    };

    private String label;

    ControllerOperation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void evaluate(Display display, Mathematic mathematic) {
        throw new IllegalStateException("This must be overridden!");
    }

    public static ControllerOperation getOperationFrom(String label) {
        ControllerOperation op = null;
        for (ControllerOperation cont : ControllerOperation.values()) {
            if (cont.label.equals(label)) {
                op = cont;
                break;
            }
        }
        return op;
    }
}
