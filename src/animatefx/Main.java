/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animatefx;

import animatefx.animation.BounceIn;
import animatefx.animation.BounceInDown;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/animatefx/sample.fxml"));
        primaryStage.setTitle("AnimateFX Demo");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
        new BounceInDown(root).play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}