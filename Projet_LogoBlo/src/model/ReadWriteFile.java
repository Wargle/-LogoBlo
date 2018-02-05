package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Alexis Arnould
 */
public class ReadWriteFile implements IReadWriteImg {

    private static int caseSizePx = 10;

    @Override
    public Case[][] read(String file) {
        Case[][] code = new Case[21][21];
        for(int i = 0; i < 21; i++) {
            for(int j = 0; j < 21; j++) {
                code[i][j] = new CaseNULL();
            }
        }
        code[0][0] = new CaseBleu();
        code[0][20] = new CaseBleu();
        code[20][0] = new CaseBleu();
        return code;
    }

    private void writeCase(BufferedImage img, Case c, int x, int y){
        int color = c.getRGB();
        for(int i = x * caseSizePx; i < x * caseSizePx + caseSizePx; i++) {
            for(int j = y * caseSizePx; j < y * caseSizePx + caseSizePx; j++) {
                //System.out.println(":: " + i + "xx" + j + " // " + x + "xxx" + y);
                img.setRGB(i, j, color);
            }
        }
    }

    @Override
    public void write(Case[][] datas, String file) {
        BufferedImage img = null;
        File f;

        try {
            f = new File(file);
            img = ImageIO.read(f);

            for(int i = 0; i < MyImage.getMatriceSize(); i++) {
                for(int j = 0; j < MyImage.getMatriceSize(); j++) {
                    //System.out.println(i + "x" + j + " // " + datas[i][j].getRGB());
                    writeCase(img, datas[i][j], i, j);
                }
            }

            ImageIO.write(img, "png", f);
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
