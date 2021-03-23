package br.univali.comp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class Controller {
    private boolean hasOpenFile = false;
    private String absoluteFilePath;

    @FXML
    public TextArea inputTextArea;
    public TextArea messageTextArea;
    public Label statusBar;
    // Menu bar items
    public MenuItem saveMenuItem;
    public MenuItem saveAsMenuItem;
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
        disableSaving(false);
    }

    @FXML
    public void saveFileDialog(ActionEvent actionEvent) {
        actionEvent.consume();
        File f = new File(absoluteFilePath);
        if (f.exists()) {
            System.err.printf("File exists: %s%n", absoluteFilePath);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Já existe um arquivo neste lugar, deseja sobrescrevê-lo?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                try {
                    saveFile(f);
                    setStatusMsg("Arquivo salvo");
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


    public void saveFile(File f) throws IOException {
        PrintWriter pw = new PrintWriter(f);
        BufferedWriter bufferedWriter = new BufferedWriter(pw);
        bufferedWriter.write(inputTextArea.getText());
        bufferedWriter.flush();
    }

    public void setStatusMsg(String msg) {
        statusBar.setText(String.format("Status: %s", msg));
    }
}
