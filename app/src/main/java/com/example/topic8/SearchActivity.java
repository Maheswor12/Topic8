package com.example.topic8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topic8.api.EmployeeApi;
import com.example.topic8.model.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    private static final String base_url = "http://dummy.restapiexample.com/api/v1/";
    private EditText empId;
    private Button btnSearch1;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        empId = findViewById(R.id.empID);
        btnSearch1 = findViewById(R.id.btnSearch1);
        tvData = findViewById(R.id.tvData);

        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
                Call<Employee> listCall = employeeApi.getEmployeebyID(Integer.parseInt(empId.getText().toString()));
                listCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        String content = "";
                        content += "ID:" + response.body().getId() + "\n";
                        content += "employee_name:" + response.body().getEmployee_name() + "\n";
                        content += "employee_salary:" + response.body().getEmployee_Salary() + "\n";
                        content += "employee_age:" + response.body().getEmployee_age() + "\n";
                        tvData.setText(content);
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}
