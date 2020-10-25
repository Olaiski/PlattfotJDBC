package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SlettController {

    @FXML
    private Label lblNavn;

    public void setNavn(String navn) {
        lblNavn.setText(navn);
    }

}
