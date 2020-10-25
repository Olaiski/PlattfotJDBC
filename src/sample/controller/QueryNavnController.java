package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class QueryNavnController{

    @FXML
    private TextField tfSokNavn;


    public String getSokNavn() {
        return tfSokNavn.getText().trim();
    }

}
