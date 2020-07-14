package Task.Db;

import java.util.*;
import java.util.stream.Stream;

public class DbApi {

    public List<Student> getStudentData() {
        List<Student> students = Dao.getInstance().getResult();
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                if (Department.valueOf(student1.getDepartment()).getOrder() > Department.valueOf(student2.getDepartment()).getOrder()) {
                    return 1;
                }
                if (Department.valueOf(student1.getDepartment()).getOrder() == Department.valueOf(student2.getDepartment()).getOrder()) {
                    if (student1.getId() > student2.getId()) {
                        return 1;
                    }
                }
                return -1;
            }
        });
        return students;
    }

    public void getTopThreeStudents() {
        List<Student> students = Dao.getInstance().getResult();
        List<Student> passStudents = new ArrayList<>();
        for (Student student : students) {
            if (isPass(student)) {
                passStudents.add(student);
            }
        }

        Collections.sort(passStudents, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                int sum1 = 0;
                int sum2 = 0;
                for (Subject subject : student1.getSubjects()) {
                    sum1 += subject.getMark();
                }
                for (Subject subject : student2.getSubjects()) {
                    sum2 += subject.getMark();
                }
                if (sum1 < sum2) {
                    return 1;
                }
                return -1;
            }
        });

        int count = 0;
        int rank = 0;
        List<Integer> mark = new ArrayList<>();
        for (int i = 0; i < passStudents.size(); ++i) {
            if (!mark.contains(getTotalMark(passStudents.get(i)))) {
                mark.add(getTotalMark(passStudents.get(i)));
                rank = ++rank + count;
                count = 0;
            } else {
                count++;
            }
            if (rank <= 3)
                System.out.println(passStudents.get(i));
            if (rank > 3)
                break;
        }
    }

    private int getTotalMark(Student student) {
        int sum = 0;
        for (int i = 0; i < student.getSubjects().size(); ++i) {
            sum += student.getSubjects().get(i).getMark();
        }
        return sum;
    }


    public void getEachDepartmentTopRanks() {
        List<Student> students = Dao.getInstance().getResult();
        List<Student> passStudents = new ArrayList<>();
        for (Student student : students) {
            if (isPass(student)) {
                passStudents.add(student);
            }
        }

        Collections.sort(passStudents, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                int sum1 = 0;
                int sum2 = 0;
                for (Subject subject : student1.getSubjects()) {
                    sum1 += subject.getMark();
                }
                for (Subject subject : student2.getSubjects()) {
                    sum2 += subject.getMark();
                }
                if (Department.valueOf(student1.getDepartment()).getOrder() > Department.valueOf(student2.getDepartment()).getOrder()) {
                    return 1;
                }
                if (Department.valueOf(student1.getDepartment()).getOrder() == Department.valueOf(student2.getDepartment()).getOrder()) {
                    if (sum1 < sum2) {
                        return 1;
                    }
                }
                return -1;
            }
        });

        List<Integer> mark = new ArrayList<>();
        for (Department department : Department.values()) {
            int count = 0;
            int rank = 0;
            mark = new ArrayList<>();
            for (int i = 0; i < passStudents.size(); ++i) {
                if (passStudents.get(i).getDepartment().equals(department.toString())) {
                    if (!mark.contains(getTotalMark(passStudents.get(i)))) {
                        mark.add(getTotalMark(passStudents.get(i)));
                        rank = ++rank + count;
                        count = 0;
                    } else {
                        count++;
                    }
                    if (rank <= 3) {
                        System.out.println(passStudents.get(i));
                    }
                    if (rank > 3)
                        break;
                }
            }
            System.out.println("--------------------");
        }
    }

    public void getNthRankStudent(int n) {
        List<Student> students = Dao.getInstance().getResult();
        List<Student> passStudents = new ArrayList<>();
        for (int i = 0; i < students.size(); ++i) {
            if (isPass(students.get(i))) {
                passStudents.add(students.get(i));
            }
        }

        Collections.sort(passStudents, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                int sum1 = 0;
                int sum2 = 0;
                for (Subject subject : student1.getSubjects()) {
                    sum1 += subject.getMark();
                }
                for (Subject subject : student2.getSubjects()) {
                    sum2 += subject.getMark();
                }
                if (sum1 < sum2) {
                    return 1;
                }
                return -1;
            }
        });

        int count = 0;
        int rank = 0;
        List<Integer> mark = new ArrayList<>();
        for (int i = 0; i < passStudents.size(); ++i) {
            if (!mark.contains(getTotalMark(passStudents.get(i)))) {
                mark.add(getTotalMark(passStudents.get(i)));
                rank = ++rank + count;
                count = 0;
            } else {
                count++;
            }
            if (rank == n)
                System.out.println(passStudents.get(i));
            if (rank > n)
                break;
        }
    }

    public void getAllStudentDataRankWise() {
        List<Student> students = Dao.getInstance().getResult();
        List<Student> passStudents = new ArrayList<>();
        List<Student> failStudents = new ArrayList<>();

        for (int i = 0; i < students.size(); ++i) {
            if (isPass(students.get(i))) {
                passStudents.add(students.get(i));
            } else {
                failStudents.add(students.get(i));
            }
        }

        Comparator<Student> com = (student1, student2) ->
        {
            int sum1 = 0;
            int sum2 = 0;
            for (Subject subject : student1.getSubjects()) {
                sum1 += subject.getMark();
            }
            for (Subject subject : student2.getSubjects()) {
                sum2 += subject.getMark();
            }
            if (sum1 < sum2) {
                return 1;
            } else if (sum1 == sum2) {
                return 0;
            }
            return -1;
        };

        passStudents.sort(com);
        failStudents.sort(com);
        System.out.println("* -----   Pass Student Rank :-  ----- *");
        getAllStudentsRank(passStudents);
        System.out.println("* -----   fail Student Rank :-  ----- *");
        getAllStudentsRank(failStudents);
    }

    private void getAllStudentsRank(List<Student> students) {
        int rank = 0;
        int count = 0;
        List<Integer> mark = new ArrayList<>();
        for (int i = 0; i < students.size(); ++i) {
            if (!mark.contains(getTotalMark(students.get(i)))) {
                mark.add(getTotalMark(students.get(i)));
                rank = ++rank + count;
                count = 0;
            } else {
                count++;
            }
            System.out.println("Rank " + rank + " is -> " + students.get(i));
        }
        System.out.println();
    }

    private boolean isPass(Student student) {
        for (int i = 0; i < student.getSubjects().size(); ++i) {
            if (student.getSubjects().get(i).getMark() < 35) {
                return false;
            }
        }
        return true;
    }

    public void getEachSubjectAverage() {
        List<Student> students = Dao.getInstance().getResult();
        List<String> subName = Dao.getInstance().getSubjectsName();
        Map<String, Integer> average = new HashMap<>();
        Map<String, List<Integer>> marks = Dao.getInstance().getSubjectMark();

        for (String name : subName) {
            int sum = 0;
            List<Integer> mark = marks.get(name);
            for (int ch : mark) {
                sum += ch;
            }
            average.put(name, sum / mark.size());
        }
        average.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(System.out::println);
    }

    public void getHighestMarkAndStudentNameTeacherName() {
        List<Student> students = Dao.getInstance().getResult();
        List<String> subName = Dao.getInstance().getSubjectsName();

        for (String name : subName) {
            int max = Integer.MIN_VALUE;
            for (Student student : students) {
                for (Subject subject : student.getSubjects()) {
                    if (subject.getName().equals(name) && subject.getMark() > max) {
                        max = subject.getMark();
                    }
                }
            }
            for (Student student : students) {
                for (Subject subject : student.getSubjects()) {
                    if (subject.getName().equals(name) && max == subject.getMark()) {
                        System.out.println(max + " " + student.getName() + " " + subject.getName() + " " + subject.getStaff());
                        break;
                    }
                }
            }
        }
    }

    public void getPassPercentageDeptWise() {
        Map<String, Integer> passPercentage = new HashMap<>();
        List<Student> students = getStudentData();
        for (Department department : Department.values()) {
            int count = 0;
            int totalStudent = 0;
            for (Student student : students) {
                if (student.getDepartment().equals(department.toString())) {
                    if (!isPass(student)) {
                        ++count;
                    }
                    ++totalStudent;
                }
            }
            int pass = totalStudent - count;
            passPercentage.put(department.toString(), (pass * 100) / totalStudent);
        }
        for (Map.Entry<String, Integer> entry : passPercentage.entrySet()) {
            System.out.format("%1c%-20s%1c%4d%2s%1c", '|', entry.getKey(), '|', entry.getValue(), "", '|');
            System.out.println();
        }
    }

    public void getTeacherWisePassPercentage() {
        Map<String, List<Integer>> marks = Dao.getInstance().getStudentMark();
        Map<String, Integer> passPercentage = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : marks.entrySet()) {
            int count = 0;
            int total = 0;
            List<Integer> list = entry.getValue();
            for (int ch : list) {
                if (ch < 35) {
                    count++;
                }
                total++;
            }
            int pass = total - count;
            passPercentage.put(entry.getKey(), (pass * 100) / total);
        }

        for (Map.Entry<String, Integer> entry : passPercentage.entrySet()) {
            System.out.format("%1c%-20s%1c%4d%2s%1c", '|', entry.getKey(), '|', entry.getValue(), "", '|');
            System.out.println();
        }
    }

    private enum Department {
        mech(0), civil(1), eee(2), ece(3), cse(4);
        int order;

        Department(int i) {
            this.order = i;
        }

        public int getOrder() {
            return order;
        }
    }
}
