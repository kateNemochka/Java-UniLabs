/*Напишіть фрагмент коду для генерування експоненціально
розподілених чисел за формулою (formula), де symb - випадкове число,
рівномірно розподілене в інтервалі (0;1).*/

package Practice1;

import java.lang.Math;
import Practice1.RandomNumberGenerator;

public class RandomExpGenerator {

    public static void main(String[] args) {
        int numbers = 15;
        double lambda = 1.0;
        RandomNumberGenerator r = new RandomNumberGenerator();

        for (int i = 0; i < numbers; ++i) {
            System.out.printf("%.3f ", expRandom(lambda, r.random()));
        }
    }

    public static double expRandom(double lambda, double ksi) {
        return -1 / lambda * Math.log(ksi);
    }
}
