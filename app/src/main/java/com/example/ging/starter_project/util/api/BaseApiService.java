package com.example.ging.starter_project.util.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface BaseApiService {
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> register(@Field("email") String email,
                              @Field("nama") String nama,
                              @Field("password") String password,
                              @Field("password_confirmation") String password_confirmation);
    @FormUrlEncoded
    @POST("signin")
    Call<ResponseBody> signin(@Field("email") String email,
                                @Field("password") String password);



}
