package LectureExamples1;

public class ClassMethodArguments {
    private int f;
    private static int next = 1;
    private int id;

    public ClassMethodArguments(int valueF){
        f=valueF;
        id = next++;
    }

    public void changeFields(ClassMethodArguments another){
        f = another.getF();
    }

    public int getId() {
        return this.id;
    }

    public int getF() {
        return this.f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public static void changeNewObjects(ClassMethodArguments one, ClassMethodArguments another){
        one = new ClassMethodArguments(another.getF());
        another = new ClassMethodArguments(one.getF());
    }

    public static void changeFieldsOfObjects(ClassMethodArguments one, ClassMethodArguments another){
        int temp = one.getF();
        one.setF(another.getF());
        another.setF(temp);
    }

    public void print(){
        System.out.println(
                "Obj with id = "+getId()+ " has f = "+getF());
    }

    public static void main(String[] args){
        ClassMethodArguments one = new ClassMethodArguments(10);
        ClassMethodArguments another = new ClassMethodArguments(100000);
        one.print();
        another.print();
        System.out.println("------change fields---------");

        one.changeFieldsOfObjects(one,another);
        one.print();
        another.print();
    }
}
