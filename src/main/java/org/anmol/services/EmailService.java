package org.anmol.services;

import org.anmol.commands.implementations.EmailCommand;
import org.anmol.game.User;

public class EmailService {
    private void sendEmail(User user, String message) {
        // mail is sent here
    }

    public Void send(EmailCommand command) {
        sendEmail(command.getNotificationDetails().getUser(), command.getNotificationDetails().getMessage());
        return null;
    }
}
