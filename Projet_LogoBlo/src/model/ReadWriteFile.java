package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Alexis Arnould
 */
public class ReadWriteFile implements IReadWrite<Case[][]> {

    public static int MATRICE_SIZE = MyImage.MATRICE_SIZE;
    
    private boolean isBlue(int color) {
        int r = (color>>16) & 0xff, v = (color>>8) & 0xff, b = (color) & 0xff;
        return r <= 20 && v <= 20 && b >= 200;
    }
    
    private int isOrigin(BufferedImage img, int x, int y) {
        int size = 0, color;
        color = img.getRGB(x,y);
        if(!isBlue(color))
            return -1;
        while(isBlue(color)) {
            size++;
            x++;
            color = img.getRGB(x,y);
        }
        if(!isBlue(img.getRGB(x - 1, x - 1)))
            return -1;
        return size;
    }
    
    private Case readCase(BufferedImage img, int x, int y, int size) {
        int color = img.getRGB(x, y);
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                color += img.getRGB(i, j);
                color = color / 2;
            }
        }
        return new Case(color);
    } 

    @Override
    public Case[][] read(String file) {
        BufferedImage img = null;
        File f;
        Case[][] code = new Case[MATRICE_SIZE][MATRICE_SIZE];
        boolean isBreak = false;
        int x = 0, y = 0;
        
        try {
            f = new File(file);
            img = ImageIO.read(f);
            int width = img.getWidth(), height = img.getHeight(), caseSize = 1, i = 0, j = 0;
            
            for(i = 0; i < width; i++) {
                if(isBreak)
                    break;
                for(j = 0; j < height; j++) {
                    if(isBlue(img.getRGB(i, j))) {
                        caseSize = isOrigin(img, i, j);
                        if(caseSize != -1) {
                            isBreak = true;
                            x = i;
                            y = j;
                            break;
                        }
                    }
                }
            }
            
            code[0][0] = new CaseBlue();
            
            for(int ii = 0; ii < MATRICE_SIZE; ii++) {
                for(int jj = y + caseSize; jj < (y + caseSize) * MATRICE_SIZE; jj += caseSize) {
                    Case nc = readCase(img, x + ii*caseSize, jj, caseSize);
                    code[ii][jj / caseSize] = nc;
                    if(isBlue(nc.getRGB()))
                        break;
                }
            }
            
            return code;
        }
        catch(IOException e) {
            System.out.println(e);
            return null;
        }
    }

    private void writeCase(BufferedImage img, Case c, int x, int y){
        int color = c.getRGB();
        int a, r,g,b,p;
        for(int i = x * MATRICE_SIZE; i < x * MATRICE_SIZE + MATRICE_SIZE; i++) {
            for(int j = y * MATRICE_SIZE; j < y * MATRICE_SIZE + MATRICE_SIZE; j++) {
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

            for(int i = 0; i < MyImage.MATRICE_SIZE; i++) {
                for(int j = 0; j < MyImage.REAL_MATRICE_SIZE; j++) {
                    if(datas[i][j] == null)
                        continue;
                    writeCase(img, datas[i][j], i, j);
                }
            }
            writeCase(img, new CaseBlue(), 0, MyImage.REAL_MATRICE_SIZE);
            ImageIO.write(img, "png", f);
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
