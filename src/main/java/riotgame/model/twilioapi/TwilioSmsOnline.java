package riotgame.model.twilioapi;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import riotgame.PopUpWindow;

public class TwilioSmsOnline implements TwilioSms {

    private final String accountSid;
    private final String authToken;
    private final String fromNumber;
    private final String toNumber;

    public TwilioSmsOnline(String accountSid, String authToken, String fromNumber, String toNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
    }

    @Override
    public void sendMessage(String message) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.twilio.com/2010-04-01/Accounts/"+accountSid+"/Messages.json")
                .basicAuth(accountSid, authToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("From", fromNumber)
                .field("To", toNumber)
                .field("Body", message)
                .asJson();

        if(response.getBody().toPrettyString().contains("detail")) {
            PopUpWindow.throwWarning(response.getBody().toPrettyString().replace('{', ' ').replace('}', ' ').replace('"', ' '));
        }
    }
}
