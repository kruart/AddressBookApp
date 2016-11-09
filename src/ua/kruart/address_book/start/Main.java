package ua.kruart.address_book.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(new Scene(root, 350, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
