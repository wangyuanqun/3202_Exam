package riotgame;

import java.sql.*;
import riotgame.riotobjects.UserInfo;

public class ConnectDatabase {

    private static ConnectDatabase base;

    private Connection connection;

    /**
     * Initialize the database and creating corresponding tables.
     */
    private ConnectDatabase() {

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/Database.db");
            Statement statement = connection.createStatement();

            //create table for summoner info
            statement.executeUpdate("create table if not exists summonerInfo (id string, accountId string, puuid string, name string," +
                    "profileIconId integer, revisionDate long, summonerLevel integer)");

            //create table for league entry
            statement.executeUpdate(
          "create table if not exists leagueEntry (leagueId string, queueType string, tier string, rank string, summonerId string," +
                  "summonerName string, leaguePoints integer, wins1 integer, losses1 integer, veteran boolean, inactive boolean," +
                  "freshBlood boolean, hotStreak boolean, target integer, wins2 integer, losses2 integer, progress string)");

        } catch (SQLException e) {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
    }

    /**
     * Initializing the database.
     */
    public static void init() {
        base = new ConnectDatabase();
    }

    /**
     * Get the database.
     * @return initialized database.
     */
    public static ConnectDatabase getDatabase() {
        return base;
    }

    /**
     * Write the User Information to database.
     * @param info the user information
     */
    public void writeSummonerInfo(UserInfo info) {
        if(info == null) {
            return;
        }
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into summonerInfo(id, accountId," +
                    "puuid, name, profileIconId, revisionDate, summonerLevel) values(?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, info.getId());
            preparedStatement.setString(2, info.getAccountId());
            preparedStatement.setString(3, info.getPuuid());
            preparedStatement.setString(4, info.getName());
            preparedStatement.setInt(5, info.getProfileIconId());
            preparedStatement.setLong(6, info.getRevisionDate());
            preparedStatement.setInt(7, info.getSummonerLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Get the matched user information from database.
     * @param name the user name that used to find the matched information.
     * @return the UserInfo found in database.
     */
    public UserInfo getSummonerInfo(String name) {
        UserInfo info = null;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from summonerInfo");
            while(resultSet.next()) {
                if(resultSet.getString("name").equals(name)) {
                    info = new UserInfo(resultSet.getString("id"), resultSet.getString("accountId"), resultSet.getString("puuid"),
                            resultSet.getString("name"), resultSet.getInt("profileIconId"), resultSet.getLong("revisionDate"),
                            resultSet.getInt("summonerLevel"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return info;
    }

    /**
     * Update the database with user information.
     * @param info the user information used to update the database.
     */
    public void updateSummonerInfo(UserInfo info) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("update summonerInfo set accountId = ?, "
                    +"puuid = ?, "
                    +"name = ?, "
                    +"profileIconId = ?, "
                    +"revisionDate = ?, "
                    +"summonerLevel = ? "
                    +"where id = ?");
            preparedStatement.setString(1, info.getAccountId());
            preparedStatement.setString(2, info.getPuuid());
            preparedStatement.setString(3, info.getName());
            preparedStatement.setInt(4, info.getProfileIconId());
            preparedStatement.setLong(5, info.getRevisionDate());
            preparedStatement.setInt(6, info.getSummonerLevel());
            preparedStatement.setString(7, info.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * To clear the database (Note uncomment the code in stop() in RiotGameApp)
     */
    public static void clearTable() {
        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/Database.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table if exists summonerInfo");
            statement.executeUpdate("drop table if exists leagueEntry");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}