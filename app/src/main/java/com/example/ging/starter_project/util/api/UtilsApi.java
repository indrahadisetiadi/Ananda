package com.example.ging.starter_project.util.api;

public class UtilsApi {
    public static final String BASE_URL_API = "https://ananda.web.id/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
