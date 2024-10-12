package org.anmol.commands.implementations;

import org.anmol.events.Event;
import org.anmol.game.User;

public class EmailCommand {
    NotificationDetails notificationDetails;
    String link;
    String templateId;
    String templateString;

    public EmailCommand(User user, String message) {
        notificationDetails = new NotificationDetails(user, message);
    }

    public EmailCommand(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    public EmailCommand(Event event) {
        this.notificationDetails = new NotificationDetails(event.getUser(), event.getMessage());
    }

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }
}
