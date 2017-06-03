/**
 * Created by QiFeng on 5/7/17.
 */
public class Queens {


    private Reproducer<Chromosome> mChromosomeReproducer;


    public static void main(String[] args){
        ElitistReproducer elitistReproducer = new ElitistReproducer(
                Constants.N,
                Constants.POPULATION,
                Constants.MUTATION_RATE,
                Constants.ELITE_SIZE,
                new ChromosomeComparator(),
                new TournamentSelector(Constants.TOURNAMENT_SIZE)
        );

        Queens q = new Queens(elitistReproducer);
        q.solve(Constants.GENERATIONS);

    }

    public Queens(Reproducer<Chromosome> reproducer){
        mChromosomeReproducer = reproducer;
    }


    public void solve(int generations){
        mChromosomeReproducer.initPopulation();

        if (printIfNeeded(0, 1, mChromosomeReproducer.getBest())) return;

        int gen = 1;
        int printEvery = generations / 10;


        do {
            mChromosomeReproducer.advanceGeneration();
            if (printIfNeeded(gen, printEvery, mChromosomeReproducer.getBest())) return;
            gen++;
        }while (gen <= generations);

        printBoard(mChromosomeReproducer.getBest());
    }

    public boolean printIfNeeded(int generation, int printEvery, Chromosome chromosome){
        if (chromosome.getFitness() == Chromosome.BEST_FITNESS){
            printSolutionFound();
            printInfo(generation, chromosome);
            printBoard(chromosome);

            return true;

        }else if (generation % printEvery == 0){
            printInfo(generation, chromosome);
        }

        return false;
    }


    public void printSolutionFound(){
        System.out.println("\n--- SOLUTION FOUND ---");
    }

    public void printInfo(int generation, Chromosome chromosome){
        System.out.println("Conflicts left: " + chromosome.getFitness() + " ;;; Generation: " + generation);
    }

    public void printBoard( Chromosome chromosome){
        System.out.println("Board: ");
        Chromosome.print(chromosome.getSequence());
    }

}
