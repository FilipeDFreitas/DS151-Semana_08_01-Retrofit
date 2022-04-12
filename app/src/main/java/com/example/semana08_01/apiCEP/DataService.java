package com.example.semana08_01.apiCEP;



import com.example.semana08_01.Model.Photo;
import com.example.semana08_01.Model.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("/photos")
    Call<List<Photo>> getPhotos();

    @GET("/posts")
    Call<Post> getPost();
}

