package riotgame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kong.unirest.JsonNode;
import riotgame.riotobjects.UserInfo;


public class Handler {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Parse the JsonNode to a local object UserInfo.
     * @param obj the JsonNode that get from service or dummy version.
     * @return the UserInfo parsed from a JsonNode.
     */
    public UserInfo parseUserJson(JsonNode obj) {
        return gson.fromJson(obj.toString(), UserInfo.class);
    }

}
