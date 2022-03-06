package org.esgi.shared_kernel.event;

public interface EventListener<E extends Event> {
    void listenTo(E event);
}
