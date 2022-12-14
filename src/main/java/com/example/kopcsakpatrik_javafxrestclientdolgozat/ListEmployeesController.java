package com.example.kopcsakpatrik_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-employee-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Create People");
            stage.setScene(scene);
            stage.show();
            insertButton.setDisable(true);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
            stage.setOnCloseRequest(event -> {
                insertButton.setDisable(false);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
                try {
                    loadPeopleFromServer();
                } catch (IOException e) {
                    error("An error occurred while communicating with the server");
                }
            });
        } catch (IOException e) {
            error("Could not load form", e.getMessage());
        }
    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
        int selectedIndex = employeesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            warning("Please select a person from the list first");
            return;
        }

        Employee selected = employeesTable.getSelectionModel().getSelectedItem();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(String.format("Are you sure you want to delete %s?", selected.getName()));
        Optional<ButtonType> optionalButtonType = confirmation.showAndWait();
        if (optionalButtonType.isEmpty()) {
            System.err.println("Unknown error occurred");
            return;
        }
        ButtonType clickedButton = optionalButtonType.get();
        if (clickedButton.equals(ButtonType.OK)) {
            String url = App.BASE_URL + "/" + selected.getId();
            try {
                RequestHandler.delete(url);
                loadPeopleFromServer();
            } catch (IOException e) {
                error("An error occurred while communicating with the server");
            }
        }
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
        int selectedIndex = employeesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            warning("Please select a person from the list first");
            return;
        }
        Employee selected = employeesTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("update-employee-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Update People");
            stage.setScene(scene);
            UpdateEmployeeController controller = fxmlLoader.getController();
            controller.setEmployee(selected);
            stage.show();
            insertButton.setDisable(true);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
            stage.setOnHidden(event -> {
                insertButton.setDisable(false);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
                try {
                    loadPeopleFromServer();
                } catch (IOException e) {
                    error("An error occurred while communicating with the server");
                }
            });
        } catch (IOException e) {
            error("Could not load form", e.getMessage());
        }
    }
}
