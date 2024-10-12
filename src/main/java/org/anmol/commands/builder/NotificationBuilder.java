package org.anmol.commands.builder;

import org.anmol.commands.implementations.NotificationDetails;
import org.anmol.game.User;

public class NotificationBuilder {
    User user;
    String message;

    public NotificationBuilder user(User user) {
        this.user = user;
        return this;
    }

    public NotificationBuilder message(String message) {
        this.message = message;
        return this;
    }

    public NotificationDetails build() {
        return new NotificationDetails(user, message);
    }

}
