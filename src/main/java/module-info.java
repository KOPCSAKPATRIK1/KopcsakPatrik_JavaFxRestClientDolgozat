module com.example.kopcsakpatrik_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.kopcsakpatrik_javafxrestclientdolgozat to javafx.fxml, com.google.gson;
    exports com.example.kopcsakpatrik_javafxrestclientdolgozat;
}