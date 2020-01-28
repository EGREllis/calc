package net.calc.model;

public enum MemoryOperation {
    MEMORY_CLEAR("MC", null),
    MEMORY_ADD("M+", MathOperation.ADD),
    MEMORY_SUBTRACT("M-", MathOperation.SUBTRACT),
    MEMORY_RECALL("MR", null);

    String label;
    MathOperation mathOperation;

    MemoryOperation(String label, MathOperation op) {
        this.label = label;
        this.mathOperation = op;
    }

    public String getLabel() {
        return label;
    }

    public MathOperation getMathOperation() {
        return mathOperation;
    }

    public static MemoryOperation getOperationFrom(String label) {
        MemoryOperation op = null;
        for (MemoryOperation mem : MemoryOperation.values()) {
            if (mem.label.equals(label)) {
                op = mem;
                break;
            }
        }
        return op;
    }
}
