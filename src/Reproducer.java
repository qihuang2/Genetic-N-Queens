import java.util.List;

/**
 * Created by QiFeng on 6/3/17.
 */
public interface Reproducer <T> {

    public void initPopulation();

    public List<T> advanceGeneration();

    public List<T> getPopulation();

    public T getBest();

    public int getGeneration();
}
