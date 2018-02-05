public class Case {
    protected int rouge, vert, bleu, trans;

    public Case(int r, int v, int b, int t){
        rouge = r;
        vert = v;
        bleu = b;
        trans = b;
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
        return (rouge<<16) | (vert<<8);
    }

    public String getHexa() {
        return ConvertRgbHexa.getHexaFromRG(rouge, vert);
    }
}
