package riotgame.model.twilioapi;

public interface TwilioSms {
    /**
     * Send the message to your phone.
     * @param message the message you want to send to your phone.
     */
    void sendMessage(String message);
}
