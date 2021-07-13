package riotgame;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import kong.unirest.JsonNode;

import java.util.Optional;

public class PopUpWindow {

    /**
     * Create a warning window with the given message.
     * @param message the message that displaying on the warning.
     */
    public static void throwWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
    }

    /**
     * Get the information from a JsonNode and display the information in a warning window.
     * @param node the JsonNode from service.
     */
    public static void throwWarning(JsonNode node) {
        String message = node.getObject().getJSONObject("status").getString("message");
        String code = node.getObject().getJSONObject("status").getString("status_code");
        Alert alert = new Alert(Alert.AlertType.WARNING, message+"\n"+code);
        alert.show();
    }

    /**
     *  Creating the confirmation window with the given information and return
     *  if the user click ok or cancel.
     * @param message the message displaying on the confirmation window.
     * @return true if the user clicks ok, false if the user closes the window or cancel.
     */
    public static boolean confirmWindow(String message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setResizable(false);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        return button == ButtonType.OK;
    }

}
