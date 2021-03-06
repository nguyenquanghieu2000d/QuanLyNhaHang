package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller.*;

import java.io.IOException;

public class Main extends Application {
    Stage window;
    Scene loginform, generalform;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFile.fxml"));
        loginform = new Scene(root);
        window.setScene(loginform);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
