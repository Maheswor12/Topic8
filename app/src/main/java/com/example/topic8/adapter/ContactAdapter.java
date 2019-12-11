package com.example.topic8.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topic8.R;
import com.example.topic8.model.Employee;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    Context mContext;
    List<Employee> employeeList;

    public ContactAdapter(Context mContext, List<Employee> employeeList) {
        this.mContext = mContext;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contactview,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Employee employee    = employeeList.get(position);
        holder.id.setText(employee.getId());
        holder.name.setText(employee.getEmployee_name());
        holder.salary.setText(employee.getEmployee_Salary());
        holder.age.setText(employee.getEmployee_age());


    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, salary, age;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idRec);
            name = itemView.findViewById(R.id.nameRec);
            salary = itemView.findViewById(R.id.salaryRec);
            age = itemView.findViewById(R.id.ageRec);
        }
    }
}
