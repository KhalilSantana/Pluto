package br.univali.comp.gui;

import br.univali.comp.parser.tokenizer.Tokenizer;
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

import java.io.IOException;
import java.util.Optional;

public class Controller {
    private EditorFile editorFile = null;
    private static boolean hasEditedFile = false;
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
        handleOpenUnsavedFile();
        FileChooser filePicker = new FileChooser();
        filePicker.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*." + EditorFile.FILE_EXT));
        editorFile = new EditorFile(filePicker.showOpenDialog(new Stage()), false);
        // Error handling
        if (!editorFile.isFileStatusOK()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Failed opening file: %s", editorFile.getFileStatus()));
            alert.showAndWait();
            return;
        }
        fileContentsToCodeArea();
        clearMessageArea();
    }

    public void newFileDialog(ActionEvent event) {
        event.consume();
        handleOpenUnsavedFile();
        FileChooser filePicker = new FileChooser();
        filePicker.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Text files", "*." + EditorFile.FILE_EXT));
        editorFile = new EditorFile(filePicker.showSaveDialog(new Stage()), true);
        switch (editorFile.getFileStatus()) {
            case OK -> {
                fileContentsToCodeArea();
                clearMessageArea();
            }
            case INVALID_EXTENSION -> new Alert(Alert.AlertType.ERROR, "The file name must use the '.txt' suffix/extension!").show();
            case IO_ERROR -> new Alert(Alert.AlertType.ERROR, "There was an IO error while handling this request!").show();
            case NO_OPEN_FILE -> new Alert(Alert.AlertType.INFORMATION, "You've canceled creating a new file").show();
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
        this.stage.setTitle(String.format("Compiler - [%s]", editorFile.getFilePath().get()));
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
        registerWindowClose();
        registerLineColUpdater();
    }

    private Operation handleOpenUnsavedFile() {
        Operation op = Operation.CANCELED;
        if (hasEditedFile) {
            Alert alert = new Alert(Alert.AlertType.NONE,
                    "You have an edited file open and unsaved, do you want to save it?");
            alert.getDialogPane().getButtonTypes().addAll(
                    new ButtonType("Yes", ButtonBar.ButtonData.YES),
                    new ButtonType("No", ButtonBar.ButtonData.NO),
                    new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE)
            );
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.isPresent() && optional.get().equals(ButtonType.YES)) {
                EditorFile.FileStatus status = editorFile.save(inputTextArea.getText());
                if (status != EditorFile.FileStatus.OK) {
                    new Alert(Alert.AlertType.ERROR, "Failed saving file!").show();
                    op = Operation.FAILURE;
                } else {
                    hasEditedFile = false;
                    disableSaving(true);
                    op = Operation.SUCCESS;
                }
            }
            if (optional.isPresent() && optional.get().equals(ButtonType.NO)) {
                op = Operation.SUCCESS;
            }
        } else {
            op = Operation.SUCCESS;
        }
        System.out.println(op);
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
            Alert alert = new Alert(Alert.AlertType.ERROR, "A blank file cannot be compiled");
            alert.setTitle("Error");
            alert.setHeaderText("Error");
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
