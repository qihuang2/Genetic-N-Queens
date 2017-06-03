import java.util.Comparator;

/**
 * Created by QiFeng on 6/3/17.
 */
public class ChromosomeComparator implements Comparator<Chromosome> {

    //asc order

    @Override
    public int compare(Chromosome o1, Chromosome o2) {
        return o1.getFitness() - o2.getFitness();
    }

}
