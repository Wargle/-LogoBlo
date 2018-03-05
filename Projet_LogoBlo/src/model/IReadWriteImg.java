package model;

/**
 *
 * @author Alexis Arnould
 */
public interface IReadWriteImg<T> {
    public T read(String file);
    public void write(T datas, String file);
}
