package riotgame.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import riotgame.ConnectDatabase;
import riotgame.PopUpWindow;
import riotgame.model.riotapi.RiotGameMaker;
import riotgame.model.twilioapi.TwilioSmsMaker;
import riotgame.riotobjects.UserInfo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SummonerInfoController {

    private final RiotGameMaker maker = RiotGameMaker.getMaker();
    private final TwilioSmsMaker SmsMaker = TwilioSmsMaker.getMaker();
    private final ConnectDatabase database = ConnectDatabase.getDatabase();

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private TextField username;

    @FXML
    private Button summonerOk;

    @FXML
    private TextField accountId;
    @FXML
    private TextField iconId;
    @FXML
    private TextField date;
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField puuid;
    @FXML
    private TextField level;

    @FXML
    private Button report;

    private UserInfo info;

    private LocalDateTime ld;

    private boolean stale;

    /**
     *  This is the initialization of this class.
     */
    public void initialize() {
        new Thread(this::setChoiceBox).start();
        setButtonAction();
    }

    /**
     * Creating actions on buttons:
     *
     * summonerOk button is to decide whether the user wants to load data from local or from service,
     * and use setTextField() to display matching information.
     *
     * report button is to send the matched information to your phone.
     */
    private void setButtonAction() {
        summonerOk.setOnAction(event -> {
            if(username.getText().isEmpty()) {
                PopUpWindow.throwWarning("Username is empty.");
            } else {
                UserInfo user = database.getSummonerInfo(username.getText());
                if(user != null) {
                    boolean bool = PopUpWindow.confirmWindow("Find information in Local Database.\n" +
                            "Do you want to load from local?");
                    if(bool) {
                        info = user;
                    } else {
                        info = maker.getSummonerByName(choiceBox.getValue(), username.getText());
                        database.updateSummonerInfo(info);
                    }
                } else {
                    info = maker.getSummonerByName(choiceBox.getValue(), username.getText());
                    new Thread(() -> database.writeSummonerInfo(info)).start();
                }

                setTextField();
                if(ld.getYear() < year.getValue()) {
                    PopUpWindow.confirmWindow("Stale.\nIf you request the report, the" +
                            "report will start with an asterisk (*).");
                    stale = true;
                }
            }
        });

        report.setOnAction(event -> {
            if(info == null) {
                PopUpWindow.throwWarning("Please fill your info first.");
            } else {
                PopUpWindow.confirmWindow("You have successfully send the information.");
                String star = "";
                if(stale) {
                    star = "(*)";
                }
                SmsMaker.sendMessage(star+
                        "Account ID: "+accountId.getText()+"\n"
                        +"Profile Icon ID: "+ iconId.getText()+"\n"
                        +"Revision Date: "+date.getText()+"\n"
                        +"Name: "+name.getText()+"\n"
                        +"ID: "+id.getText()+"\n"
                        +"Puuid: "+puuid.getText()+"\n"
                        +"Level: "+level.getText());
            }
        });
    }

    /**
     * display the found information based on the username.
     */
    private void setTextField() {
        if(info == null) {
            return;
        }
        accountId.setText(info.getAccountId());
        iconId.setText(String.valueOf(info.getProfileIconId()));
        long millis = info.getRevisionDate();
        ld = Instant.ofEpochMilli(millis)
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
        date.setText(ld.toString());
        name.setText(info.getName());
        id.setText(info.getId());
        puuid.setText(info.getPuuid());
        level.setText(String.valueOf(info.getSummonerLevel()));

        List<TextField> list = new ArrayList<>(Arrays.asList(accountId, iconId, date, name, id, puuid, level));
        for(TextField t:list) {
            t.setEditable(false);
        }
    }

    /**
     *  creating the region choice box.
     */
    private void setChoiceBox() {
        choiceBox.getItems().addAll(new ArrayList<>(Arrays.asList("BR1", "EUN1", "EUW1", "JP1", "KR", "LA1", "LA2", "NA1", "OC1", "RU", "TR1")));
        choiceBox.setValue("OC1");

        List<Integer> list = new ArrayList<>();
        for(int i = 1900; i <= 2021; i++) {
            list.add(i);
        }
        year.getItems().addAll(list);
        year.setValue(1900);
    }


}
