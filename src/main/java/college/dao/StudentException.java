package college.dao;

public class StudentException extends RuntimeException {
    public StudentException(String s, Exception e) {
        super(s, e);
    }
    public StudentException(String s) {
        super(s);
    }
}
