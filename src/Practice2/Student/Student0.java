package Practice2.Student;

/*Напишіть клас «Студент», який містить журнал оцінок студента та
може розраховувати середню успішність з дисципліни та загальну
середню успішність студента.*/

public class Student0 {
    private int maxMarkNum;     // максимальна кількість оцінок на дисципліну
    private int[][] marks;      // журнал оцінок: marks[дисципліна][оцінка]
    private int[] markIndex;    // індекси для наступних оцінок, що додаються, для кожної дисц.
    //marks.length == markIndex.length


    Student0(int coursesNumber, int maxMarkNumber) {
        this.maxMarkNum = maxMarkNumber;
        this.marks = new int[coursesNumber][maxMarkNumber];
        this.markIndex = new int[coursesNumber];
        //заповнення списку індексів нулями (1ша оцінка ставиться на 0й індекс)
        for (int i = 0; i < coursesNumber; ++i) {
            markIndex[i] = 0;
        }
    }


    public void addMark(int course, int mark) {
        //додавання оцінки за умови, що кількість наявних оцінок не є граничною
        int lastIndex = markIndex[course]++;
        if (lastIndex < maxMarkNum) {
            marks[course][lastIndex] = mark;
        } else {
            System.out.printf("Cannot add more than %d marks", maxMarkNum);
        }
    }

    public void showMarks() {
        //виведення журналу оцінок
        System.out.println("Student's marks: ");
        for (int[] crs : marks) {
            for (int mrk : crs) {
                System.out.printf("%d ", mrk);
            }
            System.out.println();
        }
        System.out.println();
    }

    public double courseAverage(int course) {
        //середнє по дисципліні
        double cAvg = 0;
        for (int mrk : marks[course]) {
            cAvg += mrk;
        }
        if (markIndex[course] != 0) {
            return cAvg / markIndex[course];
        }
        else {
            return 0;
        }
    }

    public double average() {
        //загальна середня успішність студента
        double avg = 0;
        for (int i = 0; i < marks.length; ++i) {
            avg += courseAverage(i);
        }
        avg /= marks.length;
        return avg;
    }


    public static void main(String[] args) {
        System.out.println("Creating student object with 7 marks");
        Student0 student = new Student0(7, 10);

        student.addMark(0, 50);
        student.showMarks();

        for (int i=0; i<6; i++) {
            student.addMark(i,90+i);
        }
        student.showMarks();

        student.addMark(5, 70);
        student.showMarks();

        System.out.printf("Course %d average: %.2f\n", 6, student.courseAverage(6));
        System.out.printf("Average mark: %.2f\n", student.average());
    }
}
