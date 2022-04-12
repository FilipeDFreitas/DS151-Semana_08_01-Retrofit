package com.example.semana08_01.apiCEP;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                //.baseUrl("https://viacep.com.br/ws/")
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public CEPService getCEPService(){
        return this.retrofit.create(CEPService.class);
    }
    public DataService getDataService(){
        return this.retrofit.create(DataService.class);
    }
}
