package org.anmol.services;

import org.anmol.commands.implementations.SMSCommand;
import org.anmol.game.User;

public class SMSService {
    private void sendSMS(User user, String message) {
        // mail is sent here
    }

    public Void send(SMSCommand command) {
        sendSMS(command.getUser(), command.getMessage());
        return null;
    }
}
