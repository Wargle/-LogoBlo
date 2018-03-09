package model;

/**
 *
 * @author Alexis Arnould
 */
public class CaseWhite extends Case {
    public CaseWhite() {
        super(255, 255, 255, 255);
    }

    @Override
    public int getRGB() {
        return (trans<<24) | (rouge<<16) | (vert<<8) | bleu;
    }
}
