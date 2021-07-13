package riotgame.riotobjects;

/**
 * This class is used to parse the JsonNode get from riot api.
 */
public class UserInfo {

    private final String id;
    private final String accountId;
    private final String puuid;
    private final String name;
    private final int profileIconId;
    private final long revisionDate;
    private final int summonerLevel;

    public UserInfo(String id, String accountId, String puuid, String name, int profileIconId, long revisionDate, int summonerLevel) {
        this.id = id;
        this.accountId = accountId;
        this.puuid = puuid;
        this.name = name;
        this.profileIconId =profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public String getName() {
        return name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }
}
