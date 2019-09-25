package Practice2.ABCD;

public class C {
    private int c_attribute;
    private static int counter=1;

    public C (int attrVal) {
        c_attribute = attrVal + counter++;
    }

    public void show_attributes() {
        System.out.printf("C\nNon-static attribute: %s\nStatic attribute: %s\n", c_attribute, counter);
    }

    public static void main(String[] args) {
        C c1 = new C(5);
        c1.show_attributes();
        new C(4).show_attributes();
    }
}
