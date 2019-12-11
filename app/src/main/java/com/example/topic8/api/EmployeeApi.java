package com.example.topic8.api;

import com.example.topic8.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {
    //retrieving all employee
    @GET("employees")
    Call<List<Employee>> getAllEmployee();
}
