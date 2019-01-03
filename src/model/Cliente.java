package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {

    private final IntegerProperty idcliente = new SimpleIntegerProperty(0);
    private final StringProperty documento = new SimpleStringProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty apellido = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> fechanacimiento = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> fechaderegistro = new SimpleObjectProperty<>();

    public LocalDateTime getFechaderegistro() {
        return fechaderegistro.get();
    }

    public void setFechaderegistro(LocalDateTime value) {
        fechaderegistro.set(value);
    }

    public ObjectProperty fechaderegistroProperty() {
        return fechaderegistro;
    }
    

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String value) {
        telefono.set(value);
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }        

    public LocalDate getFechanacimiento() {
        return fechanacimiento.get();
    }

    public void setFechanacimiento(LocalDate value) {
        fechanacimiento.set(value);
    }

    public ObjectProperty fechanacimientoProperty() {
        return fechanacimiento;
    }    

    public int getIdcliente() {
        return idcliente.get();
    }

    public void setIdcliente(int value) {
        idcliente.set(value);
    }

    public IntegerProperty idclienteProperty() {
        return idcliente;
    }    

    public String getDocumento() {
        return documento.get();
    }

    public void setDocumento(String value) {
        documento.set(value);
    }

    public StringProperty documentoProperty() {
        return documento;
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

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String value) {
        apellido.set(value);
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }
    
    
}
