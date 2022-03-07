package org.esgi.core.use_cases.education_certificate.application.command;

import org.esgi.kernel.cqs.CommandHandler;

public class MyCommandHandler implements CommandHandler<MyCommand, Void> {

  @Override
  public Void handle(MyCommand command) {
    return null;
  }
}
