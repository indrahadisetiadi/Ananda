package com.example.ging.starter_project.util.api;

public class UtilsApi {
    public static final String BASE_URL_API = "http://192.168.43.98/ananda/public/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
