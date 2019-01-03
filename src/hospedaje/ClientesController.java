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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TitledPane;
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
    @FXML
    private Button btnVerUsuario;
    @FXML
    private Button btnEliminarUsuario;
    @FXML
    private Button btnListar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                               
        colDocumento.setCellValueFactory(cell->cell.getValue().documentoProperty());
        colNombre.setCellValueFactory(cell->{
            return new SimpleStringProperty(cell.getValue().getNombre()+" "+cell.getValue().getApellido());
        });
        colTelefono.setCellValueFactory(cell->cell.getValue().telefonoProperty());
        colFechaNacimiento.setCellValueFactory(cell->cell.getValue().fechanacimientoProperty());
        colFechaRegistro.setCellValueFactory(cell->cell.getValue().fechaderegistroProperty());
        
        tablaClientes.setEditable(true);
        tablaClientes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
        tablaClientes.getSelectionModel().setCellSelectionEnabled(true);
        
//        con = new Conexion();
//        try {
//            listaClientes.addAll(cDAO.clientes(con));
            tablaClientes.setItems(listaClientes);
//        } catch (SQLException ex) {            
//            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            con.CERRAR();  
//            tablaClientes.getColumns().forEach((column) -> {
//                clases. Metodos.changeSizeOnColumn(column, tablaClientes);
//            });            
//        }
        
        
    }
    
    @FXML
    void add(ActionEvent evt) throws IOException{
        abrirFormulario();
        stage.showAndWait();
        loadData(evt);
        ap.setEffect(null);
    }
    
    @FXML
    void delete(ActionEvent evt){
        ObservableList<Cliente> c = tablaClientes.getSelectionModel().getSelectedItems();
        if(c!=null){            
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "¿Confirma que desea borrar el usuario?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> option = confirm.showAndWait();
            if(option.get()==(ButtonType.YES)){
                con = new Conexion();
                c.forEach(i->{
                    try {
                        cDAO.delete(i, con);
                        listaClientes.remove(i);
                    } catch (SQLException ex) {
                        clases.Metodos.alert("ERROR AL ELIMINAR EL CLIENTE", null, null, Alert.AlertType.ERROR, ex, null);
                        Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                con.CERRAR();
            }
        }else{
            clases.Metodos.alert("Mensaje", "Seleccione un cliente de la tabla", null, Alert.AlertType.INFORMATION, null, null);
        }
    }
    
    @FXML
    void view(ActionEvent evt) throws IOException{
        Cliente c = tablaClientes.getSelectionModel().getSelectedItem();
        c.setDocumento("nooombree");
        abrirFormulario();
        rcc.setCliente(c);
        stage.showAndWait();
        ap.setEffect(null);
        listaClientes.set(tablaClientes.getSelectionModel().getSelectedIndex(), rcc.getCliente());
    }
    
    @FXML
    void loadData(ActionEvent evt){
        Task<List<Cliente>> loadDataTask = new Task<List<Cliente>>() {
            @Override
            protected List<Cliente> call() throws Exception {
                con = new Conexion();                
                return  cDAO.clientes(con);
            }
        };
        loadDataTask.setOnSucceeded(e -> {
            con.CERRAR();
            listaClientes.setAll(loadDataTask.getValue());
            tablaClientes.getColumns().forEach((column) -> {
                clases. Metodos.changeSizeOnColumn(column, tablaClientes);
            });
        });
        loadDataTask.setOnFailed(e -> { /* handle errors... */ });

        ProgressIndicator progressIndicator = new ProgressIndicator();
        tablaClientes.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();
    }
    
    private Stage stage = null;
    RegistrarClienteController rcc;
    void abrirFormulario() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        TitledPane root = loader.load(getClass().getResourceAsStream(("/hospedaje/RegistrarCliente.fxml")));
        rcc = loader.getController();
        stage = new Stage(StageStyle.UNDECORATED);        
        stage.setTitle("Registrar Cliente");
        stage.setScene(new Scene(root));        
        stage.initOwner(ap.getScene().getWindow());
        clases.Metodos.gaussianBlur(ap);                
    }
    
}
