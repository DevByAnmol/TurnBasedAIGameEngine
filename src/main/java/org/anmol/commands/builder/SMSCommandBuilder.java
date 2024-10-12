package org.anmol.commands.builder;

import org.anmol.commands.implementations.SMSCommand;
import org.anmol.game.User;

public class SMSCommandBuilder {
    NotificationBuilder notificationBuilder;
    String link;
    String templateId;
    String templateString;

    public SMSCommand build() {
        return new SMSCommand(notificationBuilder.build());
    }

    public SMSCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }

    public SMSCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }
}
