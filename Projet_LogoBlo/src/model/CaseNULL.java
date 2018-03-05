package model;

/**
 *
 * @author Alexis Arnould
 */
public class CaseNULL extends Case {
    public CaseNULL() {
        super(255, 255, 255, 255);
    }

    @Override
    public int getRGB() {
        return (trans<<24) | (rouge<<16) | (vert<<8) | bleu;
    }
}
