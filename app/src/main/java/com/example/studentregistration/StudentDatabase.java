package com.example.studentregistration;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabase.Callback;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class},version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public static StudentDatabase instance;



    public static synchronized StudentDatabase getInstance(Context context){
        if (instance == null ){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StudentDatabase.class,"student database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static Callback callback= new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsyncTask(instance).execute();
        }
    };
    private static class populateDbAsyncTask extends AsyncTask<Void , Void, Void> {
        private StudentDao dao;
        private populateDbAsyncTask(StudentDatabase db){
            dao = db.studentDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            dao.insert(new Student("Atr/7567/11","Yishak Wondim",3.9,"addisababa","software engneering"));
            dao.insert(new Student("Atr/7545/11","Abebe Kebede",2.9,"jimma","mechanical engneering"));
            dao.insert(new Student("Atr/7542/11","Tilahun Yishak",3.4,"hawassa","electrical engneering"));
            return null;
        }
    }
}
