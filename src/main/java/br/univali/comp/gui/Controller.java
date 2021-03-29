package br.univali.comp.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    private static boolean hasEditedFile = false;
    @FXML
    public CodeArea inputTextArea;
    public TextArea messageTextArea;
    public Label statusBar;
    // Menu bar items
    public MenuItem saveMenuItem;
    public MenuItem saveAsMenuItem;
    public MenuItem cutMenuItem;
    public MenuItem copyMenuItem;
    public MenuItem pasteMenuItem;
    //  Menu toolbar buttons
    public Button newBtn;
    public Button openBtn;
    public Button saveBtn;
    public Button copyBtn;
    public Button cutBtn;
    public Button pasteBtn;
    public Button buildBtn;
    public Button runBtn;
    public Button helpBtn;
    private Stage stage;
    private EditorFile editorFile = null;

    @FXML
    public void openFileDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        FileChooser filepicker = new FileChooser();
        editorFile = new EditorFile(filepicker.showOpenDialog(new Stage()));
        // Error handling
        if (!editorFile.isFileStatusOK()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Failed opening file: %s", editorFile.getFileStatus()));
            alert.showAndWait();
            return;
        }
        inputTextArea.setWrapText(false);
        try {
            inputTextArea.replaceText(editorFile.getFileContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputTextArea.setParagraphGraphicFactory(LineNumberFactory.get(inputTextArea));

        setStatusMsg(String.format("Success reading file %s", editorFile.getFilePath().get()));
        disableEditOptions(false);
        inputTextArea.setDisable(false);
    }

    @FXML
    public void saveFileDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        if (editorFile.isFileStatusOK()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    String.format("The file '%s' already exists, do you wish to overwrite it?", editorFile.getFilePath().get()));
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                try {
                    saveFile();
                    setStatusMsg("File saved!");
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR,
                            String.format("Failed saving file to '%s'", editorFile.getFilePath().get()));
                    e.printStackTrace();
                }
            }
        }

    }

    public void disableSaving(boolean b) {
        saveBtn.setDisable(b);
        saveMenuItem.setDisable(b);
        saveAsMenuItem.setDisable(b);
    }

    public void disableEditOptions(boolean b) {
        cutBtn.setDisable(b);
        cutMenuItem.setDisable(b);
        copyBtn.setDisable(b);
        copyMenuItem.setDisable(b);
        pasteBtn.setDisable(b);
        pasteMenuItem.setDisable(b);
    }

    public void saveFile() throws IOException {
        hasEditedFile = false;
        editorFile.writeToFile(inputTextArea.getText());
        disableSaving(true);
    }

    public void setStatusMsg(String msg) {
        statusBar.setText(String.format("Status: %s", msg));
    }

    public void fileContentChanged(KeyEvent keyEvent) {
        disableSaving(false);
        hasEditedFile = true;
    }

    public void registerWindowClose() {
        this.stage.setOnCloseRequest(new ExitButtonListener());
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
        registerWindowClose();
    }

    public class ExitButtonListener implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent windowEvent) {
            if (hasEditedFile) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "You have an edited file open and unsaved, do you want to save it?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                    try {
                        editorFile.writeToFile(inputTextArea.getText());
                        Platform.exit();
                    } catch (IOException e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Failed saving file!");
                    }
                }
            }
        }
    }
}
