package org.esgi.shared_kernel.exceptions;


import org.esgi.shared_kernel.ValueObjectId;

public final class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(String message) {
        super(message);
    }

    public static <VOID extends ValueObjectId> NoSuchEntityException withId(VOID id) {
        return new NoSuchEntityException(String.format("No entity found with ID %s.", id.getValue()));
    }
}
