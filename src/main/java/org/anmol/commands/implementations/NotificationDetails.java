package org.anmol.commands.implementations;

import org.anmol.game.User;

public class NotificationDetails {
    User user;
    String message;

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public NotificationDetails(User user, String message) {
        this.user = user;
        this.message = message;
    }
}
