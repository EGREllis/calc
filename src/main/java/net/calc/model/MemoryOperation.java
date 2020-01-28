package net.calc.model;

public enum MemoryOperation {
    MEMORY_CLEAR("MC", null),
    MEMORY_ADD("M+", Operation.ADD),
    MEMORY_SUBTRACT("M-", Operation.SUBTRACT),
    MEMORY_RECALL("MR", null);

    String label;
    Operation operation;

    MemoryOperation(String label, Operation op) {
        this.label = label;
        this.operation = op;
    }

    public String getLabel() {
        return label;
    }

    public Operation getOperation() {
        return operation;
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
