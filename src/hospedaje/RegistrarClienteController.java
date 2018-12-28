package hospedaje;

import DAO.ClienteDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import model.Cliente;
import clases.Conexion;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NELSON
 */
public class RegistrarClienteController implements Initializable {

    @FXML
    private AnchorPane ap;
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

    
    ClienteDAO cDAO = new ClienteDAO();
    Conexion con;
    @FXML
    private JFXButton btnCerrar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void guardar(ActionEvent evt){
        
        Cliente c = new Cliente();
        c.setDocumento(cjDocumento.getText().trim());
        c.setNombre(cjNombre.getText().trim());
        c.setApellido(cjApellido.getText().trim());
        c.setFechanacimiento(cjFechaNacimiento.getValue());
        c.setTelefono(cjTelefono.getText().trim());
        
        con = new Conexion();
        try {
            int n = cDAO.guardar(c, con);
            System.out.println(n);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void cerrar(ActionEvent evt){
        Node  source = (Node)  evt.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
