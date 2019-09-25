/*Виберіть з рядку символів фрагменти однакових послідовностей, що
містять 4 символи. Наприклад, рядок abbcdaabcdaadd містить 2
однакові послідовності bcda та 2 однакові послідовності cdaa*/

package Practice1;

public class CharSequences {
    private char[][] sequences;
    private int[] count;
    private int unique; // sequences and count index

    CharSequences(int len) {
        this.sequences = new char[len-3][4];
        this.count = new int[len-3];
        this.unique = 0;
    }

    public static void main(String[] args) {

        String symbols = "abbcdaabcdaadd";
        CharSequences findRep = new CharSequences(symbols.length());

        for (int i = 0; i < symbols.length() - 3; ++i) {
            findRep.check(symbols.substring(i, i + 4));
        }

    }

    public void check(String seq) {
        if (unique == 0) {
            addSequence(seq, unique);
        }
        else {
            boolean matched = false;
            for (int i = 0; i < unique; ++i) {
                if (match(seq, i)) {
                    matched = true;
                    if (count[i] == 0) {
                        System.out.println(seq);
                        count[i] = 1;
                        break;
                    }
                }
            }
            if (!matched) {
                addSequence(seq, unique);
            }

        }

    }

    public boolean match(String s, int index) {
        for (int i = 0; i < 4; ++i) {
            if (s.charAt(i) != sequences[index][i]) {
                return false;
            }
        }
        return true;
    }

    public void addSequence(String s, int index) {
        for (int i = 0; i < 4; ++i) {
            sequences[index][i] = s.charAt(i);
            count[i] = 0;
        }
        unique++;
    }
}
