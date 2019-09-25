package LectureExamples1;

public class InternalClassExample {
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public InternalClassExample() {
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {
        EvenIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    private class EvenIterator implements java.util.Iterator<Integer> {
        private int nextIndex = 0;

        public boolean hasNext() {
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);
            nextIndex += 2;
            return retValue;
        }
    }

    public static void main(String[] s) {
        InternalClassExample ds = new InternalClassExample();
        ds.printEven();
    }
}