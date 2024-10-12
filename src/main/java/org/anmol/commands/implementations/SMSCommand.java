package org.anmol.commands.implementations;

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

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }
}
