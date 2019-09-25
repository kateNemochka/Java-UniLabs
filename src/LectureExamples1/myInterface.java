package LectureExamples1;

import java.io.Serializable;
import java.lang.Comparable;
import java.lang.Cloneable;

public interface myInterface extends Serializable,
        Comparable<myInterface>,
        Cloneable {
    static final int firstId = 1;
    int getId();
    default int firstId(){
        return firstId;
    }
}
