package sample.model;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class Kontigent {

    private SimpleStringProperty id;
    private Date betaltDato;
    private SimpleStringProperty idMedlemFK;


    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public Date getBetaltDato() {
        return betaltDato;
    }

    public void setBetaltDato(Date betaltDato) {
        this.betaltDato = betaltDato;
    }

    public String getIdMedlemFK() {
        return idMedlemFK.get();
    }

    public SimpleStringProperty idMedlemFKProperty() {
        return idMedlemFK;
    }

    public void setIdMedlemFK(String idMedlemFK) {
        this.idMedlemFK.set(idMedlemFK);
    }
}
