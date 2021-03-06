package com.t3ch.shaj.sqlite_crud_student_details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "mystudentdatabase";
    dbManager mDatabase;

    TextView textViewViewStudents;
    EditText editTextName, editTextID;
    Spinner spinnerDepartment;
    Button addStudentBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        mDatabase = new dbManager(this);

        textViewViewStudents = findViewById(R.id.textViewViewStudents);
        editTextName = findViewById(R.id.editTextName);
        editTextID = findViewById(R.id.editTextID);

        spinnerDepartment = findViewById(R.id.spinnerDepartment);

        //addStudentBTN = findViewById(R.id.buttonAddStudent);


        //addStudentBTN.setOnClickListener(this);
        findViewById(R.id.buttonAddStudent).setOnClickListener(this);
        textViewViewStudents.setOnClickListener(this);




    }


    private boolean inputsAreCorrect(String name, String roll) {
        if (name.isEmpty()) {
            editTextName.setError("Please enter a name");
            editTextName.requestFocus();
            return false;
        }

        if (roll.isEmpty() || Integer.parseInt(roll) <= 0) {
            editTextID.setError("Please enter ID");
            editTextID.requestFocus();
            return false;
        }
        return true;
    }

    private void addStudent() {

        String name = editTextName.getText().toString().trim();
        String roll = editTextID.getText().toString().trim();
        String dept = spinnerDepartment.getSelectedItem().toString();

        //getting the current time for joining date
        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        //validating the inptus
        if (inputsAreCorrect(name, roll)) {

            if (mDatabase.addStudent(name, dept, joiningDate,roll))
                Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Could not add Student", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonAddStudent:

                addStudent();

                break;
            case R.id.textViewViewStudents:

                startActivity(new Intent(this, StudentActivity.class));
                
                break;
        }

    }
}
