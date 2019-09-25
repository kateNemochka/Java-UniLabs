/*Виберіть з масиву числа, які складаються з однакових цифр.*/

package Practice1;

import java.util.Random;

public class SameArrayNumbers {

    public static void main(String[] args) {
        int length = 20;
        int[] array = new int[length];
        Random random = new Random();

        // filling array with numbers
        System.out.print("Array elements: ");
        for (int i = 0; i < length; ++i) {
            array[i] = random.nextInt(1000);
            System.out.printf("%d ", array[i]);
        }

        System.out.println("\nNumbers with the same digits:");
        // checking pairs of numbers
        for (int i = 0; i < length; ++i) {
            for (int j = i+1; j < length; ++j) {
                if (sameDigits(array[i], array[j])) {
                    System.out.printf("(%d,%d) ", array[i], array[j]);
                }
            }
        }

    }

    public static boolean sameDigits(int num1, int num2) {
        int[] n1 = numDigits(num1);
        int[] n2 = numDigits(num2);
        for (int i = 0; i < 10; ++i) {
            if (n1[i] != n2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] numDigits(int number) {
        int[] digits = {0, 0, 0, 0, 0,  0, 0, 0, 0, 0};
        while (number > 0) {
            digits[number%10] += 1;
            number /= 10;
        }
        return digits;
    }
}
