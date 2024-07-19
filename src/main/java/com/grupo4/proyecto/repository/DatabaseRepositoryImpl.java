package com.grupo4.proyecto.repository;

import java.io.IOException;

import com.grupo4.proyecto.data.EmpleadosResponse;
import com.grupo4.proyecto.data.FormacionResponse;

import retrofit2.Call;
import retrofit2.Response;


public class DatabaseRepositoryImpl {
private static DatabaseRepositoryImpl INSTANCE;
private DatabaseClient client;

	private DatabaseRepositoryImpl(String url,Long timeout) {
	   client = new DatabaseClient(url,timeout);
	}
	
	public static DatabaseRepositoryImpl getInstance(String url,Long timeout) {
		if(INSTANCE == null) {
			synchronized (DatabaseRepositoryImpl.class) {
				if(INSTANCE == null) {
					INSTANCE = new DatabaseRepositoryImpl(url,timeout);
				}
			}
		}
		return INSTANCE;
	}

    public EmpleadosResponse consultarEmpleados() throws IOException {
    	Call<EmpleadosResponse> call = client.getClient().getEmpleados();
    	Response<EmpleadosResponse> response = call.execute();
    	if(response.isSuccessful()){
    		return response.body();
    	}else {
    		return null;
    	}
    }
    
    public FormacionResponse consultarFormacion() throws IOException {
        Call<FormacionResponse> call = client.getClient().getFormacion();
        Response<FormacionResponse> response = call.execute();
        if (response.isSuccessful()){
            return response.body();
        } else {
            return null;
        }
    }

}