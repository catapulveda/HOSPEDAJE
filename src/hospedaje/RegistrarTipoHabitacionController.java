package hospedaje;

import DAO.HotelDAO;
import DAO.TipoHabitacionDAO;
import animatefx.animation.Wobble;
import clases.Conexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Hotel;
import model.TipoHabitacion;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.Action;

public class RegistrarTipoHabitacionController implements Initializable {

    private final TipoHabitacionDAO thDAO = new TipoHabitacionDAO();
    Conexion con;
    
    @FXML
    private JFXTextField cjNombre;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private StackPane root;
    @FXML
    private GridPane gridPane;   
    @FXML
    private JFXTextArea cjDescripcion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardarTipoHabitacion(ActionEvent event) {
        
        if(cjNombre.getText().isEmpty()){            
            new Wobble(cjNombre).play();
            return;
        }
        
        con = new Conexion();
        TipoHabitacion th = new TipoHabitacion();
        th.setNombre(cjNombre.getText().trim());
        th.setDescripcion(cjDescripcion.getText().trim());
        
        try {
            thDAO.save(th, con);
            con.CERRAR();
            ((PopOver)((JFXButton)event.getSource()).getScene().getWindow()).hide();
        } catch (SQLException ex) {
            clases.Metodos.alert("ERROR", "OCURRIO UN ERROR AL INTENTAR GUARDAR EL TIPO DE HABITACIÓN", ex.getMessage(), Alert.AlertType.ERROR, ex, null);
            Logger.getLogger(RegistrarTipoHabitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
