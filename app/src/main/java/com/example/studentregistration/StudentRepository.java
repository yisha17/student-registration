package com.example.studentregistration;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    Application application;
    StudentDao studentDao;
    LiveData<List<Student>> students;

    StudentRepository(Application application){
        StudentDatabase db = StudentDatabase.getInstance(application);
        studentDao = db.studentDao();
        students = studentDao.getAllStudents();
    }

    public void insert(Student student){
        new insertAsyncTask(studentDao).execute(student);
    }
    public void update(Student student){
        new updateAsyncTask(studentDao).execute(student);
    }
    public void delete(Student student){
        new deleteAsyncTask(studentDao).execute(student);
    }
    public void deleteAll(){
        new deleteAllAsyncTask(studentDao).execute();
    }

    public LiveData<List<Student>> getAllStudents(){
        return students;
    }

    private static class insertAsyncTask extends AsyncTask<Student,Void,Void>{
        StudentDao dao;
        insertAsyncTask(StudentDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.insert(students[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Student,Void,Void>{
        StudentDao dao;
       updateAsyncTask(StudentDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.update(students[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Student,Void,Void>{
        StudentDao dao;
        deleteAsyncTask(StudentDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.delete(students[0]);
            return null;
        }
    }
    private static class deleteAllAsyncTask extends AsyncTask<Student,Void,Void>{
        StudentDao dao;
        deleteAllAsyncTask(StudentDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.deleteAll();
            return null;
        }
    }
}
