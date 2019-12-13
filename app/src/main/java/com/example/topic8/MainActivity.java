package com.example.topic8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topic8.adapter.ContactAdapter;
import com.example.topic8.api.EmployeeApi;
import com.example.topic8.model.Employee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView textView;
    private static final String base_url = "http://dummy.restapiexample.com/api/v1/";
private Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
               finish();
            }
        });



        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//Interface instance

        final EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
        Call<List<Employee>> Listcall = employeeApi.getAllEmployee();
//        asynchronous call
        Listcall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (response.body().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                    } else {
                        List<Employee> lstEmployee = response.body();
                        ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this, lstEmployee);
                        textView.setAdapter(contactAdapter);
                        textView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                }

            }


//                List<Employee> lstEmployee = response.body();
//                Log.d("Loo",lstEmployee+"");
//                ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this,lstEmployee);
//                textView.setAdapter(contactAdapter);
//                textView.setLayoutManager( new LinearLayoutManager(MainActivity.this));
//                List<Employee> employeeList = new ArrayList<>();
//                employeeList.add(new Employee())
//employeeList.add(new Employee)
            // for (Employee employees : lstEmployee) {
            // employeeList.add(new Employee( getId() ));
//                    String content = "";
//                    content += "ID:" + employee.getId() + "\n";
//                    content += "employee_name:" + employee.getEmployee_name() + "\n";
//                    content += "employee_salary:" + employee.getEmployee_Salary() + "\n";
//                    content += "employee_age:" + employee.getEmployee_age() + "\n";
//                    textView.append(content);

            //}


            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
