package net.calc.model;

public interface Memory {
    String getMemory();
    void execute(MemoryOperation operation, String display);
}
