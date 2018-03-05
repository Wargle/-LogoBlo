package model;

import com.sun.javafx.fxml.expression.BinaryExpression;

/**
 *
 * @author Alexis Arnould
 */
public class ConvertRgbHexa {
    private static String[] mapColors = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    
    public static int getPosofHexa(String hexa) {
        int i;
        for(i = 0; i < 16; i++) {
            if(mapColors[i].equals(hexa)) {
                break;
            }
        }
        return i;
    }

    private static int calculHexaByColor(int r, int v, int b) {
        String bin = Integer.toBinaryString(b) + Integer.toBinaryString(v) + Integer.toBinaryString(r);
        int pos = Integer.parseInt(bin, 2);
        return pos;
    }
    
    public static String getHexaFromRGB(int rouge, int vert, int bleu) {
        int r = rouge/128, v = vert/128, b = bleu/64;
        return mapColors[calculHexaByColor(r, v, b)];
    }
    
    public static String getHexaFromRGB(int color) {
        int rouge = (color>>16) & 0xff;
        int vert = (color>>8) & 0xff;
        int bleu = color & 0xff;
                        
        return getHexaFromRGB(rouge, vert, bleu);
    }
    
    public static int getRGBFromHexa(String hexa) {
        
        String bin = String.format("%4s", Integer.toBinaryString(getPosofHexa(hexa))).replace(' ', '0');
        String r = Character.toString(bin.charAt(3)), 
                v = Character.toString(bin.charAt(2)), 
                b = Character.toString(bin.charAt(0)) + Character.toString(bin.charAt(1));
        int rI = Integer.parseInt(r, 2), vI = Integer.parseInt(v, 2), bI = Integer.parseInt(b, 2);
        
        return 255 << 24 | (rI * 128 << 16) | (vI * 128 << 8) | bI * 64;
    }
}