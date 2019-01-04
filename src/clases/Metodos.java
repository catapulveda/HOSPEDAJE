/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import animatefx.animation.AnimationFX;
import animatefx.animation.Wobble;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.awt.HeadlessException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author NELSON
 */
public class Metodos {
    
    public static void changeSizeOnColumn(TableColumn tc, TableView table, int indexRow) {
        try {
            Text title = new Text(tc.getText());
            double ancho = title.getLayoutBounds().getWidth() + 10;                        
            
            for (Object item : table.getItems()) {
                Object value = "";
                if(indexRow>-1){
                    value= tc.getCellData(indexRow);
                }else{
                    value= tc.getCellData(item);
                }
                title = new Text(""+value);
                
                if (title.getLayoutBounds().getWidth() > ancho) {
                    ancho = title.getLayoutBounds().getWidth()+10;
                }
                if(indexRow>-1){
                    break;
                }
            }
            tc.setPrefWidth(ancho + 8);
        } catch (HeadlessException ex) {
            System.err.println(ex);
        }
    }
    
    public static void gaussianBlur(Node node){
        javafx.scene.effect.GaussianBlur gb = new GaussianBlur(7.0);        
        node.setEffect(gb);
    }
    
    public static void closeEffect(Node node){
        Stage stage  = (Stage) node.getScene().getWindow();
        ScaleTransition st = new ScaleTransition(Duration.millis(100), node);
        st.setToX(0);
        st.setToY(0);
        st.setToZ(0);        
        st.play();        
        st.setOnFinished(e->{stage.close();});  
    }
    
    public static void alert(String tittle, String header, String content, Alert.AlertType type, Exception ex, String graphic){
        Alert a = new Alert(type);
        a.setTitle(tittle);
        
        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image(Metodos.class.getClassLoader().getResource("images/LOGO_CONSORCIO.png").toString()));
        
        if(header!=null){
            a.setHeaderText(header); 
        }
        if(content!=null){
            a.setContentText(content);
        }                
        if(graphic!=null){
            a.setGraphic(new ImageView(Metodos.class.getClass().getResource("/images/"+graphic).toString()));
        }                
        
        if(ex!=null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            
            Label label = new Label("Detalles del error:");
            TextArea textArea = new TextArea(sw.toString());
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            a.getDialogPane().setExpandableContent(expContent);
        }        
        a.showAndWait();        
    }        
    
}
