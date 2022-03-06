package org.esgi.shared_kernel.event;

public interface EventDispatcher<E extends Event> {
    void dispatch(E event);
}
