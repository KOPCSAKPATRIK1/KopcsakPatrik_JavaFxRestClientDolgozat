package com.example.kopcsakpatrik_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ListEmployeesController extends Controller{

    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, Integer> salaryCol;
    @FXML
    private TableColumn<Employee, Integer> cvvCol;

    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        cvvCol.setCellValueFactory(new PropertyValueFactory<>("cvv"));
        Platform.runLater(() -> {
            try {
                loadPeopleFromServer();
            } catch (IOException e) {
                error("Couldn't get data from server", e.getMessage());
                Platform.exit();
            }
        });
    }

    private void loadPeopleFromServer() throws IOException {
        Response response = RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Employee[] employes = converter.fromJson(content, Employee[].class);
        employeesTable.getItems().clear();
        for (Employee employe : employes) {
            employeesTable.getItems().add(employe);
        }
    }


    @FXML
    public void insertClick(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
    }
}
