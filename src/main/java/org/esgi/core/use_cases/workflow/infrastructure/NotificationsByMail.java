package org.esgi.core.use_cases.workflow.infrastructure;

import java.util.logging.Logger;
import org.esgi.core.use_cases.member.domain.model.Notifications;

public class NotificationsByMail implements Notifications {

  private static final Logger LOGGER = Logger.getLogger(NotificationsByMail.class.getName());

  @Override
  public void send(String destinataire, String message) {
    LOGGER.info(" Mail sended to " + destinataire + " with content :\n" + message);
  }
}
