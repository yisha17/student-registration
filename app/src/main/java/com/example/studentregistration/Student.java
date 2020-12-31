package com.example.studentregistration;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {

    @PrimaryKey
    @NonNull
    String id;
    @NonNull
    String name;

    double gpa;
    @NonNull
    String school;
    @NonNull
    String department;

    public Student(@NonNull String id, @NonNull String name, double gpa, @NonNull String school, @NonNull String department) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.school = school;
        this.department = department;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public double getGpa() {
        return gpa;
    }

    @NonNull
    public String getSchool() {
        return school;
    }

    @NonNull
    public String getDepartment() {
        return department;
    }
}
