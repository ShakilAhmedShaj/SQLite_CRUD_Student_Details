package com.t3ch.shaj.sqlite_crud_student_details;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Shakil Ahmed Shaj on 05,January,2019
 * shakilahmedshaj@gmail.com
 */
public class StudentAdapter extends ArrayAdapter<Student> {

    Context mCtx;
    int listLayoutRes;
    List<Student> studentList;
    SQLiteDatabase mDatabase;

    public StudentAdapter(Context mCtx, int listLayoutRes, List<Student> studentList,SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, studentList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.studentList = studentList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        final Student student = studentList.get(position);


        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewDept = view.findViewById(R.id.textViewDepartment);
        TextView textViewID = view.findViewById(R.id.textViewID);
        TextView textViewJoiningDate = view.findViewById(R.id.textViewJoiningDate);


        textViewName.setText(student.getName());
        textViewDept.setText(student.getDept());
        textViewID.setText(String.valueOf(student.getRoll()));
        textViewJoiningDate.setText(student.getJoiningDate());

        Button buttonEdit = view.findViewById(R.id.buttonEditStudent);

        //adding a clicklistener to button
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudent(student);
            }
        });


        return view;
    }

    private void updateStudent(final Student student) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_student, null);
        builder.setView(view);


        final EditText editTextName = view.findViewById(R.id.editTextName);
        final EditText editTextID = view.findViewById(R.id.editTextID);
        final Spinner spinnerDepartment = view.findViewById(R.id.spinnerDepartment);

        editTextName.setText(student.getName());
        editTextID.setText(String.valueOf(student.getRoll()));

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.buttonUpdateStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String roll = editTextID.getText().toString().trim();
                String dept = spinnerDepartment.getSelectedItem().toString();

                if (name.isEmpty()) {
                    editTextName.setError("Name can't be blank");
                    editTextName.requestFocus();
                    return;
                }

                if (roll.isEmpty()) {
                    editTextID.setError("ID can't be blank");
                    editTextID.requestFocus();
                    return;
                }

                String sql = "UPDATE students \n" +
                        "SET name = ?, \n" +
                        "department = ?, \n" +
                        "roll = ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{name, dept,roll, String.valueOf(student.getId())});
                Toast.makeText(mCtx, "Student Updated", Toast.LENGTH_SHORT).show();
                reloadStudentsFromDatabase();

                dialog.dismiss();
            }
        });
    }

    private void reloadStudentsFromDatabase() {
        Cursor cursorStudents = mDatabase.rawQuery("SELECT * FROM students", null);
        if (cursorStudents.moveToFirst()) {
            studentList.clear();
            do {
                studentList.add(new Student(
                        cursorStudents.getInt(0),
                        cursorStudents.getString(1),
                        cursorStudents.getString(2),
                        cursorStudents.getString(3),
                        cursorStudents.getInt(4)
                ));
            } while (cursorStudents.moveToNext());
        }
        cursorStudents.close();
        notifyDataSetChanged();
    }

}
