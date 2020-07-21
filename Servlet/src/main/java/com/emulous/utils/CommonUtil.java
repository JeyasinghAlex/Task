package com.emulous.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.emulous.model.Student;
import com.emulous.model.Subject;

public class CommonUtil {
	
    public static void sort(List<Student> students) {
        Collections.sort(students, new Comparator<Student>() {
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
                } else if (sum1 == sum2) {
                    return 0;
                }
                return -1;
            }
        });
    }
    
    
    public static boolean isPass(Student student) {
        for (int i = 0; i < student.getSubjects().size(); ++i) {
            if (student.getSubjects().get(i).getMark() < 35) {
                return false;
            }
        }
        return true;
    }

}
