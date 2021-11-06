module com.essai3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.mail;


    opens com.essai3 to javafx.fxml;
    opens com.essai3.beans;
    exports com.essai3;
}