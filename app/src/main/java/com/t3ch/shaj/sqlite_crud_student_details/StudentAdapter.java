package com.t3ch.shaj.sqlite_crud_student_details;

import android.content.Context;
import android.content.DialogInterface;
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

    public StudentAdapter(Context mCtx, int listLayoutRes, List<Student> studentList) {
        super(mCtx, listLayoutRes, studentList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        final Student student = studentList.get(position);


        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewDept = view.findViewById(R.id.textViewDepartment);
        TextView textViewSalary = view.findViewById(R.id.textViewID);
        TextView textViewJoiningDate = view.findViewById(R.id.textViewJoiningDate);


        textViewName.setText(student.getName());
        textViewDept.setText(student.getDept());
        textViewSalary.setText(String.valueOf(student.getRoll()));
        textViewJoiningDate.setText(student.getJoiningDate());


        return view;
    }

}
