package br.univali.comp.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        handleOpenUnsavedFile();
        FileChooser filePicker = new FileChooser();
        filePicker.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        editorFile = new EditorFile(filePicker.showOpenDialog(new Stage()), false);
        // Error handling
        if (!editorFile.isFileStatusOK()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Failed opening file: %s", editorFile.getFileStatus()));
            alert.showAndWait();
            return;
        }
        fileContentsToCodeArea();
    }

    public void newFileDialog(ActionEvent event) {
        event.consume();
        handleOpenUnsavedFile();
        FileChooser filePicker = new FileChooser();
        filePicker.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        editorFile = new EditorFile(filePicker.showSaveDialog(new Stage()), true);
        switch (editorFile.getFileStatus()) {
            case OK:
                fileContentsToCodeArea();
                break;
            case INVALID_EXTENSION:
                new Alert(Alert.AlertType.ERROR, "The file name must use the '.txt' suffix/extension!").show();
                break;
            case IO_ERROR:
                new Alert(Alert.AlertType.ERROR, "There was an IO error while handling this request!").show();
                break;
            case NO_OPEN_FILE:
                new Alert(Alert.AlertType.INFORMATION, "You've canceled creating a new file").show();
                break;
        }
    }

    private void fileContentsToCodeArea() {
        hasEditedFile = false;
        inputTextArea.setWrapText(false);
        try {
            inputTextArea.replaceText(editorFile.getFileContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputTextArea.setParagraphGraphicFactory(LineNumberFactory.get(inputTextArea));

        setStatusMsg(String.format("Success reading file %s", editorFile.getFilePath().get()));
        updateStageTitle();
        disableEditOptions(false);
        inputTextArea.setDisable(false);
    }

    private void updateStageTitle() {
        this.stage.setTitle(String.format("Compilador - [%s]", editorFile.getFilePath().get()));
    }

    @FXML
    public void saveFileDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        if (editorFile.isFileStatusOK()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    String.format("The file '%s' already exists, do you wish to overwrite it?", editorFile.getFilePath().get()));
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                EditorFile.FileStatus status = editorFile.save(inputTextArea.getText());
                if (status == EditorFile.FileStatus.OK) {
                    setStatusMsg("File saved!");
                    updateStageTitle();
                    disableSaving(true);
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            String.format("Failed saving file to '%s'", editorFile.getFilePath().get()))
                            .show();
                }
            }
            hasEditedFile = false;
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

    public void setStatusMsg(String msg) {
        statusBar.setText(String.format("Status: %s", msg));
    }

    public void fileContentChanged() {
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

    private EditorFile.FileStatus handleOpenUnsavedFile() {
        EditorFile.FileStatus status = EditorFile.FileStatus.OK;
        if (hasEditedFile) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "You have an edited file open and unsaved, do you want to save it?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                status = editorFile.save(inputTextArea.getText());
                if (status != EditorFile.FileStatus.OK) {
                    new Alert(Alert.AlertType.ERROR, "Failed saving file!").show();
                }
            }
            hasEditedFile = false;
            disableSaving(true);
        }
        return status;
    }


    public class ExitButtonListener implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent windowEvent) {
            if (handleOpenUnsavedFile() == EditorFile.FileStatus.OK) {
                Platform.exit();
            }
        }
    }
}
