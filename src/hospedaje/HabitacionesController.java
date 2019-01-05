package hospedaje;

import DAO.HabitacionDAO;
import DAO.HotelDAO;
import animatefx.animation.Wobble;
import clases.Conexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.Cliente;
import model.Habitacion;
import model.Hotel;
import org.controlsfx.control.PopOver;

public class HabitacionesController implements Initializable {

    ObservableList<Habitacion> listaHabitaciones = FXCollections.observableArrayList();
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
    private TableColumn<Habitacion, String> colHotel;
    @FXML
    private TableColumn<Habitacion, String> colNombre;
    
    
    private Conexion con;
    private HotelDAO hDAO = new HotelDAO();
    private final HabitacionDAO habitacionDAO = new HabitacionDAO();
    
    private PopOver pop = new PopOver();    
    @FXML
    private Button btnRecargarHoteles;
    @FXML
    private Button btnMostrarPopupHotel;
    @FXML
    private Button btnRecargarTipoHabitacion;
    @FXML
    private Button btnMostrarPopupTipoHabitacion;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnRecargarHoteles.setTooltip(new Tooltip("Cargar lista de hoteles"));
        btnMostrarPopupHotel.setTooltip(new Tooltip("Registrar nuevo hotel"));
        
        btnRecargarTipoHabitacion.setTooltip(new Tooltip("Cargar tipos de habitacion"));
        btnMostrarPopupTipoHabitacion.setTooltip(new Tooltip("Registrar tipo de habitacion"));
        
        btnGuardar.setTooltip(new Tooltip("Guardar habitación"));
        
        colHotel.setCellValueFactory(cell->cell.getValue().getHotel().nombreProperty());
        colNombre.setCellValueFactory(cell->cell.getValue().nombreProperty());
        
        tablaHabitaciones.setItems(listaHabitaciones);
        tablaHabitaciones.setEditable(true);
        tablaHabitaciones.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
        tablaHabitaciones.getSelectionModel().setCellSelectionEnabled(true);                
        
        comboHoteles.setItems(listaHoteles);        
        
        loadHoteles(null);
        
        loadData();
                                
    }
    
    @FXML
    void guardarHabitacion(ActionEvent evt){
        
        Hotel hotel = comboHoteles.getSelectionModel().getSelectedItem();
        if(cjNombre.getText().isEmpty()){
            new Wobble(cjNombre).play();
            return;
        }
        String nombre = cjNombre.getText();
        
        Habitacion hab = new Habitacion();
        hab.setNombre(nombre);
        hab.setHotel(hotel);
        
        try {
            con = new Conexion();
            int n = habitacionDAO.save(hab, con);
            cjNombre.setText(null);
            loadData();
        } catch (SQLException ex) {
            clases.Metodos.alert("ERROR AL REGISTRAR LA HABITACION", null, null, Alert.AlertType.ERROR, ex, null);
            Logger.getLogger(HabitacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.CERRAR();
        }
    }
        
    void loadData(){
        Task<List<Habitacion>> loadDataTask = new Task<List<Habitacion>>() {
            @Override
            protected List<Habitacion> call() throws Exception {
                con = new Conexion();                
                return  habitacionDAO.getHabitaciones(con);
            }
        };
        loadDataTask.setOnSucceeded(e -> {
            con.CERRAR();
            listaHabitaciones.setAll(loadDataTask.getValue());
            tablaHabitaciones.getColumns().forEach((column) -> {
                clases. Metodos.changeSizeOnColumn(column, tablaHabitaciones, -1);
            });
        });
        loadDataTask.setOnFailed(e -> { /* handle errors... */ });

        ProgressIndicator progressIndicator = new ProgressIndicator();
        tablaHabitaciones.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();
    }
    
    @FXML
    void loadHoteles(ActionEvent evt){
        con = new Conexion();
        try {
            listaHoteles.setAll(hDAO.getHotels(con));
            comboHoteles.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            clases.Metodos.alert("ERROR AL CARGAR LOS HOTELES", null, null, Alert.AlertType.ERROR, ex, null);
            Logger.getLogger(HabitacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.CERRAR();
    }
       
    @FXML
    private void showPopup(ActionEvent event) throws IOException{
        if(!pop.isShowing()){
            StackPane stack = null;
            pop.setContentNode(stack);
            pop.setAnimated(true);pop.setHeaderAlwaysVisible(true);
            
            if(event.getSource()==btnMostrarPopupHotel){
                stack = FXMLLoader.load(getClass().getResource("RegistrarHotel.fxml"));
                pop.setTitle("Registrar Hotel");
            }else if(event.getSource()==btnMostrarPopupTipoHabitacion){
                pop.setTitle("Registrar Tipo de Habitación");
                stack = FXMLLoader.load(getClass().getResource("RegistrarTipoHabitacion.fxml"));
            }

            pop.setContentNode(stack);
            pop.show((Button) event.getSource());
        }        
    }    
}
