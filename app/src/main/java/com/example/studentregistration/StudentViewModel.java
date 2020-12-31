package com.example.studentregistration;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    StudentRepository repository;
    LiveData<List<Student>> students;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        students = repository.getAllStudents();
    }

    public void insert(Student student ){
        repository.insert(student);
    }
    public void update(Student student ){
        repository.update(student);
    }
    public void delete(Student student ){
        repository.delete(student);
    }
    public void deleteAll(){
        repository.deleteAll();
    }
    public LiveData<List<Student>> getAllStudents(){
        return students;
    }

}
