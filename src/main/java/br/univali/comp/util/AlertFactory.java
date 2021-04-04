package br.univali.comp.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertFactory {
    public static Alert create(Alert.AlertType type, String title, String header, String content) {
        var alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    public static Alert AlertYesNoCancel(Alert.AlertType type, String title, String header, String content) {
        var alert = new Alert(type, "",
                new ButtonType("Yes", ButtonBar.ButtonData.YES),
                new ButtonType("No", ButtonBar.ButtonData.NO),
                new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
}
