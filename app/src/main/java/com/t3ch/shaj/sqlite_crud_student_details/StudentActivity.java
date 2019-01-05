package com.t3ch.shaj.sqlite_crud_student_details;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    List<Student> studentList;
    dbManager mDatabase;
    ListView listViewStudents;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        listViewStudents = (ListView) findViewById(R.id.listViewStudents);
        studentList = new ArrayList<>();

        //opening the database
        mDatabase = new dbManager(this);

        //display the students in the list
        showStudentsFromDatabase();
    }

    private void showStudentsFromDatabase() {
        //we used rawQuery(sql, selectionargs) for fetching all the students
        Cursor cursorStudents = mDatabase.getAllStudents();

        //if the cursor has some data
        if (cursorStudents.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the student list
                studentList.add(new Student(
                        cursorStudents.getInt(0),
                        cursorStudents.getString(1),
                        cursorStudents.getString(2),
                        cursorStudents.getString(3),
                        cursorStudents.getInt(4)
                ));
            } while (cursorStudents.moveToNext());
        }
        //closing the cursor
        cursorStudents.close();

        //creating the adapter object
        adapter = new StudentAdapter(this, R.layout.list_layout_student, studentList,mDatabase);

        //adding the adapter to listview
        listViewStudents.setAdapter(adapter);
    }
}
