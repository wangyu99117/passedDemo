package huodong.format;

/**
 * Created by wangyu21 on 2017/2/24.
 */
public interface Format<T> {
    T get(Long id);

    T get(T info);


}
