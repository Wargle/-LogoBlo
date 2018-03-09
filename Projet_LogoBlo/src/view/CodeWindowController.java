/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import model.*;
import view_model.CaseVm;
import view_model.MyImageVm;

/**
 *
 * @author Alexis Arnould
 */
public class CodeWindowController implements Initializable {

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
    
    
    private List<KeyCode> dropKeys = new ArrayList<>(Arrays.asList(KeyCode.ENTER, KeyCode.SHIFT, KeyCode.CAPS));
    private MyImageVm iVm;
    
    @FXML
    private Pane code;
    
    @FXML
    private GridPane window;
    
    @FXML
    private MyTextField mess;
    
    @FXML
    private TextField path;
    
    @FXML
    private Button decode, parcour, enter;
    
    private void update() {
        code.getChildren().clear();
        code.getChildren().addAll(iVm.getCases());
    }
    
    private void launch() {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/view/fxml/DecodeWindow.fxml")));
            stage.setScene(scene);

            stage.show();
        }
        catch (IOException e) {
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        code.setMinHeight(MyImageVm.MAX_Y * CaseVm.CASE_SIZE);
        code.setMinWidth(MyImageVm.MAX_X * CaseVm.CASE_SIZE);
        
        code.setPrefHeight(MyImageVm.MAX_Y * CaseVm.CASE_SIZE);
        code.setPrefWidth(MyImageVm.MAX_X * CaseVm.CASE_SIZE);
        
        mess.setMaxLength((MyImageVm.MAX_X - 3) * (MyImageVm.MAX_Y - 2) / 2);
        
        iVm = new MyImageVm();
        code.getChildren().addAll(iVm.getCases());
        
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
        
        mess.setOnKeyPressed((KeyEvent ke) -> {
            KeyCode k = ke.getCode();
            if(k == KeyCode.BACK_SPACE) {
                iVm.enleverCase();
                iVm.enleverCase();
                
                update();
            }
            
            if(MyRegex.isMatch("[\'\"éèàa-zA-Z0-9_ ?,;.:/!§ù%$£¤*µ]", ke.getText())) {
                String hexa = ConvertHexaAscii.getHexaFromAscii(ke.getText());
                iVm.ajouterCase(new Case(Character.toString(hexa.charAt(0))));
                iVm.ajouterCase(new Case(Character.toString(hexa.charAt(1))));

                update();
                //System.out.println(iVm.getMessage());
            }
        });
        
        path.setOnKeyPressed((KeyEvent ke) -> {
            if(ke.getCode() == KeyCode.ENTER) {
                iVm.print(path.getText());
            }
        }); 
        
        enter.setOnMouseClicked((event) -> {
            iVm.print(path.getText());
        });
        
        decode.setOnMouseClicked((event) -> {
            launch();
        });
    }    
    
}
