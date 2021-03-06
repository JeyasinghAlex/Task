package Task.StudentTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentUtil {

    public static Map<String, Integer> getRankBasedOnTotal(Student[] students) {
        boolean[] status = new boolean[students.length];

        for (int i = 0; i < students.length; ++i) {
            status[i] = isPass(students[i]);
        }
        sort(students, status);
        return setRank(students, status);
    }

    private static Map<String, Integer> setRank(Student[] students, boolean[] status) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> topRank = new HashMap<>();
        int rank = 0;
        int count = 0;
        for (int i = 0; i < students.length; ++i) {
            if (status[i] && list.contains(getTotalMark(students[i]))) {
                map.put(students[i].getName(), rank);
                count++;
            } else if (status[i]) {
                rank = ++rank + count;
                map.put(students[i].getName(), rank);
                list.add(getTotalMark(students[i]));
                count = 0;
            }else {
                map.put(students[i].getName(), 0);
            }
        }
        return map;
    }

    private static void sort(Student[] students, boolean[] status) {
        for (int i = 0; i < students.length; ++i) {
            for (int j = i + 1; j < students.length; ++j) {
                if (getTotalMark(students[i]) < getTotalMark(students[j])) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;

                    boolean tmp = status[i];
                    status[i] = status[j];
                    status[j] = tmp;
                }
            }
        }
    }
    public static int getStudentTotalMark(String studentName, Student[] students)  {
        for (int i = 0; i < students.length; ++i) {
            if (students[i].getName().equals(studentName)) {
                return getTotalMark(students[i]);
            }
        }
        return -1;
    }
    private static int getTotalMark(Student student) {
        List<Subject> subjects = student.getSubjects();
        int totalMark = 0;
        for (int i = 0; i < subjects.size(); ++i) {
            totalMark += subjects.get(i).getMarks();
        }
        return totalMark;
    }

    private static boolean isPass(Student student) {
        List<Subject> subjects = student.getSubjects();
        for (int i = 0; i < subjects.size(); ++i) {
            if (subjects.get(i).getMarks() < 35) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, Integer> getAverage(Student[] students) {
        Map<String, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < students[0].getSubjects().size(); ++i) {
            sum = 0;
            for (int j = 0; j < students.length; ++j) {
                sum += students[j].getSubjects().get(i).getMarks();
            }
            map.put(students[0].getSubjects().get(i).getSubjectName().toString(), sum / students.length);
        }
        return map;
    }

    public static  Map<String, Map<List<String>, List<Integer>>> getHighestMarkStudentName(Student[] students) {
        int max = Integer.MIN_VALUE;
        Map<String, Map<String, Integer>> highestMark = new HashMap<>();
        Map<String, Integer> studentName = new HashMap<>();
        String name = "";
        /*-------------------------------------------------------------------------------*/
        Map<String, Map<List<String>, List<Integer>>> map = new HashMap<>();
        Map<List<String>, List<Integer>> innerMap = new HashMap<>();
        List<String> stdName = new ArrayList<>();
        List<Integer> stdMark = new ArrayList<>();

            for (int i = 0; i < students[0].getSubjects().size(); ++i) {
                stdName = new ArrayList<>();
                stdMark = new ArrayList<>();
                innerMap = new HashMap<>();
                for (int j = 0; j < students.length; ++j) {
                    for (int k = 0; k < students[j].getSubjects().size(); ++k) {
                        if (students[0].getSubjects().get(i).getSubjectName().equals(students[j].getSubjects().get(k).getSubjectName())) {
                            stdName.add(students[j].getName());
                            stdMark.add(students[j].getSubjects().get(k).getMarks());
                        }
                    }
                }
                sort(stdMark, stdName);
                innerMap.put(stdName, stdMark);
                map.put(students[0].getSubjects().get(i).getSubjectName().toString(), innerMap);
            }
        /*-------------------------------------------------------------------------------*/
        for (int i = 0; i < students[0].getSubjects().size(); ++i) {
            studentName = new HashMap<>();
            max = Integer.MIN_VALUE;
            for (int j = 0; j < students.length; ++j) {
                if (students[j].getSubjects().get(i).getMarks() > max) {
                    max = students[j].getSubjects().get(i).getMarks();
                    name = students[j].getName();
                }
            }
            studentName.put(name, max);
            highestMark.put(students[0].getSubjects().get(i).getSubjectName().toString(), studentName);
        }
        return map;
    }

    public static Map<String, Map<String, Integer>> getAboveAverageMarkStudents(Student[] students, Map<String, Integer> map) {
        int averageTamil = map.get(SubjectName.TAMIL.toString());
        int averageEnglish = map.get(SubjectName.ENGLISH.toString());
        int averageMaths = map.get(SubjectName.MATHS.toString());
        int averageScience = map.get(SubjectName.SCIENCE.toString());
        int averageSocial = map.get(SubjectName.SOCIAL_SCIENCE.toString());
        Map<String, Integer> nameAndMark = new HashMap<>();
        Map<String, Map<String, Integer>> averageMarkAboveStudent = new HashMap<>();

        for (int i = 0; i < students[0].getSubjects().size(); ++i) {
            nameAndMark = new HashMap<>();
            for (int j = 0; j < students.length; ++j) {
                if (students[j].getSubjects().get(i).getSubjectName().equals(SubjectName.TAMIL) && students[j].getSubjects().get(i).getMarks() > averageTamil) {
                    nameAndMark.put(students[j].getName(), students[j].getSubjects().get(i).getMarks());
                } else if (students[j].getSubjects().get(i).getSubjectName().equals(SubjectName.ENGLISH) && students[j].getSubjects().get(i).getMarks() > averageEnglish) {
                    nameAndMark.put(students[j].getName(), students[j].getSubjects().get(i).getMarks());
                } else if (students[j].getSubjects().get(i).getSubjectName().equals(SubjectName.MATHS) && students[j].getSubjects().get(i).getMarks() > averageMaths) {
                    nameAndMark.put(students[j].getName(), students[j].getSubjects().get(i).getMarks());
                } else if (students[j].getSubjects().get(i).getSubjectName().equals(SubjectName.SCIENCE) && students[j].getSubjects().get(i).getMarks() > averageScience) {
                    nameAndMark.put(students[j].getName(), students[j].getSubjects().get(i).getMarks());
                } else if (students[j].getSubjects().get(i).getSubjectName().equals(SubjectName.SOCIAL_SCIENCE) && students[j].getSubjects().get(i).getMarks() > averageSocial) {
                    nameAndMark.put(students[j].getName(), students[j].getSubjects().get(i).getMarks());
                }
            }
            averageMarkAboveStudent.put(students[0].getSubjects().get(i).getSubjectName().toString(), nameAndMark);
        }
        return averageMarkAboveStudent;
    }

    public static void sort(List<Integer> averageMark, List<String> subName) {
        for (int i = 0; i < averageMark.size(); ++i) {
            for (int j = i + 1; j < averageMark.size(); ++j) {
                if (averageMark.get(i) < averageMark.get(j)) {
                    int temp = averageMark.get(i);
                    averageMark.set(i, averageMark.get(j));
                    averageMark.set(j, temp);

                    String str = subName.get(i);
                    subName.set(i, subName.get(j));
                    subName.set(j, str);
                }
            }
        }
    }

    public static Map<String, List<String>> getNumberOfSubjectAboveAverageMark(Student[] students, Map<String, Integer> subAverageMark) {
        int averageTamil = subAverageMark.get(SubjectName.TAMIL.toString());
        int averageEnglish = subAverageMark.get(SubjectName.ENGLISH.toString());
        int averageMaths = subAverageMark.get(SubjectName.MATHS.toString());
        int averageScience = subAverageMark.get(SubjectName.SCIENCE.toString());
        int averageSocial = subAverageMark.get(SubjectName.SOCIAL_SCIENCE.toString());

        Map<String, List<String>> numberOfAboveAverageMark = new HashMap<>();
        List<String> subName = null;

        for (int i = 0; i < students.length; ++i) {
            subName = new ArrayList<>();
            for (int j = 0; j < students[i].getSubjects().size(); ++j) {
                if (students[i].getSubjects().get(j).getSubjectName().equals(SubjectName.TAMIL) && students[i].getSubjects().get(j).getMarks() > averageTamil) {
                    subName.add(SubjectName.TAMIL.toString());
                }else if (students[i].getSubjects().get(j).getSubjectName().equals(SubjectName.ENGLISH) && students[i].getSubjects().get(j).getMarks() > averageEnglish) {
                    subName.add(SubjectName.ENGLISH.toString());
                }else if (students[i].getSubjects().get(j).getSubjectName().equals(SubjectName.MATHS) && students[i].getSubjects().get(j).getMarks() > averageMaths) {
                    subName.add(SubjectName.MATHS.toString());
                }else if (students[i].getSubjects().get(j).getSubjectName().equals(SubjectName.SCIENCE) && students[i].getSubjects().get(j).getMarks() > averageScience) {
                    subName.add(SubjectName.SCIENCE.toString());
                }else if (students[i].getSubjects().get(j).getSubjectName().equals(SubjectName.SOCIAL_SCIENCE) && students[i].getSubjects().get(j).getMarks() > averageSocial) {
                    subName.add(SubjectName.SOCIAL_SCIENCE.toString());
                }
            }
            numberOfAboveAverageMark.put(students[i].getName(), subName);
        }
        return numberOfAboveAverageMark;
    }
}
