/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.Node;
import model.Case;
import model.CaseBleu;
import model.CaseNULL;
import model.ConvertRgbHexa;
import model.MyImage;

/**
 *
 * @author Alexis Arnould
 */
public class MyImageVm {
    
    public static final int MAX_X = MyImage.MATRICE_SIZE, MAX_Y = MAX_X;
    private List<CaseVm> casesVm;
    private MyImage model;
    
    private int xPos = 3, yPos = 1;

    public MyImageVm() {
        model = new MyImage();  
        casesVm = new LinkedList<>();
        
        init();
    }
    
    private void calculLigne(int ligne) {
        int sum = 0;
        for(int i = 3; i < MAX_X; i++) {
            if(model.getCase(i, ligne) == null)
                continue;
            sum += ConvertRgbHexa.getPosofHexa(ConvertRgbHexa.getHexaFromRGB(model.getCase(i, ligne).getRGB()));
        }
        String sumS = String.format("%3s", sum).replace(" ", "0");
        
        for(int j = 0; j < 3; j++) {
            ajouterCase(new Case(Character.toString(sumS.charAt(j))), j, ligne);
        }
    }
    
    private CaseVm findByPos(int x, int y) {
        CaseVm f = null;
        for(CaseVm c : casesVm) {
            if(c.isThis(x, y))
                return c;
        }
        return f;
    }
    
    private void enleverCalculLigne(int ligne) {
        for(int i = 0; i < 3; i++) {
            casesVm.remove(findByPos(i, ligne));
        }
    }
    
    public void ajouterCase(Case c) {
        if(yPos == MAX_Y - 1)
            return;
        
        model.ajouterCase(c, xPos, yPos);
        casesVm.add(new CaseVm(c, xPos, yPos));

        xPos++;
        if(xPos == MAX_Y) {
            yPos++;
            xPos = 3;
        } 
        
        if(xPos == 3)
            calculLigne(yPos - 1);
    }
    
    private void ajouterCase(Case c, int x, int y) {
        model.ajouterCase(c, x, y);
        casesVm.add(0, new CaseVm(c, x, y));
    }
    
    public void enleverCase() {
        if(xPos == 3 && yPos == 1)
            return;
        xPos--;
        if(xPos == 2) {
            yPos--;
            xPos = MAX_X - 1;
        }
        model.enleverCase(xPos, yPos);
        casesVm.remove(casesVm.size() - 1);
        
        if(xPos == MAX_X - 1)
            enleverCalculLigne(yPos);
    }

    private void init() {
        //etalonage
        ajouterCase(new CaseBleu(), 0, 0);
        ajouterCase(new CaseBleu(), MAX_X - 1, 0);
        ajouterCase(new CaseBleu(), 0, MAX_Y - 1);
        
        ajouterCase(new CaseNULL(), 1, 0);
        
        for(int i = 2; i<=17; i++) {
            String bin = String.format("%4s", Integer.toBinaryString(i - 1)).replace(' ', '0');
            String r = Character.toString(bin.charAt(3)), 
                v = Character.toString(bin.charAt(2)), 
                b = Character.toString(bin.charAt(0)) + Character.toString(bin.charAt(1));
            
            int rI = Integer.parseInt(r, 2), vI = Integer.parseInt(v, 2), bI = Integer.parseInt(b, 2);
            ajouterCase(new Case(rI * 128, vI * 128, bI * 64, 255), i, 0);
        }
    }
    
    public void print(String path) {
        if(yPos != MAX_Y - 1 && xPos != 3) {
            calculLigne(yPos);
            MyImage.REAL_MATRICE_SIZE = yPos + 1;
        }
        else
            MyImage.REAL_MATRICE_SIZE = yPos;
        
        model.printCode(path);
    }

    public List<CaseVm> getCases() {
        return casesVm;
    }
}
