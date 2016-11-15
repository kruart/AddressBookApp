package ua.kruart.address_book.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.kruart.address_book.model.Person;
import ua.kruart.address_book.utils.DialogManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arthur on 09.11.2016.
 */
public class EditDialogController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    private Person person;
    private ResourceBundle resourceBundle;

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()){
            return;
        }
        person.setPhone(txtPhone.getText());
        person.setFullName(txtName.getText());
        actionClose(actionEvent);
    }

    private boolean checkValues() {
        if (txtName.getText().trim().length()==0 || txtPhone.getText().trim().length()==0){
            DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("fill_field"));
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtName.setText(person.getFullName());
        txtPhone.setText(person.getPhone());
    }

    public Person getPerson() {
        return person;
    }
}
