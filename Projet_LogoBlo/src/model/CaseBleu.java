public class CaseBleu extends Case {
    public CaseBleu() {
        super(0, 0, 255, 255);
    }

    @Override
    public int getRGB() {
        return (trans<<24) | (rouge<<16) | (vert<<8) | bleu;
    }
}
