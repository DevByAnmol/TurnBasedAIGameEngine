package org.anmol.commands.builder;

import org.anmol.commands.implementations.EmailCommand;
import org.anmol.game.User;

public class EmailCommandBuilder {
    NotificationBuilder notificationBuilder;
    String link;
    String templateId;
    String templateString;

    public EmailCommand build() {
        return new EmailCommand(notificationBuilder.build());
    }

    public EmailCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }

    public EmailCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }
}
