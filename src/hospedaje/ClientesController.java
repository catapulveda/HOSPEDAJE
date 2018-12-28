package hospedaje;

import DAO.ClienteDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Cliente;
import clases.Conexion;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author NELSON
 */
public class ClientesController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private VBox vBox;
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colDocumento;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, LocalDate> colFechaNacimiento;
    @FXML
    private TableColumn<Cliente, LocalDateTime> colFechaRegistro;

    ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    ClienteDAO cDAO = new ClienteDAO();
    Conexion con;
    @FXML
    private Button btnAgregarUsuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        colTelefono.setCellValueFactory(cell->cell.getValue().telefonoProperty());
        colFechaNacimiento.setCellValueFactory(cell->cell.getValue().fechanacimientoProperty());
        colFechaRegistro.setCellValueFactory(cell->cell.getValue().fechaderegistroProperty());
        colDocumento.setCellValueFactory(cell->cell.getValue().documentoProperty());
        colNombre.setCellValueFactory(cell->{
            return new SimpleStringProperty(cell.getValue().getNombre()+" "+cell.getValue().getApellido());
        });
        
        tablaClientes.setEditable(true);
        tablaClientes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
        tablaClientes.getSelectionModel().setCellSelectionEnabled(true);
        
        con = new Conexion();
        try {
            listaClientes.addAll(cDAO.clientes(con));
            tablaClientes.setItems(listaClientes);
        } catch (SQLException ex) {            
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.CERRAR();  
            tablaClientes.getColumns().forEach((column) -> {
                clases.Metodos.changeSizeOnColumn(column, tablaClientes);
            });            
        }               
        
        
    }    
    
    
    @FXML
    void agregarUsuario(ActionEvent evt) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        AnchorPane app = loader.load(getClass().getResourceAsStream(("/hospedaje/RegistrarCliente.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Registrar Cliente");
        stage.setScene(new Scene(app));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(ap.getScene().getWindow());
        stage.showAndWait();
    }
}
