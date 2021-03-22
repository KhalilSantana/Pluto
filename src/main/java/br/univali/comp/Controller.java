package br.univali.comp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {
    private boolean hasOpenFile = false;
    private String absoluteFilePath;

    @FXML
    public TextArea inputTextArea;
    public TextArea messageTextArea;
    public Label statusBar;

    @FXML
    public void openFileDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        FileChooser filepicker = new FileChooser();
        File f = filepicker.showOpenDialog(new Stage());
        // Error handling
        if (f == null || !f.isFile()) {
            System.err.println("Failed to open file!");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Falha ao abrir arquivo!");
            alert.showAndWait();
            return;
        }
        // Get cannonical path / absolute path
        f = f.getAbsoluteFile();
        absoluteFilePath = f.getPath();
        inputTextArea.setWrapText(false);
        StringBuilder builder = new StringBuilder();
        try (Scanner input = new Scanner(f)) {
            while (input.hasNextLine()) {
                builder.append(input.nextLine());
                /*  Manually add newlines again, trying to preserve formating
                    TODO: find a class that preserves line-endings?
                 */
                builder.append('\n');
            }
        } catch (FileNotFoundException ex) {
            // Should never happen
            ex.printStackTrace();
        }
        inputTextArea.setText(builder.toString());
        hasOpenFile = true;
        setStatusMsg(String.format("Successo ao ler arquivo %s", absoluteFilePath));
    }

    public void setStatusMsg(String msg) {
        statusBar.setText(String.format("Status: %s", msg));
    }
}
