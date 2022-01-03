package com.razormist.simplecrudapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateEmployee extends AppCompatActivity {
    private EditText et_ufirstname, et_ulastname, et_uaddress;
    private Button btn_back, btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);
        final Database db = new Database(getBaseContext());
        final Employee employee = (Employee)getIntent().getSerializableExtra("employee");
        et_ufirstname = (EditText)findViewById(R.id.et_ufirstname);
        et_ulastname = (EditText)findViewById(R.id.et_ulastname);
        et_uaddress = (EditText)findViewById(R.id.et_uaddress);
        this.btn_back = (Button)findViewById(R.id.btn_back);
        btn_update = (Button)findViewById(R.id.btn_update);

        et_ufirstname.setText(employee.getFirstname());
        et_ulastname.setText(employee.getLastname());
        et_uaddress.setText(employee.getAddress());
        et_ufirstname.setSelection(et_ufirstname.getText().length());
        et_ulastname.setSelection(et_ulastname.getText().length());
        et_uaddress.setSelection(et_uaddress.getText().length());

        this.btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateEmployee.this, EmployeeDetail.class);
                intent.putExtra("employee", employee);
                startActivity(intent);
            }
        });

       btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = et_ufirstname.getText().toString();
                String lastname = et_ulastname.getText().toString();
                String address = et_uaddress.getText().toString();

                employee.setFirstname(firstname);
                employee.setLastname(lastname);
                employee.setAddress(address);


                if(db.Update(employee)){
                    //Toast.makeText(UpdateEmployee.this, "SUCCESS!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("System");
                    builder.setMessage("SUCCESSFULLY UPDATED");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(UpdateEmployee.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.create().show();
                }else{
                    Toast.makeText(UpdateEmployee.this, "FAILED!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
