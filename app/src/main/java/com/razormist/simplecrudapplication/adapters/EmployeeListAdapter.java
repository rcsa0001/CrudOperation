package com.razormist.simplecrudapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.razormist.simplecrudapplication.Employee;
import com.razormist.simplecrudapplication.R;

import org.w3c.dom.Text;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Arvin on 3/28/2018.
 */

public class EmployeeListAdapter extends ArrayAdapter<Employee> {

    private  Context context;
    private List<Employee>employees;

    public EmployeeListAdapter(Context context, List<Employee> employees){
        super(context, R.layout.employee_list, employees);
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = layoutInflater.inflate(R.layout.employee_list, parent, false);
        TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
        TextView tv_address = (TextView)view.findViewById(R.id.tv_address);
        tv_name.setText("Name: " + employees.get(position).getFirstname() + " " + employees.get(position).getLastname());
        tv_address.setText("Address: " + employees.get(position).getAddress());

        return view;
    }


}
