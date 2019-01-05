package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TipoHabitacion {

    private final IntegerProperty idtipohabitacion = new SimpleIntegerProperty(0);
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty descripcion = new SimpleStringProperty();

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String value) {
        descripcion.set(value);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }    
 
    public int getIdtipohabitacion() {
        return idtipohabitacion.get();
    }

    public void setIdtipohabitacion(int value) {
        idtipohabitacion.set(value);
    }

    public IntegerProperty idtipohabitacionProperty() {
        return idtipohabitacion;
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
    
    
}
