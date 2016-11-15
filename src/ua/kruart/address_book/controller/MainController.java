package ua.kruart.address_book.controller;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import ua.kruart.address_book.model.Person;
import ua.kruart.address_book.repository.impls.InMemoryAddressBookRepository;
import ua.kruart.address_book.utils.DialogManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private InMemoryAddressBookRepository repository = new InMemoryAddressBookRepository();

    private Stage mainStage;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSearch;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private TableView tableAddressBook;
    @FXML
    private TableColumn<Person, String> columnName;
    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    private Label labelCount;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    private ResourceBundle resourceBundle;

    private ObservableList<Person> backupList;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;

        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("fullName"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        setupClearButtonField(txtSearch);
        initListeners();
        fillData();
        initLoader();
    }

    private void setupClearButtonField(CustomTextField customTextField) {
        try{
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillData() {
        repository.fillTestData();
        backupList = FXCollections.observableArrayList();
        backupList.addAll(repository.getPersonList());
        tableAddressBook.setItems(repository.getPersonList());
    }

    private void initListeners() {
        repository.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });


        tableAddressBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    editDialogController.setPerson((Person)tableAddressBook.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });


    }

    private void initLoader() {
        try {

            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("ua.kruart.address_book.bundle.locale", new Locale("ua")));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCountLabel() {
        labelCount.setText(resourceBundle.getString("count_contacts") + ": " + repository.getPersonList().size());
    }


    public void actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;

        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();


        switch (clickedButton.getId()) {
            case "btnAdd":
                editDialogController.setPerson(new Person());
                showDialog();
                repository.add(editDialogController.getPerson());
                backupList.add(editDialogController.getPerson());
                break;

            case "btnEdit":
                if(!personIsSelected(selectedPerson)) {
                    return;
                }
                editDialogController.setPerson(selectedPerson);
                showDialog();
                break;


            case "btnDelete":
                if(!personIsSelected(selectedPerson)) {
                    return;
                }
                repository.delete(selectedPerson);
                backupList.remove(selectedPerson);
                break;

        }

    }

    private boolean personIsSelected(Person selectedPerson) {
        if (selectedPerson == null) {
            DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("select_person"));
            return false;
        }
        return true;
    }


    private void showDialog() {

        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle(resourceBundle.getString("edit"));
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

      editDialogStage.showAndWait(); // для ожидания закрытия окна
    }

    public void actionSearch(ActionEvent actionEvent) {
        repository.getPersonList().clear();

        for (Person person : backupList) {
            if (person.getFullName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    person.getPhone().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                repository.getPersonList().add(person);
            }
        }
    }
}
