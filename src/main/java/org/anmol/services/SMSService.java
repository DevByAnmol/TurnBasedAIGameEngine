package org.anmol.services;

import org.anmol.commands.implementations.SMSCommand;
import org.anmol.game.User;

public class SMSService {
    private void sendSMS(User user, String message) {
        // mail is sent here
    }

    public void send(SMSCommand command) {
        sendSMS(command.getNotificationDetails().getUser(), command.getNotificationDetails().getMessage());
    }
}
