package model;


/**
 *
 * @author Alexis Arnould
 */
public class MyImage {
    private Case[][] code;
    private IReadWriteImg io;

    private static int matriceSize;

    public MyImage() {
        matriceSize = 21;
        io = new ReadWriteFile();
        new ConvertRgbHexa();
    }

    public void loadImg(String file) {
        code = io.read(file);
    }

    public void printCode() {
        io.write(code, "C:\\Users\\Benoit\\Documents\\Cours L3_s6\\Reseaux\\TP\\src\\sample.png");
    }

    public String getMessage() {
        return "";
    }

    public static int getMatriceSize() {
        return matriceSize;
    }
}
