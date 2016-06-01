/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfx;

import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mateusz
 */
public class ServerFx extends Application implements Initializable {
    
    @FXML
    private TextField portTextField;
    @FXML
    private Button okButton;
    @FXML
    private Button anulujButton;
    @FXML
    private Label serverLabel;
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            
            Scene scene = new Scene(root);
            stage.setTitle("Snake_Serwer");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ServerFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void connectAction(ActionEvent event) {
        new ServerPrime(portTextField, serverLabel , okButton);
        Log.set(Log.LEVEL_DEBUG);
        
    }
    
    @FXML
    private void anulujAction(ActionEvent event) {
        System.exit(0);
    }
    
}
