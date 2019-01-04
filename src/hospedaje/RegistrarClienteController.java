package hospedaje;

import DAO.ClienteDAO;
import animatefx.animation.Wobble;
import clases.Conexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TitledPane;
import javax.swing.JOptionPane;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author PROGRAMADOR
 */
public class RegistrarClienteController implements Initializable {

    @FXML
    private TitledPane root;
    @FXML
    private JFXTextField cjDocumento;
    @FXML
    private JFXTextField cjNombre;
    @FXML
    private JFXTextField cjApellido;
    @FXML
    private DatePicker cjFechaNacimiento;
    @FXML
    private JFXTextField cjTelefono;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    
    ClienteDAO cDAO = new ClienteDAO();
    Conexion con;
    private Cliente cliente = new Cliente();
    private boolean n = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
    @FXML
    void guardar(ActionEvent evt){
        
        if(cjDocumento.getText().isEmpty()){
            new Wobble(cjDocumento).play();
            return;
        }
        if(cjNombre.getText().isEmpty()){
            new Wobble(cjNombre).play();
            return;
        }
        if(cjApellido.getText().isEmpty()){
            new Wobble(cjApellido).play();
            return;
        }
        if(cjFechaNacimiento.getValue()==null){
            cjFechaNacimiento.setValue(LocalDate.parse("1990-01-01"));            
        }        
        
        Cliente c = null;
        if(getCliente().getIdcliente()==0){
            c = new Cliente();
        }else{
            System.out.println("actualizando");
            c = getCliente();
        }
        
        c.setDocumento(cjDocumento.getText().trim());
        c.setNombre(cjNombre.getText().trim());
        c.setApellido(cjApellido.getText().trim());
        c.setFechanacimiento(cjFechaNacimiento.getValue());
        c.setTelefono(cjTelefono.getText().trim());        
        
        con = new Conexion();
        try {
            n = (cDAO.save(c, con)>0);            
            clases.Metodos.closeEffect(root);
        } catch (Exception ex) {
            clases.Metodos.alert("ERROR AL REGISTRAR EL CLIENTE", null, null, Alert.AlertType.ERROR, ex, null);
            Logger.getLogger(RegistrarClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.CERRAR();
        }
    }
    
    @FXML
    void cerrar(ActionEvent evt){
        clases.Metodos.closeEffect( root );    
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cli) {
        this.cliente = cli;
        cjDocumento.textProperty().bindBidirectional(cliente.documentoProperty());
        cjNombre.textProperty().bindBidirectional(cliente.nombreProperty());
        cjApellido.textProperty().bindBidirectional(cliente.apellidoProperty());
        cjFechaNacimiento.valueProperty().bindBidirectional(cliente.fechanacimientoProperty());
        cjTelefono.textProperty().bindBidirectional(cliente.telefonoProperty());
    }

    public boolean isN() {
        return n;
    }

    public void setN(boolean n) {
        this.n = n;
    }
}
