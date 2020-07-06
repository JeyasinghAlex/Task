package Task.StudentTask;

public class Subject {

    private SubjectName name;
    private int marks;

    public Subject(SubjectName name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public SubjectName getSubjectName() {
        return name;
    }

    public void getSubjectName(SubjectName name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
