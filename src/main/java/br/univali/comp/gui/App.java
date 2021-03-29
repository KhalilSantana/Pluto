package br.univali.comp.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        primaryStage.setTitle("Compilador");
        primaryStage.setScene(new Scene(root));

        Controller controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        primaryStage.show();
    }
}