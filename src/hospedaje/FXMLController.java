/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospedaje;

import DAO.ClienteDAO;
import clases.Conexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author PROGRAMADOR
 */
public class FXMLController implements Initializable {

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
//        stage.set
        ScaleTransition st = new ScaleTransition(Duration.millis(300), ap);
        st.setToX(0);
        st.setToY(0);
        st.setToZ(0);        
        st.play();
        st.setOnFinished(e->{stage.close();});        
    }
}
