package riotgame.model.twilioapi;

public class TwilioSmsOffline implements TwilioSms {

    public TwilioSmsOffline() {

    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
