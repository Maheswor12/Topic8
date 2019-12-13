package com.example.topic8.api;

import com.example.topic8.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeApi {
    //retrieving all employee
    @GET("employees")
    Call<List<Employee>> getAllEmployee();

    //    to get id
    @GET("employee/{empID}")
    Call<Employee> getEmployeebyID(@Path("empID") int empId);
}
