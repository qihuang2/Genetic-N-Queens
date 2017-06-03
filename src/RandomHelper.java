import java.util.Random;

/**
 * Created by QiFeng on 5/7/17.
 */
public class RandomHelper {

    private static final Random random = new Random();

    public static int getRandom(int max){
        return random.nextInt(max);
    }

    public static int getRandom(int min, int max){
        return random.nextInt(max - min) + min;
    }

    public static boolean shouldMutate(double rate){
        return random.nextDouble() < rate;
    }
}
