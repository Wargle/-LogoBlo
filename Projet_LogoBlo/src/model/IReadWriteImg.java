package model;

/**
 *
 * @author Alexis Arnould
 */
public interface IReadWriteImg {
    public Case[][] read(String file);
    public void write(Case[][] datas, String file);
}
