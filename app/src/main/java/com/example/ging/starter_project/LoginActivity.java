package com.example.ging.starter_project;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.example.ging.starter_project.model.Users;
import com.example.ging.starter_project.util.SessionManager;
import com.example.ging.starter_project.util.api.BaseApiService;
import com.example.ging.starter_project.util.api.UtilsApi;
import com.google.gson.JsonObject;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
{
    Dialog RegisterDialog;

    @BindView(R.id.txt_email)
    EditText txt_email;
    @BindView(R.id.txt_password)
    EditText txt_password;
    @BindView(R.id.btn_login)
    TextView btn_login;
    ProgressDialog loading;

    BaseApiService mApiService;
    Context mContext;

    JSONObject jsonObject;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Checking for first time launch - before calling setContentView()
        sessionManager = new SessionManager(this);

        if (!sessionManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        RegisterDialog = new Dialog(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

    }

    private void requestLogin(){
        mApiService.signin(txt_email.getText().toString(), txt_password.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {

                                String rawresponse;
                                rawresponse = response.body().string();
                                JSONObject jsonObject = new JSONObject(rawresponse);
//                                Log.d("JSON A", jsonObject.getString("success_login"));
//                                Log.d("errorna asup te",rawresponse);
                                if (jsonObject.has("success_login")) {
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    JSONObject data = jsonObject.getJSONObject("success_login");
                                    Users users = new Users
                                            (data.getString("id"),
                                            data.getString("nama"),
                                            data.getString("email"),
                                            txt_password.getText().toString(),
                                            jsonObject.getString("token"));
                                    sessionManager.setFirstTimeLaunch(false);
                                    sessionManager.login_session(
                                            users.getId_user(),
                                            users.getEmail(),
                                            users.getNama(),
                                            users.getPassword(),
                                            users.getToken());
                                    loading.dismiss();
                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                    launchHomeScreen();
                                } else {
                                    // Jika login gagal
//                                    String error_message = jsonRESULTS.getString("error_msg");
//                                    Log.d("error",jsonObject.getString("error"));
                                    MDToast.makeText(mContext, jsonObject.getString("error"), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
                                    // Toast.makeText(mContext, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.d("teu sukses","aya");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        Log.d("failure","aya");
                        loading.dismiss();
                    }
                });
    }


    private void launchHomeScreen() {
        sessionManager.setFirstTimeLaunch(false);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    public void viewRegister(View v) {
        final TextView txtclose,
                btn_register_pop;
        final EditText txt_email_reg,txt_password_reg,txt_confirm_password_reg,txt_nama_reg;

        RegisterDialog.setContentView(R.layout.popup_register);
        txtclose =(TextView) RegisterDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        txt_nama_reg = (EditText)RegisterDialog.findViewById(R.id.txt_nama_reg);
        txt_email_reg = (EditText)RegisterDialog.findViewById(R.id.txt_email_reg);
        txt_password_reg = (EditText)RegisterDialog.findViewById(R.id.txt_password_reg);
        txt_confirm_password_reg = (EditText)RegisterDialog.findViewById(R.id.txt_confirm_password_reg);
        btn_register_pop = (TextView)RegisterDialog.findViewById(R.id.btn_register_pop);
        btn_register_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                mApiService.register(txt_email_reg.getText().toString(),
                                        txt_nama_reg.getText().toString(),
                                        txt_password_reg.getText().toString(),
                                        txt_confirm_password_reg.getText().toString())
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    loading.dismiss();
                                    try {

                                        String rawresponse;
                                        rawresponse = response.body().string();
                                        JSONObject jsonObject = new JSONObject(rawresponse);
//                                Log.d("JSON A", jsonObject.getString("success_login"));
//                                Log.d("errorna asup te",rawresponse);
                                        if (jsonObject.has("success_login")) {
                                            // Jika login berhasil maka data nama yang ada di response API
                                            // akan diparsing ke activity selanjutnya.
                                            JSONObject data = jsonObject.getJSONObject("success_login");
                                            Users users = new Users
                                                    (data.getString("id"),
                                                            data.getString("nama"),
                                                            data.getString("email"),
                                                            txt_password.getText().toString(),
                                                            jsonObject.getString("token"));
                                            sessionManager.setFirstTimeLaunch(false);
                                            sessionManager.login_session(
                                                    users.getId_user(),
                                                    users.getEmail(),
                                                    users.getNama(),
                                                    users.getPassword(),
                                                    users.getToken());
                                            loading.dismiss();
                                            Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                            launchHomeScreen();
                                        } else {
                                            // Jika login gagal
//                                    String error_message = jsonRESULTS.getString("error_msg");
//                                    Log.d("error",jsonObject.getString("error"));
                                            MDToast.makeText(mContext, jsonObject.getString("error"), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
                                            // Toast.makeText(mContext, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Log.d("teu sukses","aya");
                                    loading.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("debug", "onFailure: ERROR > " + t.toString());
                                Log.d("failure","aya");
                                loading.dismiss();
                            }
                        });
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterDialog.dismiss();
            }
        });
        RegisterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RegisterDialog.show();
    }

}

