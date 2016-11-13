package ua.kruart.address_book.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.kruart.address_book.controller.MainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Адресная книга");
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(new Scene(fxmlMain, 350, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
