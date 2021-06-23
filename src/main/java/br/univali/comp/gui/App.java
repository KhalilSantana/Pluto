package br.univali.comp.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = fxmlLoader.load();
        root.getStylesheets().add("/main.css");
        primaryStage.setTitle("Compiler");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/pluto.png"))));
        primaryStage.setScene(new Scene(root));

        Controller controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        primaryStage.show();
    }
}