package org.anmol.commands.implementations;

import org.anmol.events.Event;
import org.anmol.game.User;

public class SMSCommand {
    NotificationDetails notificationDetails;
    String link;
    String templateId;
    String templateString;

    public SMSCommand(User user, String message) {
        notificationDetails = new NotificationDetails(user, message);
    }

    public SMSCommand(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    public SMSCommand(Event event) {
        this.notificationDetails = new NotificationDetails(event.getUser(), event.getMessage());
    }

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }

    public User getUser() {
        return notificationDetails.getUser();
    }

    public String getMessage() {
        return notificationDetails.getMessage();
    }
}
