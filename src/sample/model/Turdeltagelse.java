package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Turdeltagelse {

    private SimpleIntegerProperty id;
    private SimpleStringProperty turrute;
    private SimpleStringProperty medlemEpostFK;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getMedlemEpostFK() {
        return medlemEpostFK.get();
    }

    public SimpleStringProperty medlemEpostFKProperty() {
        return medlemEpostFK;
    }

    public void setMedlemEpostFK(String medlemEpostFK) {
        this.medlemEpostFK.set(medlemEpostFK);
    }
}
