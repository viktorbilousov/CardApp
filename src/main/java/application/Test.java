package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by BellPC on 22.01.2017.
 */
public class Test{
    StringProperty name;
    StringProperty familie;

    public Test(String name, String familie) {
        this.name = new SimpleStringProperty(name);
        this.familie = new SimpleStringProperty(familie);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFamilie() {
        return familie.get();
    }

    public StringProperty familieProperty() {
        return familie;
    }

    public void setFamilie(String familie) {
        this.familie.set(familie);
    }
}

