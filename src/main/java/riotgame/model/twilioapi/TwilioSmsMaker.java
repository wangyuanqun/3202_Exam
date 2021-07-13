package riotgame.model.twilioapi;

public class TwilioSmsMaker {

    private static TwilioSmsMaker maker;

    private final TwilioSms sms;

    private TwilioSmsMaker(TwilioSms sms) {
        this.sms = sms;
    }

    /**
     * Get the initialized TwilioSmsMaker.
     * @return TwilioSmsMaker.
     */
    public static TwilioSmsMaker getMaker() {
        return maker;
    }

    /**
     *  Initialize the TwilioSmsMaker.
     * @param twilioSms the TwilioSms of twilio api or the dummy
     */
    public static void init(TwilioSms twilioSms) {
        maker = new TwilioSmsMaker(twilioSms);
    }

    /**
     * Send the message to your phone.
     * @param message the message that you want to send to your phone.
     */
    public void sendMessage(String message) {
        sms.sendMessage(message);
    }
}
