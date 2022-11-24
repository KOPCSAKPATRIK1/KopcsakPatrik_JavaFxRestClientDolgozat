package com.example.kopcsakpatrik_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateEmployeeController extends Controller{
    @FXML
    private TextField nameField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField cvvField;
    @FXML
    private Button updateButton;

    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
        nameField.setText(String.valueOf(this.employee.getName()));
        salaryField.setText(String.valueOf(this.employee.getSalary()));
        cvvField.setText(String.valueOf(this.employee.getSalary()));
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        String salary = salaryField.getText().trim();
        String cvv = cvvField.getText().trim();
        if (name.isEmpty()) {
            warning("Name is required");
            return;
        }
        this.employee.setName(name);
        this.employee.setSalary(Integer.parseInt(salary));
        this.employee.setCvv(Integer.parseInt(cvv));
        Gson converter = new Gson();
        String json = converter.toJson(this.employee);
        try {
            String url = App.BASE_URL + "/" + this.employee.getId();
            Response response = RequestHandler.put(url, json);
            if (response.getResponseCode() == 200) {
                Stage stage = (Stage) this.updateButton.getScene().getWindow();
                stage.close();
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
