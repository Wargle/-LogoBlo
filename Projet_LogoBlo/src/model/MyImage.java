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
    private IReadWriteImg io;

    public static final int MATRICE_SIZE = 19;

    public MyImage() {
        code = new Case[MATRICE_SIZE][MATRICE_SIZE];
        io = new ReadWriteFile();
    }

    public void loadImg(String file) {
        code = (Case[][]) io.read(file);
    }

    public void printCode(String path) {
        try {
            Files.copy(new File("src\\resources\\sample.jpg").toPath(), new File(path).toPath(), StandardCopyOption.REPLACE_EXISTING);
            io.write(code, path);
        } 
        catch (IOException ex) {
            System.err.println("Erreur:: copy du fichier sample.jpg");
        }
    }

    public String getMessage() {
        return "";
    }
    
    public Case getCase(int x, int y) {
        return code[x][y];
    }

    public void ajouterCase(Case c, int x, int y) {
        code[x][y] = c;
    }
    
    public void enleverCase(int x, int y) {
        code[x][y] = new CaseNULL();
    }
}
