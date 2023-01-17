module com.example.konyvtarasztali {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.konyvtarasztali to javafx.fxml;
    exports com.example.konyvtarasztali;
}