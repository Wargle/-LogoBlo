package model;

/**
 *
 * @author Alexis Arnould
 */
public class CaseBleu extends Case {
    public CaseBleu() {
        super(0, 0, 255, 255);
    }

    @Override
    public int getRGB() {
        int c = 0;
        c = (trans<<24) | (rouge<<16) | (vert<<8) | bleu;
        return c;
    }
}
