package ua.kruart.address_book.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.kruart.address_book.model.Person;
import ua.kruart.address_book.repository.impls.InMemoryAddressBookRepository;

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

    private void testData() {
        InMemoryAddressBookRepository addressBookList = new InMemoryAddressBookRepository();

        Person person1 = new Person("John Doe", "2223377");
        Person person2 = new Person("Luke Smith", "5558722");
        Person person3 = new Person("Kate Underwood", "9854401");

        addressBookList.add(person1);
        addressBookList.add(person2);

        person2.setPhone("3389392");

        addressBookList.delete(person2);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
