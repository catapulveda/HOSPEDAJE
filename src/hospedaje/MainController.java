package hospedaje;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author NELSON
 */
public class MainController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private MenuItem menuCerrar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            FXMLLoader l = new FXMLLoader();
            javafx.scene.layout.AnchorPane rootClientes = l.load(getClass().getResource("Clientes.fxml"));
            Tab tabClientes = new Tab("CLIENTES", rootClientes);
            FontIcon icon = new FontIcon("enty-users");
            icon.setIconSize(32);
            icon.setIconColor(Paint.valueOf("#FFF"));
            tabClientes.setGraphic(icon);
            
            
            javafx.scene.layout.AnchorPane rootHabitaciones = l.load(getClass().getResource("Habitaciones.fxml"));
            FontIcon icon2 = new FontIcon("dashicons-admin-multisite");
            icon2.setIconSize(32);
            icon2.setIconColor(Paint.valueOf("#FFF"));
            Tab tabHabitaciones = new Tab("HABITACIONES", rootHabitaciones);
            tabHabitaciones.setGraphic(icon2);
            
            tabPane.getTabs().addAll(tabClientes, tabHabitaciones);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void agregar(){
        
    }
    
}
