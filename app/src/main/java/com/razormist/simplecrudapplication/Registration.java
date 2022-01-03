package com.razormist.simplecrudapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    Database database;
    EditText et_regfirstname, et_reglastname, et_regaddress;
    Button btn_regsave, btn_regback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        database = new Database(this);

        et_regfirstname = (EditText)findViewById(R.id.et_firstname);
        et_reglastname = (EditText)findViewById(R.id.et_lastname);
        et_regaddress = (EditText)findViewById(R.id.et_address);
        btn_regsave = (Button)findViewById(R.id.btn_regsave);
        btn_regback = (Button)findViewById(R.id.btn_regback);

        btn_regsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = et_regfirstname.getText().toString();
                String lastname = et_reglastname.getText().toString();
                String address = et_regaddress.getText().toString();

                if(!firstname.equals("") || !lastname.equals("") || !address.equals("")){

                    Employee employee = new Employee();
                    employee.setFirstname(firstname);
                    employee.setLastname(lastname);
                    employee.setAddress(address);

                    if (database.InsertData(employee)) {
                        Toast.makeText(Registration.this, "Successfully Inserted Data", Toast.LENGTH_SHORT).show();
                        et_regfirstname.setText("");
                        et_reglastname.setText("");
                        et_regaddress.setText("");
                    }
                }else{
                    Toast.makeText(Registration.this, "Please complete the required field!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_regback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
