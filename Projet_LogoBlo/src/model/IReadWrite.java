package model;

/**
 *
 * @author Alexis Arnould
 */
public interface IReadWrite<T> {
    public T read(String file);
    public void write(T datas, String file);
}
