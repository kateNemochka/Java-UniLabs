/*Виконайте завдання 2 з використанням внутрішнього класу «Журнал
оцінок з дисципліни».*/

package Practice2.Student;

import java.util.ArrayList;

public class Student {
    private int coursesNum; //кількість дисциплін
    private RatingJournal[] ratingJournal; //масив, що складається з coursesNum елем. типу RatingJournal

    public Student(int crsN) {
        this.coursesNum = crsN;
        this.ratingJournal = new RatingJournal[crsN];
        for (int i = 0; i < crsN; ++i) {
            ratingJournal[i] = new RatingJournal(i);
        }
    }


    private static class RatingJournal {
        private ArrayList<Integer> journal;
        private int course;

        private RatingJournal(int courseNumber) {
            this.journal = new ArrayList<Integer>();
            this.course = courseNumber;
        }


        public void getMarks() {
            /* метод виводить рядок оцінок для дисципліни*/
            System.out.printf("Course %d:", course);
            for (Object m : journal) {
                System.out.printf(" %d", (Integer) m);
            }
            System.out.println();
        }

        private void addMark(int mark) {
            // додавання оцінки до журналу
            journal.add(mark);
        }

        private double courseAverage() {
            // розрахунок середньої оцінки для дисципліни
            ArrayList courseMarks = journal;
            if (courseMarks.size() == 0) {
                return 0.0;
            }
            double cAvg = 0.0;
            for (Object i : courseMarks) {
                cAvg += (Integer) i;
            }
            return cAvg / courseMarks.size();

        }

    }


    public void addStudentsMark(int course, int mark) {
        ratingJournal[course].addMark(mark);
    }

    public double average() {
        double average = 0.0;
        for (int i = 0; i < coursesNum; ++i) {
            average += ratingJournal[i].courseAverage();
        }
        return average / coursesNum;
    }

    public void progress() {
        // виведення середніх балів по кожній дисципліні та загалом
        for (int i = 0; i < coursesNum; ++i) {
            System.out.printf("Course %d average: %.2f\n",
                    i, ratingJournal[i].courseAverage());
        }
        System.out.printf("Student's average progress: %.2f\n", average());
    }

    public void showJournal() {
        // виведення списку усіх наявних оцінок у журналі
        System.out.println("\nStudent's journal");
        for (int i = 0; i < coursesNum; ++i) {
            ratingJournal[i].getMarks();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Student student = new Student(7);
        student.addStudentsMark(5, 70);
        for (int i = 0; i < student.coursesNum * 2; ++i) {
             student.addStudentsMark(i%student.coursesNum, 82 + i);
        }
        student.progress();
        student.showJournal();
    }
}
