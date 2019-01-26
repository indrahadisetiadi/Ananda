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

    @FormUrlEncoded
    @POST("getanak")
    Call<ResponseBody> getanak(@Field("id_parent") String id_parent);

    @FormUrlEncoded
    @POST("createanak")
    Call<ResponseBody> createanak(@Field("id_parent") String id_parent,
                                  @Field("nama") String nama,
                                  @Field("gender") String gender,
                                  @Field("tanggal_lahir") String tanggal_lahir);

    @FormUrlEncoded
    @POST("getpertumbuhan")
    Call<ResponseBody> getpertumbuhan(@Field("id_anak") Integer id_anak);

    @FormUrlEncoded
    @POST("pertumbuhancalculate")
    Call<ResponseBody> pertumbuhancalculate(@Field("id_parent") String id_parent,
                                  @Field("id_anak") Integer id_anak,
                                  @Field("sex") String sex,
                                  @Field("umur") Integer umur,
                                  @Field("berat") Double berat,
                                  @Field("tinggi") Double tinggi,
                                  @Field("lingkar_kepala") Double lingkar_kepala);

}
