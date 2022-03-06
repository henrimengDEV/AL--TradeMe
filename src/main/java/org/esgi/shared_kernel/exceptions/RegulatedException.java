package org.esgi.shared_kernel.exceptions;


public final class RegulatedException extends RuntimeException {
    private int regulationId;

    public RegulatedException(String message) {
        super(message);
    }
    public RegulatedException(String message, int regulationId) {
        super(message);
        this.regulationId = regulationId;
    }

    public int getRegulationId() {
        return regulationId;
    }
}
