package com.example.studentregistration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST = 1;
    public static final int EDIT_REQUEST = 2;

    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    StudentViewModel viewModel;
    List<Student> students = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);


        final StudentAdapter adapter = new StudentAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(StudentViewModel.class);
        viewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                adapter.setList(students);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, AddEditStudent.class);
               startActivityForResult(intent,REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Deleting A Student");
                alertDialog.setMessage("Are you sure ou want to delete?");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = viewHolder.getAdapterPosition();
                        adapter.notifyItemChanged(position);
                        viewModel.delete(adapter.students.get(position));

                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();




            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student student) {
                Intent intent  = new Intent(MainActivity.this, AddEditStudent.class);
                intent.putExtra(AddEditStudent.NAME,student.getName());
                intent.putExtra(AddEditStudent.ID,student.getId());
                intent.putExtra(AddEditStudent.DEPARTMENT,student.getDepartment());
                intent.putExtra(AddEditStudent.SCHOOL,student.getSchool());
                intent.putExtra(AddEditStudent.GPA,student.getGpa());
                startActivityForResult(intent,EDIT_REQUEST);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tasks,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteAll:
                viewModel.deleteAll();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST){
            String name = data.getStringExtra(AddEditStudent.NAME);
            String id = data.getStringExtra(AddEditStudent.ID);
            double gpa = data.getDoubleExtra(AddEditStudent.GPA,0.0);
            String department = data.getStringExtra(AddEditStudent.DEPARTMENT);
            String school = data.getStringExtra(AddEditStudent.SCHOOL);

            Student student = new Student(id,name,gpa,school,department);

            viewModel.insert(student);
            Toast.makeText(this, "Student Registered", Toast.LENGTH_SHORT).show();
        }else if (requestCode == EDIT_REQUEST){

            String name = data.getStringExtra(AddEditStudent.NAME);
            String id = data.getStringExtra(AddEditStudent.ID);
            double gpa = data.getDoubleExtra(AddEditStudent.GPA,0.0);
            String department = data.getStringExtra(AddEditStudent.DEPARTMENT);
            String school = data.getStringExtra(AddEditStudent.SCHOOL);

            Student student = new Student(id,name,gpa,school,department);

            viewModel.update(student);
        }else {
            Toast.makeText(this, "cant update", Toast.LENGTH_SHORT).show();
        }


    }
}