/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JFileChooser;
import model.*;
import view_model.CaseVm;
import view_model.MyImageVm;

/**
 *
 * @author Alexis Arnould
 */
public class CodeWindowController implements Initializable {
    
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
    private Button parcour;
    
    private void update() {
        code.getChildren().clear();
        code.getChildren().addAll(iVm.getCases());
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
            }
        });
        
        path.setOnKeyPressed((KeyEvent ke) -> {
            if(ke.getCode() == KeyCode.ENTER) {
                iVm.print(path.getText());
            }
        });        
                
        /*String carac = "B";
        System.out.println(carac);
        String hexa = ConvertHexaAscii.getHexaFromAscii(carac);
        System.out.println(hexa);
        System.out.println(ConvertHexaAscii.getAsciiFromHexa(ConvertHexaAscii.getHexaFromAscii(carac)));*/
        
        /*code.getChildren().add(new CaseVm(new Case(Character.toString(hexa.charAt(0))), 2, 17));
        code.getChildren().add(new CaseVm(new Case(Character.toString(hexa.charAt(1))), 3, 17));*/
        
        /*Case c = new Case(128, 0, 0, 255);
        code.getChildren().add(new CaseVm(c, 17, 17));
        int color = c.getRGB();
        System.out.println(color);
        String hexa2 = ConvertRgbHexa.getHexaFromRGB(color);
        code.getChildren().add(new CaseVm(new Case(hexa2), 15, 17));
        System.out.println(hexa2);
        String hexaHexa2 = ConvertRgbHexa.getHexaFromRGB(ConvertRgbHexa.getRGBFromHexa(hexa2));
        System.out.println(hexaHexa2);
        code.getChildren().add(new CaseVm(new Case(hexaHexa2), 16, 17));*/
    }    
    
}
