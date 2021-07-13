package riotgame.model.riotapi;

import kong.unirest.JsonNode;
import riotgame.model.riotapi.RiotGame;

public class RiotGameOffline implements RiotGame {

    @Override
    public JsonNode getSummonerByName(String region, String name) {
    return new JsonNode(
        "{\n"
            + "    \"id\": \"OkDB-hlKjxiGBoqTFaHb9KS9_wjfSI7gXbG7hgP9lWpq-t8\",\n"
            + "    \"accountId\": \"QOEAA0klFt9uvKZJPJckEjdLsu1moc-Ojojl0CZOxhL__Ew3WFFjJ5Ju\",\n"
            + "    \"puuid\": \"YH4UnOulfZV7Ziupe_CQ5XwYA9qOBpcEPULoOFRUgky2veBYIDCjy_05FG3U19m5UIzaX_-pSR6yVw\",\n"
            + "    \"name\": \"heniaodaren\",\n"
            + "    \"profileIconId\": 3175,\n"
            + "    \"revisionDate\": 1596174391000,\n"
            + "    \"summonerLevel\": 10\n"
            + "}");
    }

}
