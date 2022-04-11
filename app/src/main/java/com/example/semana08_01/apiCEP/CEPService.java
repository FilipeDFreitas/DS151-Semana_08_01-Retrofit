package com.example.semana08_01.apiCEP;

import com.example.semana08_01.Model.Address;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json")
    Call<Address> getFullAddress(@Path("cep") String cep);
}
