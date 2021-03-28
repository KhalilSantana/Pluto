package br.univali.comp;

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

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class Controller {
    private static boolean hasEditedFile = false;
    private File currentlyOpenFile = null;

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

    @FXML
    public void openFileDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        FileChooser filepicker = new FileChooser();
        currentlyOpenFile = filepicker.showOpenDialog(new Stage());
        // Error handling
        if (currentlyOpenFile == null || !currentlyOpenFile.isFile()) {
            System.err.println("Failed to open file!");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Falha ao abrir arquivo!");
            alert.showAndWait();
            return;
        }
        inputTextArea.setWrapText(false);
        StringBuilder builder = new StringBuilder();
        try (Scanner input = new Scanner(currentlyOpenFile)) {
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
        inputTextArea.replaceText(builder.toString());
        inputTextArea.setParagraphGraphicFactory(LineNumberFactory.get(inputTextArea));

        setStatusMsg(String.format("Successo ao ler arquivo %s", getCurrentOpenFilePath().get()));
        disableEditOptions(false);
        inputTextArea.setDisable(false);
    }

    @FXML
    public void saveFileDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        if (currentlyOpenFile.exists()) {
            System.err.printf("File exists: %s%n", getCurrentOpenFilePath().get());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Já existe um arquivo neste lugar, deseja sobrescrevê-lo?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                try {
                    saveFile(currentlyOpenFile);
                    setStatusMsg("Arquivo salvo");
                    disableSaving(true);
                } catch (IOException e) {
                    System.err.println("Failed to save file!");
                    new Alert(Alert.AlertType.ERROR, "Falha em salvar o arquivo!");
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

    public void saveFile(File f) throws IOException {
        hasEditedFile = false;
        PrintWriter pw = new PrintWriter(f);
        BufferedWriter bufferedWriter = new BufferedWriter(pw);
        bufferedWriter.write(inputTextArea.getText());
        bufferedWriter.flush();
    }

    public void setStatusMsg(String msg) {
        statusBar.setText(String.format("Status: %s", msg));
    }

    public void fileContentChanged(KeyEvent keyEvent) {
        disableSaving(false);
        registerWindowClose();
        hasEditedFile = true;
    }

    public void registerWindowClose() {
        Stage stage = (Stage) inputTextArea.getScene().getWindow();
        stage.setOnCloseRequest(new ExitButtonListener());
    }

    public class ExitButtonListener implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent windowEvent) {
            System.err.println("Clicked close button!");
            if (hasEditedFile) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Você possui um arquivo editado aberto e não salvo, deseja salvar?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                    try {
                        saveFile(currentlyOpenFile);
                        Platform.exit();
                    } catch (IOException e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Falha em salvar o arquivo!");
                    }
                }
            }
        }
    }

    // Get cannonical path / absolute path
    private Optional<String> getCurrentOpenFilePath() {
        if (currentlyOpenFile != null && currentlyOpenFile.isFile()) {
            return Optional.of(currentlyOpenFile.getAbsolutePath());
        }
        return Optional.empty();
    }

}
