package hospedaje;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author NELSON
 */
public class MainController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private JFXTabPane tabPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("Clientes.fxml"));
            AnchorPane ap = l.load();
            
            tabPane.getTabs().add(new Tab("CLIENTES", ap));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
