package com.razormist.simplecrudapplication;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.media.RatingCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.razormist.simplecrudapplication.adapters.EmployeeListAdapter;

import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    Database database;
    EditText et_firstname, et_lastname, et_address;
    Button btn_add, btn_view;
    ListView lv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);

        btn_add = (Button)findViewById(R.id.btn_add);
        lv_list = (ListView)findViewById(R.id.lv_list);

        EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(this, database.DisplayAll());
        employeeListAdapter.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee lhs, Employee rhs) {
                return lhs.getLastname().compareTo(rhs.getLastname());
            }
        });
        lv_list.setAdapter(employeeListAdapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = database.DisplayAll().get(position);
                Intent intent = new Intent(MainActivity.this, EmployeeDetail.class);
                intent.putExtra("employee", employee);
                startActivity(intent);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });


    }





}
