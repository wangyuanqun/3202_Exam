package riotgame.model.riotapi;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import riotgame.model.riotapi.RiotGame;

public class RiotGameOnline implements RiotGame {

    private final String token;

    public RiotGameOnline(String token) {
        this.token = token;
    }

    @Override
    public JsonNode getSummonerByName(String region, String name) {
        HttpResponse<JsonNode> response = Unirest.get("https://"+region+".api.riotgames.com//lol/summoner/v4/summoners/by-name/{summorName}")
                .header("X-Riot-Token", token)
                .routeParam("summorName", name)
                .asJson();
        return response.getBody();
    }
}
