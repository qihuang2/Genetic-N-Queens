import java.util.*;

/**
 * Created by QiFeng on 6/3/17.
 */
public class ElitistReproducer implements Reproducer<Chromosome> {

    private Selector<Chromosome> mChromosomeSelector;
    private List<Chromosome> mChromosomes;
    private Comparator<Chromosome> mChromosomeComparator;

    private int mPopulation;
    private int mN;
    private double mMutationRate;
    private int mEliteSize;

    private int mGeneration;


    public ElitistReproducer(
            int n,
            int population,
            double mutationRate,
            int eliteSize,
            Comparator<Chromosome> comparator,
            Selector<Chromosome> selector
    ){

        mN = n;
        mPopulation = population;
        mMutationRate = mutationRate;
        mEliteSize = eliteSize;
        mChromosomeComparator = comparator;
        mChromosomeSelector = selector;
    }



    @Override
    public void initPopulation() {
        mChromosomes = new ArrayList<>(mPopulation);

        mGeneration = 0;

        Chromosome chromosome;
        for (int i = 0; i < mPopulation; i++) {
            chromosome = new Chromosome(mN);
            chromosome.generateRandomBoard();
            chromosome.updateFitness();

            mChromosomes.add(chromosome);
        }

        Collections.sort(mChromosomes, mChromosomeComparator);
    }


    @Override
    public List<Chromosome> advanceGeneration() {
        List<Chromosome> newGeneration = new ArrayList<>(mPopulation);

        int crossAt;

        for (int elites = 0; elites < mEliteSize; elites++){
            newGeneration.add(mChromosomes.get(elites));
        }

        Chromosome parent1;
        Chromosome parent2;
        Chromosome child;

        int left = (mChromosomes.size() - Constants.ELITE_SIZE) / 2;

        for (int mate = 0; mate < left; mate++) {
            parent1 = mChromosomeSelector.select(mChromosomes);
            parent2 = mChromosomeSelector.select(mChromosomes);

            while (parent2 == parent1) { //make sure not the same parent
                parent2 = mChromosomeSelector.select(mChromosomes);
            }

            crossAt = getCrossIndex();

            child = getChild(parent1, parent2, crossAt);
            child.updateFitness();
            newGeneration.add(child);

            child = getChild(parent2, parent1, crossAt);
            child.updateFitness();
            newGeneration.add(child);
        }

        Collections.sort(newGeneration, mChromosomeComparator);

        mChromosomes = newGeneration;

        mGeneration++;

        return mChromosomes;
    }

    //get index for crossover
    //we have to cross at least one item from each parent
    //i.e, child can not be exact copy of either parent
    public int getCrossIndex(){
        return RandomHelper.getRandom(1, Constants.N - 2);
    }

    public Chromosome getChild(Chromosome p1, Chromosome p2, int index){
        Chromosome c = new Chromosome(Constants.N);
        c.crossOver(p1, p2, index, mMutationRate);
        return c;
    }

    @Override
    public List<Chromosome> getPopulation() {
        return mChromosomes;
    }

    @Override
    public Chromosome getBest() {
        return mChromosomes.get(0);
    }

    @Override
    public int getGeneration() {
        return mGeneration;
    }


}
