package com.example.konyvtarasztali;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class KonyvtarController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}