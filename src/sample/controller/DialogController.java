package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.datasource.Datasource;

public class DialogController {

    @FXML
    private TextField tfNavn;
    @FXML
    private TextField tfEpost;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfTelefon;


    public void processResults() {

        String navn = tfNavn.getText().trim();
        String epost = tfEpost.getText().trim();
        String adresse = tfAdresse.getText().trim();
        String telefon = tfAdresse.getText().trim();

        Datasource.getInstance().insertMedlem(navn,adresse,telefon,epost);

    }


}
