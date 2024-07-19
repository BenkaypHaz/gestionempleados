package com.grupo4.proyecto.repository;

import com.grupo4.proyecto.data.EmpleadosResponse;
import com.grupo4.proyecto.data.FormacionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DatabaseRepository{
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/201930040018/appgestion/Empleados")
	Call<EmpleadosResponse> getEmpleados();
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/201930040018/appgestion/Formacion")
	Call<FormacionResponse> getFormacion();
} 