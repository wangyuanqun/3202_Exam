package riotgame.model.riotapi;

import kong.unirest.JsonNode;
import riotgame.Handler;
import riotgame.PopUpWindow;
import riotgame.riotobjects.UserInfo;

public class RiotGameMaker {

    private static RiotGameMaker maker;

    private final Handler handler;
    private final RiotGame riot;

    private RiotGameMaker(Handler handler, RiotGame riot) {
        this.handler = handler;
        this.riot = riot;
    }

    /**
     * Get the maker.
     * @return the initialized RiotGameMaker
     */
    public static RiotGameMaker getMaker() {
        return maker;
    }

    /**
     * Initialize the RiotGameMaker.
     * @param handler the handler used to parse the JsonNode
     * @param riot the RiotGame of riot api or the dummy
     */
    public static void init(Handler handler, RiotGame riot) {
        maker = new RiotGameMaker(handler, riot);
    }

    /**
     * Get the local object UserInfo parsed from a JsonNode.
     * @param region the region that you choose from region choice box.
     * @param name the username of the users.
     * @return UserInfo parsed from an JsonNode.
     */
    public UserInfo getSummonerByName(String region, String name) {
        JsonNode obj = riot.getSummonerByName(region, name);
        if(obj.toPrettyString().contains("status")) {
            PopUpWindow.throwWarning(obj);
            return null;
        }
        return handler.parseUserJson(obj);
    }
}
