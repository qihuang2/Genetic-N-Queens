import java.util.List;

/**
 * Created by QiFeng on 6/3/17.
 */
public interface Selector <T> {

    public T select(List<T> population);
}
