package model;

/**
 *
 * @author Alexis Arnould
 */
public class Case {
    protected int rouge, vert, bleu, trans;

    public Case(int rouge, int vert, int bleu, int trans){
        this.rouge = rouge;
        this.vert = vert;
        this.bleu = bleu;
        this.trans = trans;
    }

    public Case(int color) {
        this((color>>16) & 0xff, (color>>8) & 0xff, color & 0xff, (color>>24) & 0xff);
    }
    
    public Case(String hexa) {
        this(ConvertRgbHexa.getRGBFromHexa(hexa));
    }

    public int getRouge() {
        return rouge;
    }

    public int getVert() {
        return vert;
    }

    public int getBleu() {
        return bleu;
    }

    public int getTrans() {
        return trans;
    }

    public int getRGB() {
        return (trans<<24) | (rouge<<16) | (vert<<8) | bleu;
    }

    public String getHexa() {
        return ConvertRgbHexa.getHexaFromRGB(getRGB());
    }
}
