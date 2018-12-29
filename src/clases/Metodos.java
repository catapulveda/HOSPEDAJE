/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.HeadlessException;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 *
 * @author NELSON
 */
public class Metodos {
    
    public static void changeSizeOnColumn(TableColumn tc, TableView table) {
        try {
            Text title = new Text(tc.getText());
            double ancho = title.getLayoutBounds().getWidth() + 10;
            
            for (int i = 0; i < table.getItems().size(); i++) {                
                Object value = tc.getCellData(i);                 
                title = new Text( (value==null)?"":value.toString() );                
                
                if (title.getLayoutBounds().getWidth() > ancho) {
                    ancho = title.getLayoutBounds().getWidth()+10;
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
}
