package riotgame.model.riotapi;

import kong.unirest.JsonNode;

public interface RiotGame {


    /**
     * Get the matched summoner information from service or dummy version.
     * @param region the region that you choose from region choice box.
     * @param name the username of the users.
     * @return the JsonNode get from service or dummy version.
     */
    JsonNode getSummonerByName(String region, String name);
}
