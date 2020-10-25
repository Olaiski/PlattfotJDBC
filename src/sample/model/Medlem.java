package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Medlem {


    private SimpleStringProperty name;
    private SimpleStringProperty adresse;
    private SimpleStringProperty telefon;
    private SimpleStringProperty epost;

    public Medlem() {
        this.name = new SimpleStringProperty();
        this.adresse = new SimpleStringProperty();
        this.telefon = new SimpleStringProperty();
        this.epost = new SimpleStringProperty();
    }


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAdresse() {
        return adresse.get();
    }

    public SimpleStringProperty adresseProperty() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public SimpleStringProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public String getEpost() {
        return epost.get();
    }

    public SimpleStringProperty epostProperty() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost.set(epost);
    }

}
