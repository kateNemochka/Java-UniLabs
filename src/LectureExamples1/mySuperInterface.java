package LectureExamples1;

public interface mySuperInterface extends myInterface{
    @Override
    default int firstId(){
        return 1;
    }
}
