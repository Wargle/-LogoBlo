/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import model.Case;
import model.ConvertHexaAscii;
import model.MyImage;
import model.MyRegex;

/**
 * FXML Controller class
 *
 * @author Alexis Arnould
 */
public class DecodeWindowController implements Initializable {
    
    private final StringProperty pathBind = new SimpleStringProperty();
    public String getPathBind() {
        return pathBind.get();
    }
    public void setPathBind(String value) {
        pathBind.set(value);
    }
    public StringProperty pathBindProperty() {
        return pathBind;
    }
    
    @FXML
    private Text mess;
    
    @FXML
    private TextField path;
    
    @FXML
    private Button parcour, enter;
    
    private String decode() {
        try {
            MyImage i = new MyImage();
            i.loadImg(pathBind.getValue());
            return i.getMessage();
        }
        catch (Exception e) {
            return "error";
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        path.textProperty().bindBidirectional(pathBind);
        pathBind.set("C:\\Users\\Eleme\\Desktop\\example.jpg");
        
        parcour.setOnMouseClicked((event) -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getPath();
                if(!path.endsWith(".jpg"))
                    path.concat(".jpg");
                pathBind.set(path);
            }
        });
        
        path.setOnKeyPressed((KeyEvent ke) -> {
            mess.setText(decode());
        }); 
        
        enter.setOnMouseClicked((event) -> {
            mess.setText(decode());
        });
    }    
    
}
