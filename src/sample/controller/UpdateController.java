package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateController {
    @FXML
    private TextField tfNavn;
    @FXML
    private TextField tfEpost;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfTelefon;


    public String getNavn() { return tfNavn.getText().trim(); }
    public String getEpost() { return tfEpost.getText().trim(); }
    public String getAdresse() { return tfAdresse.getText().trim(); }
    public String getTelefon() { return tfTelefon.getText().trim(); }

    public void setPrompts(String epost, String navn, String adr, String tlf) {
        tfEpost.setPromptText(epost);
        tfNavn.setPromptText(navn);
        tfAdresse.setPromptText(adr);
        tfTelefon.setPromptText(tlf);
    }



}
