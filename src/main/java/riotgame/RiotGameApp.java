package riotgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import riotgame.model.riotapi.RiotGame;
import riotgame.model.riotapi.RiotGameMaker;
import riotgame.model.riotapi.RiotGameOffline;
import riotgame.model.riotapi.RiotGameOnline;
import riotgame.model.twilioapi.TwilioSms;
import riotgame.model.twilioapi.TwilioSmsMaker;
import riotgame.model.twilioapi.TwilioSmsOffline;
import riotgame.model.twilioapi.TwilioSmsOnline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RiotGameApp extends Application {

	private RiotGame riot;
	private TwilioSms twilioSms;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Parameters para = getParameters();
		if(para.getRaw().size() == 2) {

			if("offline".equals(para.getRaw().get(0)) && "offline".equals(para.getRaw().get(1))) {

				riot = new RiotGameOffline();
				twilioSms = new TwilioSmsOffline();

			} else if("online".equals(para.getRaw().get(0)) && "online".equals(para.getRaw().get(1))) {

				riot = new RiotGameOnline(readToken().get(0));
				twilioSms = new TwilioSmsOnline(readToken().get(1), readToken().get(2), readToken().get(3), readToken().get(4));

			} else if("online".equals(para.getRaw().get(0)) && "offline".equals(para.getRaw().get(1))) {

				riot = new RiotGameOnline(readToken().get(0));
				twilioSms = new TwilioSmsOffline();

			} else if("offline".equals(para.getRaw().get(0)) && "online".equals(para.getRaw().get(1))) {

				riot = new RiotGameOffline();
				twilioSms = new TwilioSmsOnline(readToken().get(1), readToken().get(2), readToken().get(3), readToken().get(4));

			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING, "You need to pass in correct parameters, read README.md.");
				alert.show();
			}

			// initialize RiotGameMaker and TwilioSMSMaker and ConnectDatabase
			ConnectDatabase.init();
			new Thread(() -> RiotGameMaker.init(new Handler(), riot)).start();
			new Thread(() -> TwilioSmsMaker.init(twilioSms)).start();


			// create the home window to display
			try {
				Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Home.fxml")));
				primaryStage.setScene(new Scene(root, 600, 430));
				primaryStage.setTitle("RiotGame");
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			PopUpWindow.throwWarning("You need to pass in parameters, read README.md.");
		}

	}

	/**
	 *  Read the riot api token, twilio token, twilio sid and the phone number used to send from and to.
	 * @return a list String which contains the found information.
	 */
	private List<String> readToken() {
		List<String> list = new ArrayList<>();
		try {
			File myObj = new File("src/main/resources/myconfig.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				list.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException event) {
			PopUpWindow.throwWarning("Can not find myconfig.txt");
			event.printStackTrace();
		}
		return list;
	}

	@Override
	public void stop() {
		// if you want to clear all the table, uncomment following:
		// ConnectDatabase.clearTable();
	}
}
