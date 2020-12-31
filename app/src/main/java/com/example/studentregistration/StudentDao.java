package com.example.studentregistration;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("DELETE FROM student_table ")
    void deleteAll();

    @Query("SELECT * FROM student_table ORDER BY gpa ASC")
    LiveData<List<Student>> getAllStudents();


}
