
/**
 * Created by QiFeng on 5/7/17.
 */

public class Chromosome{

    public static final int BEST_FITNESS = 0;

    private int[] mSequence;

    //number of clashes
    //the lower the better
    private int mFitness;

    public Chromosome(int size){
        mSequence = new int [size];
        mFitness = 0;
    }

    /**
     * generates a random board from 0 to N - 1
     * then mixes numbers up
     * we can do this because each row and column can't have more than 1 queen
     */
    public void generateRandomBoard(){
        for (int i = 0; i < mSequence.length; i++){
            mSequence[i] = i;
        }

        shuffleArray(mSequence);
    }

    static void shuffleArray(int[] ar)
    {
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = RandomHelper.getRandom(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


    /**
     * take parent1 and copy up to index. then look through parent2 and add number if it hasn't been seen in child yet
     * @param p1 - keep up to index
     * @param p2 - sequence is preserved
     * @param index - where to cross
     * @param mutRate - mutation rate
     */
    public void crossOver(Chromosome p1, Chromosome p2, int index, double mutRate){
        int i;
        int[] parentSeq = p1.getSequence();

        //keeps track of items seen
        boolean[] seen = new boolean[mSequence.length];

        for (i = 0; i < index; i++){
            mSequence[i] = parentSeq[i];
            seen[mSequence[i]] = true;
        }

        parentSeq = p2.getSequence();
        int p = 0;
        while (i < mSequence.length){
            if (seen[parentSeq[p]]){
                p++;
            }else {
                mSequence[i++] = parentSeq[p++];
            }
        }

        //mutate if needed
        for (i = 0; i < mSequence.length; i++){
            mutateIfNeeded(i, mutRate);
        }
    }


    public int getFitness(){
        return mFitness;
    }

    public void updateFitness(){
        mFitness = getClashes();
    }

    public int[] getSequence(){
        return mSequence;
    }


    private int getClashes(){
        int clashes = 0;

        int dx;
        int dy;
        for (int i = 0; i < mSequence.length; i++){
            for (int j = i + 1; j < mSequence.length; j++){
                if (i != j){
                    dx = Math.abs(i - j);
                    dy = Math.abs(mSequence[i] - mSequence[j]);

                    // diagonal conflicts
                    if (dx == dy) clashes++;
                }
            }
        }

        return clashes;
    }

    public void mutateIfNeeded(int index, double mutRate){
        if (RandomHelper.shouldMutate(mutRate)){
            int rand;
            do {
                rand = RandomHelper.getRandom(mSequence.length);
            }while (rand == index);

            swap(mSequence, index, rand);
        }
    }

    public static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void print(int[] seq){

        for (int i : seq){
            System.out.print(i + ", ");
        }
        System.out.println();

        for (int row = 0; row < seq.length; row++){

            for (int aSeq : seq) {
                System.out.print(row == seq.length - aSeq - 1 ? "X " : ". ");
            }

            System.out.println();
        }
    }

}
