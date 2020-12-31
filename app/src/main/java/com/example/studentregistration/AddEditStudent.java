package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class AddEditStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String NAME = "fwfhgw";
    public static final String GPA = "fwftghw";
    public static final String SCHOOL = "fmn fhgw";
    public static final String ID = "fwfmjjhw";
    public static final String DEPARTMENT = "fwf,jk,w";
    EditText edtName,edtGpa,edtId,edtSchool;
    Spinner chooseDepartment;
    String department;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        edtName = findViewById(R.id.edtName);
        edtGpa = findViewById(R.id.edtGpa);
        edtId = findViewById(R.id.edtId);
        edtSchool = findViewById(R.id.edtSchool);

        chooseDepartment = findViewById(R.id.chooseDepartment);

        getActionBar();
        Intent intent = getIntent();
        if (intent.hasExtra(ID)){
            setTitle("Edit Student");
            edtName.setText(intent.getStringExtra(NAME));
            edtId.setText(intent.getStringExtra(ID));
            edtSchool.setText(intent.getStringExtra(SCHOOL));
            edtGpa.setText(intent.getStringExtra(GPA));
        }else{
            setTitle("Add Student");
        }


        //assigning spinner components
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
                .createFromResource(this,R.array.Department,android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (chooseDepartment != null){
            chooseDepartment.setOnItemSelectedListener(this);
            chooseDepartment.setAdapter(adapter2);
        }
        //setting item click listener

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        department = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        department = getResources().getStringArray(R.array.Department)[6];
    }

    public void register(View view){
        Intent intent = new Intent();
        if(edtName.equals("") || edtId.equals("") || edtGpa.equals("") || edtSchool.equals("")){
            Toast.makeText(this, "please fill all areas" , Toast.LENGTH_SHORT).show();
        }else {
            intent.putExtra(NAME,edtName.getText().toString());
            intent.putExtra(DEPARTMENT,department);
            intent.putExtra(ID,edtId.getText().toString());
            intent.putExtra(GPA,Double.parseDouble(edtGpa.getText().toString()));
            intent.putExtra(SCHOOL,edtSchool.getText().toString());
            setResult(RESULT_OK,intent);
            finish();

        }

    }

}