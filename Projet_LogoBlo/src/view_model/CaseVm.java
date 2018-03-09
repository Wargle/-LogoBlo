/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

import com.sun.prism.paint.Gradient;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.Case;
import model.ReadWriteFile;

/**
 *
 * @author Alexis Arnould
 */
public class CaseVm extends Rectangle {
    
    public static final double CASE_SIZE = 24;

    private final ObjectProperty<Paint> bindColor = new SimpleObjectProperty<>();
    public Paint getBindColor() { return bindColor.get(); }
    public void setBindColor(Paint value) { bindColor.set(value); }
    public ObjectProperty bindColorProperty() { return bindColor; }
    
    private final DoubleProperty bindX = new SimpleDoubleProperty();
    public double getBindX() { return bindX.get(); }
    public void setBindX(double value) { bindX.set(value); }
    public DoubleProperty bindXProperty() { return bindX; }
    
    private final DoubleProperty bindY = new SimpleDoubleProperty();
    public double getBindY() { return bindY.get(); }
    public void setBindY(double value) { bindY.set(value); }
    public DoubleProperty bindYProperty() { return bindY; }
    
    private Case model;

    public CaseVm(Case c, int x, int y) {
        model = c;
        
        widthProperty().set(CASE_SIZE);
        heightProperty().set(CASE_SIZE);
        
        fillProperty().bindBidirectional(bindColor);
        xProperty().bindBidirectional(bindX);
        yProperty().bindBidirectional(bindY);
        
        setProperty(x, y);
    }

    private void setProperty(int x, int y) {
        bindColor.set(Color.rgb(model.getRouge(), model.getVert(), model.getBleu(), model.getTrans() / 255));
        bindX.set(x * CASE_SIZE);
        bindY.set(y * CASE_SIZE);
    }
    
    public boolean isThis(int x, int y) {
        return bindX.getValue().equals(x * CASE_SIZE) && bindY.getValue().equals(y * CASE_SIZE);
    }

    @Override
    public String toString() {
        return xProperty() + "x" + yProperty();
    }
}
