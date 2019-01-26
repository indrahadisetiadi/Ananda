package com.example.ging.starter_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ging.starter_project.model.Anak;
import com.example.ging.starter_project.model.Users;
import com.example.ging.starter_project.util.CustomTime;
import com.example.ging.starter_project.util.SessionManager;
import com.example.ging.starter_project.util.api.BaseApiService;
import com.example.ging.starter_project.util.api.UtilsApi;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {
    Dialog AddDialog;
    private RecyclerView recyclerView;
    private AdapterProfile mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    BaseApiService mApiService;
    SessionManager sessionManager;
    ProgressDialog loading;
    String jenis_kelamin;

    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile,container,false);
        ((MainActivity) getActivity()).getSupportActionBar().show();
        getActivity().setTitle("Profile Anak");
        mApiService = UtilsApi.getAPIService();
        recyclerView = (RecyclerView) view.findViewById(R.id.profile_recycleview);
        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterProfile(container.getContext());
        recyclerView.setAdapter(mAdapter);
        AddDialog = new Dialog(getContext());
        prepareItem();

        FloatingActionButton fab = view.findViewById(R.id.btn_add_profile_anak);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAddFormAnak(view);
            }
        });

        return view;
    }

    public void prepareItem() {
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
        Log.d("id_parent",Users.getId_user());
        mApiService.getanak(Users.getId_user())
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
                                if (jsonObject.has("anak")) {
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.

                                    JSONArray array = jsonObject.getJSONArray("anak");

                                    for (int i=0; i<array.length();i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Date convertedDate = new Date();
                                        Date now = new Date();
                                        try {
                                            convertedDate = dateFormat.parse(object.getString("tanggal_lahir"));
                                            now = dateFormat.parse(CustomTime.getCurrentDate("yyyy-MM-dd"));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        mAdapter.add(object.getInt("id"),object.getString("nama")
                                                ,object.getString("gender"),CustomTime.getMonthdiff(convertedDate,now));
                                        mAdapter.notifyItemInserted(i);
                                    }
                                    loading.dismiss();
                                } else {
                                    // Jika login gagal
//                                    String error_message = jsonRESULTS.getString("error_msg");
//                                    Log.d("error",jsonObject.getString("error"));
                                    MDToast.makeText(getContext(), jsonObject.getString("error"), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
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

    public void viewAddFormAnak(View v) {
        final TextView btn_add_anak,btnCloseAddForm,txt_form_date_birth_anak;
        final RadioButton rdLaki,rdPerempuan;
        final EditText txt_form_nama_anak;
        final Calendar myCalendar;
        myCalendar = Calendar.getInstance();
        AddDialog.setContentView(R.layout.popup_form_add_anak);
        btnCloseAddForm =(TextView) AddDialog.findViewById(R.id.btnCloseAddForm);
        btnCloseAddForm.setText("X");
        rdLaki = (RadioButton)AddDialog.findViewById(R.id.radioLaki_Laki);
        txt_form_nama_anak =(EditText) AddDialog.findViewById(R.id.txt_form_nama_anak);
        txt_form_date_birth_anak = (TextView)AddDialog.findViewById(R.id.txt_form_date_birth_anak);
        btn_add_anak = (TextView)AddDialog.findViewById(R.id.btn_add_anak_pop);

        txt_form_date_birth_anak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        txt_form_date_birth_anak.setText(sdf.format(myCalendar.getTime()));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnCloseAddForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDialog.dismiss();
            }
        });

        btn_add_anak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdLaki.isChecked()) {
                    jenis_kelamin="Laki-Laki";
                }
                else {
                    jenis_kelamin="Perempuan";
                }
                mApiService.createanak(
                        Users.getId_user(),txt_form_nama_anak.getText().toString(),
                        jenis_kelamin,txt_form_date_birth_anak.getText().toString())
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
                                        if (jsonObject.has("message")) {
                                            // Jika login berhasil maka data nama yang ada di response API
                                            // akan diparsing ke activity selanjutnya.

                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Date convertedDate = new Date();
                                            Date now = new Date();
                                            try {
                                                convertedDate = dateFormat.parse(txt_form_date_birth_anak.getText().toString());
                                                now = dateFormat.parse(CustomTime.getCurrentDate("yyyy-MM-dd"));
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            JSONObject data = jsonObject.getJSONObject("message");
                                            mAdapter.add(data.getInt("id"),txt_form_nama_anak.getText().toString(),jenis_kelamin,CustomTime.getMonthdiff(convertedDate,now));
                                            mAdapter.notifyItemInserted(mAdapter.getItemCount()+1);
                                            loading.dismiss();
                                            AddDialog.dismiss();
                                            MDToast.makeText(getContext(), "Data Anak Telah Ditambahkan", MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
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
        AddDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AddDialog.show();
    }


}