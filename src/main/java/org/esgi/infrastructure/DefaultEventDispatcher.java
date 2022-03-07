package org.esgi.infrastructure;


import java.util.List;
import java.util.Map;
import org.esgi.kernel.event.Event;
import org.esgi.kernel.event.EventDispatcher;
import org.esgi.kernel.event.EventListener;


public class DefaultEventDispatcher<E extends Event> implements EventDispatcher<E> {

  private final Map<Class<E>, List<EventListener<E>>> eventListenersMap;

  public DefaultEventDispatcher(Map<Class<E>, List<EventListener<E>>> eventListenersMap) {
    this.eventListenersMap = eventListenersMap;
  }

  @Override
  public void dispatch(E event) {
    final List<EventListener<E>> eventListeners = eventListenersMap.get(event.getClass());
    if (eventListeners != null) {
      eventListeners.forEach(eEventListener -> eEventListener.listenTo(event));
    }
  }
}
