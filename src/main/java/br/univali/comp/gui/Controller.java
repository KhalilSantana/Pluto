package br.univali.comp.gui;

import br.univali.comp.javacc.*;
import br.univali.comp.util.AlertFactory;
import br.univali.comp.util.AppMetadataHelper;
import br.univali.comp.util.Operation;
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

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Controller {
    private EditorFile editorFile = new EditorFile();
    private static boolean hasEditedFile = false;
    private static boolean hasOpenFile = false;
    @FXML
    private Stage stage;
    public CodeArea inputTextArea;
    public TextArea messageTextArea;
    public Label statusBar, lineColLabel;
    // Menu bar items
    public MenuItem saveMenuItem, saveAsMenuItem;
    public MenuItem cutMenuItem, copyMenuItem, pasteMenuItem;
    //  Menu toolbar buttons
    public Button newBtn, openBtn, saveBtn;
    public Button copyBtn, cutBtn, pasteBtn;
    public Button buildBtn, runBtn;
    public Button helpBtn;

    @FXML
    public void openFileDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        if (handleOpenUnsavedFile() != Operation.SUCCESS) {
            return;
        }
        FileChooser filePicker = new FileChooser();
        filePicker.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*." + EditorFile.FILE_EXT));
        filePicker.setInitialDirectory(EditorFile.LAST_CURRENT_WORKING_DIR);
        editorFile = new EditorFile(filePicker.showOpenDialog(new Stage()), false);
        // Error handling
        if (!editorFile.isFileStatusOK()) {
            Alert alert = AlertFactory.create(Alert.AlertType.ERROR, "Error", "IO Error", String.format("Failed opening file: %s", editorFile.getFileStatus()));
            alert.showAndWait();
            return;
        }
        EditorFile.LAST_CURRENT_WORKING_DIR = editorFile.getFile().getParentFile();
        fileContentsToCodeArea();
    }

    public void newFileDialog(ActionEvent event) {
        event.consume();
        if (handleOpenUnsavedFile() != Operation.SUCCESS) {
            return;
        }
        inputTextArea.clear();
        messageTextArea.clear();
        hasOpenFile = false;
        this.editorFile.setFile(null);
        updateStageTitle();
    }

    private void fileContentsToCodeArea() {
        hasEditedFile = false;
        hasOpenFile = true;
        inputTextArea.setWrapText(false);
        try {
            inputTextArea.replaceText(editorFile.getFileContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStatusMsg(String.format("Success reading file %s", editorFile.getFilePath().get()));
        updateStageTitle();
        clearMessageArea();
    }

    private void updateStageTitle() {
        String title = "Compiler";
        if (hasOpenFile) {
            title += String.format(" - [%s]", editorFile.getFilePath().get());
        }
        this.stage.setTitle(title);
    }

    @FXML
    public Operation saveFileDialog(ActionEvent actionEvent) {
        Operation op = Operation.FAILURE;
        actionEvent.consume();
        EditorFile.FileStatus status = editorFile.save(inputTextArea.getText());
        if (status == EditorFile.FileStatus.OK) {
            onSaveSuccess();
            op = Operation.SUCCESS;
        } else {
            AlertFactory.create(Alert.AlertType.ERROR, "Error", "Operation Failed",
                    String.format("Failed saving file to '%s'", editorFile.getFilePath().get()))
                    .show();
        }
        return op;
    }

    @FXML
    private Operation saveAsDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        System.out.println("Save as called");
        Operation op = Operation.CANCELED;
        FileChooser filePicker = new FileChooser();
        filePicker.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*." + EditorFile.FILE_EXT));
        filePicker.setInitialDirectory(EditorFile.LAST_CURRENT_WORKING_DIR);
        File newFile = filePicker.showSaveDialog(new Stage());
        EditorFile newED = new EditorFile(newFile, false);
        switch (newED.getFileStatus()) {
            case INVALID_EXTENSION -> {
                AlertFactory.create(Alert.AlertType.ERROR, "Error", "Invalid Extension", "The file name must use the '.txt' suffix/extension!").show();
                op = Operation.FAILURE;
            }
            case IO_ERROR -> {
                AlertFactory.create(Alert.AlertType.ERROR, "Error", "IO Error", "There was an IO error while handling this request!").show();
                op = Operation.FAILURE;
            }
            case NO_OPEN_FILE -> {
                AlertFactory.create(Alert.AlertType.INFORMATION, "Information", "Operation Canceled", "You've canceled saving to a new file").show();
                op = Operation.CANCELED;
            }
            case OK -> {
                editorFile.saveAs(inputTextArea.getText(), newFile);
                System.out.println("Should run onSaveSuccess()");
                onSaveSuccess();
                op = Operation.SUCCESS;
            }
        }
        return op;
    }

    public Operation saveAction() {
        if (hasOpenFile && hasEditedFile) {
            return saveFileDialog(new ActionEvent());
        }
        if (!hasOpenFile && hasEditedFile) {
            return saveAsDialog(new ActionEvent());
        }
        return Operation.SUCCESS;
    }

    private void onSaveSuccess() {
        hasOpenFile = true;
        hasEditedFile = false;
        setStatusMsg("File saved!");
        updateStageTitle();
        disableSaving(true);
    }

    public void disableSaving(boolean b) {
        saveBtn.setDisable(b);
        saveMenuItem.setDisable(b);
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

    private void registerLineColUpdater() {
        inputTextArea.caretPositionProperty().addListener((observableValue, integer, t1) -> {
            int line = inputTextArea.getCurrentParagraph();
            int col = inputTextArea.getCaretColumn();
            setLineColLabel(line + 1, col + 1);
        });
    }

    private void setLineColLabel(int line, int col) {
        lineColLabel.setText(String.format("Line/Column: %d:%d", line, col));
    }

    public void registerWindowClose() {
        this.stage.setOnCloseRequest(new ExitButtonListener());
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
        inputTextArea.setParagraphGraphicFactory(LineNumberFactory.get(inputTextArea));
        registerWindowClose();
        registerLineColUpdater();
    }

    @FXML
    private Operation handleOpenUnsavedFile() {
        Operation op = Operation.CANCELED;
        Alert alert;
        if (hasEditedFile) {
            alert = AlertFactory.AlertYesNoCancel(Alert.AlertType.CONFIRMATION,
                    "Confirmation",
                    "Unsaved work",
                    "You have an edited file open and unsaved, do you want to save it?"
            );
        } else {
            return Operation.SUCCESS;
        }
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isEmpty()) {
            return Operation.CANCELED;
        }
        var buttonData = optional.get().getButtonData();
        if (buttonData.equals(ButtonType.YES.getButtonData()) && !hasOpenFile) {
            return saveAsDialog(new ActionEvent());
        }
        if (buttonData.equals(ButtonType.YES.getButtonData())) {
            return saveFileDialog(new ActionEvent());
        }
        if (buttonData.equals(ButtonType.NO.getButtonData())) {
            return Operation.SUCCESS;
        }
        return op;
    }

    public void showAboutDialog(ActionEvent event) {
        event.consume();
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Pluto Compiler");
        StringBuilder authorString = new StringBuilder();
        for (String author : AppMetadataHelper.getAuthors()) {
            authorString.append("\n").append(author);
        }
        about.setContentText(String.format(
                "Authors: %s\n" +
                        "\n\nSystem Info:\n" +
                        "Running on JAVA Version: %s\n" +
                        "Running JavaFX Version %s\n",
                authorString.toString(), AppMetadataHelper.javaVersion(), AppMetadataHelper.javafxVersion())
        );
        about.setHeaderText("About this app");
        about.show();
    }

    private void clearMessageArea() {
        this.messageTextArea.clear();
    }


    public class ExitButtonListener implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent windowEvent) {
            if (handleOpenUnsavedFile() == Operation.SUCCESS) {
                Platform.exit();
            } else {
                windowEvent.consume();
            }
        }

    }

    public void compileProgram(ActionEvent actionEvent) {
        actionEvent.consume();
        if (inputTextArea.getText().length() == 0) {
            Alert alert = AlertFactory.create(Alert.AlertType.ERROR, "Error", "Blank file", "A blank file cannot be compiled");
            alert.show();
            return;
        }
        String[] args = new String[0];
        java.io.InputStream targetStream = new java.io.ByteArrayInputStream(inputTextArea.getText().getBytes());
        Tokenizer tokenizer = new Tokenizer(targetStream);
        String result = tokenizer.getTokens(args, inputTextArea.getText());
        messageTextArea.setText(result);
        System.out.println(result);
    }

    public String copySelection() {
        return inputTextArea.getSelectedText();
    }

    public String cutSelection() {
        String selection = inputTextArea.getSelectedText();
        inputTextArea.cut();
        return selection;
    }

    public void pasteFromClipboard() {
        inputTextArea.paste();
    }
}
