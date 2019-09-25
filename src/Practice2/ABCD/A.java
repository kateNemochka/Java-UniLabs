package Practice2.ABCD;

public class A extends C implements D{
    private int a_attribute;


    public A(int aAttr, int cAttr) {
        super(cAttr);
        a_attribute = aAttr;
    }


    @Override
    public void make() {
        System.out.println("executing A make method");
    }

    @Override
    public void check() {
        System.out.println("executing A check method");
    }


    @Override
    public void show_attributes() {
        super.show_attributes();
        System.out.println(a_attribute);
    }

    public static void main(String[] args) {
        D dInterface = new D() {
            @Override
            public void make() {
                System.out.println("executing d interface make");
            }

            @Override
            public void check() {
                System.out.println("executing d interface check");
            }
        };

        dInterface.check();
        dInterface.make();

        new A(7, 10).check();
        new A(10, 7).show_attributes();
    }
}
