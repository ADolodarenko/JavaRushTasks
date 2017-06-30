package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public List<Student> getStudents()
    {
        return students;
    }
    
    public void setStudents(List<Student> students)
    {
        this.students = students;
    }
    
    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students)
            if (student.getAverageGrade() == averageGrade)
                return student;
        
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxAverageGrade = Double.MIN_VALUE;
        
        for (Student student : students)
            if (student.getAverageGrade() > maxAverageGrade)
                maxAverageGrade = student.getAverageGrade();
        
        return getStudentWithAverageGrade(maxAverageGrade);
    }
    
    public Student getStudentWithMinAverageGrade()
    {
        double minAverageGrade = Double.MAX_VALUE;
    
        for (Student student : students)
            if (student.getAverageGrade() < minAverageGrade)
                minAverageGrade = student.getAverageGrade();
    
        return getStudentWithAverageGrade(minAverageGrade);
    }
    
    public void expel(Student student)
    {
        students.remove(student);
    }
}