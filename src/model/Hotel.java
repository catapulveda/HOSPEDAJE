package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hotel {

    private final IntegerProperty idhotel = new SimpleIntegerProperty(0);

    public int getIdhotel() {
        return idhotel.get();
    }

    public void setIdhotel(int value) {
        idhotel.set(value);
    }

    public IntegerProperty idhotelProperty() {
        return idhotel;
    }
    private final StringProperty nombre = new SimpleStringProperty();

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String value) {
        nombre.set(value);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public Hotel() {
    }        

    @Override
    public String toString() {
        return nombre.get(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
