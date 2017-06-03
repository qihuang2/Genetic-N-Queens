import java.util.List;

/**
 * Created by QiFeng on 6/3/17.
 */
public class TournamentSelector implements Selector<Chromosome> {

    private int mSize;

    public TournamentSelector(int size){
        mSize = size;
    }


    public Chromosome select(List<Chromosome> population){
        Chromosome best = population.get(RandomHelper.getRandom(population.size()));

        Chromosome curr;
        for (int i = 1; i < mSize ; i++){
            curr = population.get(RandomHelper.getRandom(population.size()));
            if (curr.getFitness() < best.getFitness())
                best = curr;
        }

        return best;
    }

}
