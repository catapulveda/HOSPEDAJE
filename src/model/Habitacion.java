package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Habitacion {

    private final IntegerProperty idhabitacion = new SimpleIntegerProperty(0);
    private final StringProperty nombre = new SimpleStringProperty();
    private Hotel hotel;

    public Habitacion() {
    }        

    public int getIdhabitacion() {
        return idhabitacion.get();
    }

    public void setIdhabitacion(int value) {
        idhabitacion.set(value);
    }

    public IntegerProperty idhabitacionProperty() {
        return idhabitacion;
    }    

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String value) {
        nombre.set(value);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
        
}
