package dev.matt.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX Simple Calculator App
 */
public class App extends Application {

    private static Scene scene;
    String appName = "Clc";
    String version = "v0.0.1";
    
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("calcGrid"), 220, 350);
        //stage.setResizable(false);
        stage.setTitle(appName + " - " + version);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}