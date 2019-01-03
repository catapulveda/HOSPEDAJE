package hospedaje;

import DAO.HotelDAO;
import clases.Conexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Habitacion;
import model.Hotel;

public class HabitacionesController implements Initializable {

    ObservableList<Hotel> listaHoteles = FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<Hotel> comboHoteles;
    @FXML
    private JFXTextField cjNombre;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TableView<Habitacion> tablaHabitaciones;
    @FXML
    private TableColumn<Habitacion, Hotel> colHotel;
    @FXML
    private TableColumn<Habitacion, String> colNombre;
    
    private Conexion con;
    private HotelDAO hDAO = new HotelDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        comboHoteles.setItems(listaHoteles);
        con = new Conexion();
        try {
            listaHoteles.setAll(hDAO.getHotels(con));
        } catch (SQLException ex) {
            clases.Metodos.alert("ERROR AL CARGAR LOS HOTELES", null, null, Alert.AlertType.NONE, ex, null);
            Logger.getLogger(HabitacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.CERRAR();
    }    
    
}
