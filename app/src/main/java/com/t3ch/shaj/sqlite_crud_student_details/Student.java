package com.t3ch.shaj.sqlite_crud_student_details;

/**
 * Created by Shakil Ahmed Shaj on 05,January,2019
 * shakilahmedshaj@gmail.com
 */
public class Student {

    int id,roll;
    String name, dept, joiningDate;


    public Student(int id, String name, String dept, String joiningDate, int roll) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.joiningDate = joiningDate;
        this.roll = roll;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public int getRoll() {
        return roll;
    }
}
