package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TurInfo {

    private SimpleIntegerProperty idTur;
    private SimpleStringProperty turrute;


    public int getIdTur() {
        return idTur.get();
    }

    public SimpleIntegerProperty idTurProperty() {
        return idTur;
    }

    public void setIdTur(int idTur) {
        this.idTur.set(idTur);
    }

    public String getTurrute() {
        return turrute.get();
    }

    public SimpleStringProperty turruteProperty() {
        return turrute;
    }

    public void setTurrute(String turrute) {
        this.turrute.set(turrute);
    }
}
