package hospedaje;

import DAO.HotelDAO;
import animatefx.animation.Wobble;
import clases.Conexion;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Hotel;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.Action;

public class RegistrarHotelController implements Initializable {

    @FXML
    private JFXTextField cjNombre;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private StackPane root;
    @FXML
    private GridPane gridPane;

    private final HotelDAO hDAO = new HotelDAO();
    Conexion con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardarHotel(ActionEvent event) {
        
        if(cjNombre.getText().isEmpty()){            
            new Wobble(cjNombre).play();
            return;
        }
        
        con = new Conexion();
        Hotel hotel = new Hotel();
        hotel.setNombre(cjNombre.getText().trim());
        try {
            hDAO.save(hotel, con);
            con.CERRAR();
            ((PopOver)((JFXButton)event.getSource()).getScene().getWindow()).hide();
        } catch (SQLException ex) {
            clases.Metodos.alert("ERROR", "OCURRIO UN ERROR AL INTENTAR GUARDAR EL HOTEL", ex.getMessage(), Alert.AlertType.ERROR, ex, null);
            Logger.getLogger(RegistrarHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
