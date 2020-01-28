package net.calc.model;

public enum MemoryOperation {
    MEMORY_CLEAR("MC", null),
    MEMORY_ADD("M+", MathematicOperation.ADD),
    MEMORY_SUBTRACT("M-", MathematicOperation.SUBTRACT),
    MEMORY_RECALL("MR", null);

    String label;
    MathematicOperation mathematicOperation;

    MemoryOperation(String label, MathematicOperation op) {
        this.label = label;
        this.mathematicOperation = op;
    }

    public String getLabel() {
        return label;
    }

    public MathematicOperation getMathematicOperation() {
        return mathematicOperation;
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
