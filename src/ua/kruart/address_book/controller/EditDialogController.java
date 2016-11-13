package ua.kruart.address_book.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.kruart.address_book.model.Person;

/**
 * Created by Arthur on 09.11.2016.
 */
public class EditDialogController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    private Person person;

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void setPerson(Person person) {
        this.person = person;
        txtName.setText(person.getFullName());
        txtPhone.setText(person.getPhone());
    }

    public void actionSave(ActionEvent actionEvent) {
        person.setPhone(txtPhone.getText());
        person.setFullName(txtName.getText());
        actionClose(actionEvent);
    }
}
