package com.appenjoyer.developer.retrofitexample.rest;

import com.appenjoyer.developer.retrofitexample.model.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Developer on 19/03/2018.
 */

public interface ApiInterface {

    @GET("users/{username}")
    Call<GitHubUser> getUsername(@Path("username") String username);

}
