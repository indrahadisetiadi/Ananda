package com.example.ging.starter_project.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ging.starter_project.model.Anak;
import com.example.ging.starter_project.model.Users;

public class SessionManager {
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "ananda";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public SessionManager(Context context) {
        this._context = context;
        sharedPref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void login_session(String id_user, String email, String nama, String password, String token) {
        editor.putString("email",email);
        editor.putString("id_user",id_user);
        editor.putString("password",password);
        editor.putString("nama",nama);
        editor.putString("token",token);
        editor.commit();
    }

    public void getData_session() {
        Users users = new Users();
        users.setEmail(sharedPref.getString("email",""));
        users.setPassword(sharedPref.getString("password",""));
        users.setNama(sharedPref.getString("nama",""));
        users.setId_user(sharedPref.getString("id_user",""));
        users.setToken(sharedPref.getString("token",""));
    }

    public void setProfileAnak(Integer id_anak, String nama_anak, String gender_anak, Integer umur_anak) {
        editor.putInt("id_anak_selected",id_anak);
        editor.putString("nama_anak_selected",nama_anak);
        editor.putString("gender_anak_selected",gender_anak);
        editor.putInt("umur_anak_selected",umur_anak);
        editor.commit();
    }

    public int getIdAnak() {

//        Anak anak = new Anak();
//        anak.setId_anak(sharedPref.getInt("id_anak_selected",0));
//        anak.setNama(sharedPref.getString("nama_anak_selected",""));
//        anak.setGender(sharedPref.getString("gender_anak_selected",""));
//        anak.setUmur(sharedPref.getInt("umur_anak_selected",0));
          return sharedPref.getInt("id_anak_selected",0);
    }

    public String getNamaAnak() {
        return sharedPref.getString("nama_anak_selected","");
    }

    public String getGenderAnak() {
        return sharedPref.getString("gender_anak_selected","");
    }

    public Integer getUmurAnak() {
       return sharedPref.getInt("umur_anak_selected",0);
    }



}
