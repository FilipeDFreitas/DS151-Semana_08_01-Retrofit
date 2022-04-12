package com.example.semana08_01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.semana08_01.Model.Address;
import com.example.semana08_01.Model.Photo;
import com.example.semana08_01.apiCEP.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView output;
    TextView input;
    List<Photo> PhotoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.output);
        input = findViewById(R.id.cep);

    }
    public void getListRetrofit(View view){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando fotos...");
        progressDialog.show();

        Call<List<Photo>> call =
                new RetrofitConfig().getDataService().getPhotos();

        call.enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    PhotoList = response.body();
                    for(int i = 0;i<50;i++){
                        Photo photo = PhotoList.get(i);
                        output.setText(output.getText() + "\n" + photo.getId());
                    }

                    progressDialog.dismiss();
                    //output.setText(address.getLogradouro() +" - "+ address.getComplemento()+ "\n"
                      //      + address.getBairro() + "\n" + address.getLocalidade()+" - "+address.getUf());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }
    public void getData (View view){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando endereço...");
        progressDialog.show();

        Call<Address> call = new RetrofitConfig().getCEPService().getFullAddress(input.getText().toString());
        call.enqueue(new Callback<Address>() {

            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
               if(response.isSuccessful()){
                   Address address = response.body();
                   progressDialog.dismiss();
                   output.setText(address.getLogradouro() +" - "+ address.getComplemento()+ "\n"
                           + address.getBairro() + "\n" + address.getLocalidade()+" - "+address.getUf());
               }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
    }
}