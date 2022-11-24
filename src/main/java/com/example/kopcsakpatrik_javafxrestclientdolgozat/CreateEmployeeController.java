package com.example.kopcsakpatrik_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CreateEmployeeController extends Controller {
    @FXML
    private TextField nameField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField cvvField;
    @FXML
    private Button submitButton;

    @Deprecated
    private void initialize() {

    }

    @FXML
    public void submitClick(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        int salary =  Integer.parseInt(salaryField.getText().trim());
        int cvv = Integer.parseInt(cvvField.getText().trim());
        if (name.isEmpty()) {
            warning("Name is required");
            return;
        }
        Employee newPerson = new Employee(0, name, salary, cvv);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(newPerson);
        try {
            Response response = RequestHandler.post(App.BASE_URL, json);
            if (response.getResponseCode() == 201) {
                warning("Employee added!");
                nameField.setText("");
                salaryField.setText("");
                cvvField.setText("");
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}