module com.example.kopcsakpatrik_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kopcsakpatrik_javafxrestclientdolgozat to javafx.fxml;
    exports com.example.kopcsakpatrik_javafxrestclientdolgozat;
}