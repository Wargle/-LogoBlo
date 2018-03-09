package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Alexis Arnould
 */
public class MyImage {

    public static int REAL_MATRICE_SIZE;
    private Case[][] code;
    private IReadWrite io;

    public static final int MATRICE_SIZE = 19;

    public MyImage() {
        code = new Case[MATRICE_SIZE][MATRICE_SIZE];
        io = new ReadWriteFile();
    }

    /**
     * Permet de charger un code depuis un fichier
     * @param file 
     */
    public void loadImg(String file) {
        code = (Case[][]) io.read(file);
    }
    
    /**
     * Permet d'écrire un code dans un fichier
     * @param path 
     */
    public void printCode(String path) {
        try {
            Files.copy(new File(System.getProperty("user.dir").replace("\\dist", "") + "/src/resources/sample.jpg").toPath(), new File(path).toPath(), StandardCopyOption.REPLACE_EXISTING);
            io.write(code, path);
        } 
        catch (IOException ex) {
            System.err.println("Erreur:: copy du fichier sample.jpg");
        }
    }

    /**
     * Renvoi le message du code
     * @return 
     */
    public String getMessage() {
        String mess = "";
        
        for(int j = 1; j < MATRICE_SIZE; j++) {
            for(int i = 3; i < MATRICE_SIZE; i += 2) {
                Case c = code[i][j], c2 = code[i + 1][j];
                if(c == null || c2 == null) continue;
                mess += ConvertHexaAscii.getAsciiFromHexa(ConvertRgbHexa.getHexaFromRGB(c.getRGB()).concat(ConvertRgbHexa.getHexaFromRGB(c2.getRGB())));
            }
        }
        
        return mess.replace("�", "");
    }
    
    public Case getCase(int x, int y) {
        return code[x][y];
    }

    public void ajouterCase(Case c, int x, int y) {
        code[x][y] = c;
    }
    
    public void enleverCase(int x, int y) {
        code[x][y] = new CaseWhite();
    }
}
