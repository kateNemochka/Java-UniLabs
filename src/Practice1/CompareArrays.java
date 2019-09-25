/*Напишіть код програми, яка порівнює два масиви А і В та знаходить
кількість елементів, що не співпадають.*/

package Practice1;

import java.util.Random;

public class CompareArrays {
    public static void main(String[] args) {
        int arrayLength = 20;
        Random random = new Random();

        int[] array1 = new int[arrayLength];
        int[] array2 = new int[arrayLength];
        for (int i = 0; i < arrayLength; ++i) {
            array1[i] = random.nextInt(9);
            array2[i] = random.nextInt(9);
        }

        System.out.printf("Arrays length: %d\n", arrayLength);
        System.out.print("Array 1: ");
        printArray(array1);
        System.out.print("Array 2: ");
        printArray(array2);

        System.out.printf("Number of elements that don't match: %d", compare(array1, array2));
    }

    public static int compare(int[] arr1, int[] arr2) {
        int diff = 0;
        for (int i = 0; i < arr1.length; ++i) {
            if (arr1[i] != arr2[i]) {
                diff++;
            }
        }
        return diff;
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.printf("%d ", value);
        }
        System.out.println();
    }
}
