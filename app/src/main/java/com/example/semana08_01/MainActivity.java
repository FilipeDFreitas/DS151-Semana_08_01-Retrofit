package com.example.semana08_01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.semana08_01.Model.Address;
import com.example.semana08_01.apiCEP.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView output;
    TextView input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.output);
        input = findViewById(R.id.cep);

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