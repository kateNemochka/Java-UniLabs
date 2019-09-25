/*Напишіть фрагмент коду для генерування чисел за формулою
генератора псевдовипадкових рівномірно розподілених в інтервалі
0;1 чисел: (formula), де a=5^13 , с=2^31 .*/

package Practice1;

import java.util.Random;
import java.lang.Math;

public class RandomNumberGenerator {
    private long a, c, z;

    RandomNumberGenerator() {
        Random rand = new Random();
        int seed = rand.nextInt();
        this.z = (seed >= 0) ? seed : -seed;
        this.a = (long) Math.pow(5, 13);
        this.c = (long) Math.pow(2, 13);
    }

    public static void main(String[] args) {
        int numbers = 100;
        RandomNumberGenerator r = new RandomNumberGenerator();
        for (int i = 0; i < numbers; ++i) {
            System.out.printf("%.3f ", r.random());
        }
    }

    public double random() {
        z = ((a * z) % c);
        return (double) z / c;
    }
}
