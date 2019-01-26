package com.example.ging.starter_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.example.ging.starter_project.model.Users;
import com.example.ging.starter_project.util.CustomTime;
import com.example.ging.starter_project.util.FragmentHelper;
import com.example.ging.starter_project.util.api.BaseApiService;
import com.example.ging.starter_project.util.api.UtilsApi;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.example.ging.starter_project.util.SessionManager;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPertumbuhan extends Fragment {

    Dialog AddDialog;
    private RecyclerView recyclerView;
    private AdapterPertumbuhan mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SessionManager sessionManager;
    FloatingActionMenu menu_information;
    FloatingActionButton btn_add_pertumbuhan;
    FloatingActionButton btn_anjuran_gizi;
    BaseApiService mApiService;
    ProgressDialog loading;

    public FragmentPertumbuhan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pertumbuhan,container,false);

        sessionManager = new SessionManager(getContext());
        ((MainActivity) getActivity()).getSupportActionBar().show();
        getActivity().setTitle("Pertumbuhan Anak - " + sessionManager.getNamaAnak());
        mApiService = UtilsApi.getAPIService();
        recyclerView = (RecyclerView) view.findViewById(R.id.pertumbuhan_recycleview);
        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterPertumbuhan(container.getContext());
        recyclerView.setAdapter(mAdapter);
        AddDialog = new Dialog(getContext());
        prepareItem();

        menu_information = (FloatingActionMenu)view.findViewById(R.id.menu_pertumbuhan);
        btn_add_pertumbuhan = (FloatingActionButton)view.findViewById(R.id.btn_add_pertumbuhan);
        btn_add_pertumbuhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAddFormPertumbuhan(view);
            }
        });
        btn_anjuran_gizi = (FloatingActionButton)view.findViewById(R.id.btn_informasi_pertumbuhan);
        btn_anjuran_gizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.changeFragment(getFragmentManager(),R.id.fragment_container,new FragmentAnjjuranGizi());
            }
        });
        return view;
    }

    private void viewAddFormPertumbuhan(View view) {
        final TextView btn_add_pertumbuhan,btnCloseAddForm;
        final EditText txt_form_pertumbuhan_tinggi,txt_form_pertumbuhan_beratbadan,txt_form_pertumbuhan_lingkar_kepala;

        AddDialog.setContentView(R.layout.popup_form_add_pertumbuhan);
        btnCloseAddForm =(TextView) AddDialog.findViewById(R.id.btnCloseAddFormPertumbuhan);
        btnCloseAddForm.setText("X");
        txt_form_pertumbuhan_tinggi =(EditText) AddDialog.findViewById(R.id.txt_form_pertumbuhan_tinggi);
        txt_form_pertumbuhan_beratbadan = (EditText) AddDialog.findViewById(R.id.txt_form_pertumbuhan_beratbadan);
        txt_form_pertumbuhan_lingkar_kepala = (EditText)AddDialog.findViewById(R.id.txt_form_pertumbuhan_lingkar_kepala);
        btn_add_pertumbuhan = (TextView)AddDialog.findViewById(R.id.btn_add_pertumbuhan_pop);

        btn_add_pertumbuhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
//                Log.d("id_anak",Integer.toString(sessionManager.getIdAnak()));
                mApiService.pertumbuhancalculate(
                        Users.getId_user(),
                        sessionManager.getIdAnak(),
                        sessionManager.getGenderAnak(),
                        sessionManager.getUmurAnak(),
                        Double.parseDouble(txt_form_pertumbuhan_beratbadan.getText().toString()),
                        Double.parseDouble(txt_form_pertumbuhan_tinggi.getText().toString()),
                        Double.parseDouble(txt_form_pertumbuhan_lingkar_kepala.getText().toString())
                )
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    loading.dismiss();
                                    try
                                    {

                                        String rawresponse;
                                        rawresponse = response.body().string();
                                        JSONObject jsonObject = new JSONObject(rawresponse);
//                                Log.d("JSON A", jsonObject.getString("success_login"));
//                                Log.d("errorna asup te",rawresponse);
                                        if (jsonObject.has("message")) {
                                            // Jika login berhasil maka data nama yang ada di response API
                                            // akan diparsing ke activity selanjutnya.
                                            JSONObject data = jsonObject.getJSONObject("message");
                                            mAdapter.add(
                                                    Double.parseDouble(txt_form_pertumbuhan_beratbadan.getText().toString()),
                                                    Double.parseDouble(txt_form_pertumbuhan_tinggi.getText().toString()),
                                                    Double.parseDouble(txt_form_pertumbuhan_lingkar_kepala.getText().toString()),
                                                    data.getString("bbu"),
                                                    data.getString("pbu"),
                                                    data.getString("bbt"),
                                                    data.getString("lku"),
                                                    data.getString("created")
                                            );
                                            mAdapter.notifyItemInserted(mAdapter.getItemCount()+1);
                                            loading.dismiss();
                                            AddDialog.dismiss();
                                            MDToast.makeText(getContext(), "Data Perumbuhan Telah Ditambahkan", MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();

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

        btnCloseAddForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDialog.dismiss();
            }
        });
        AddDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AddDialog.show();
    }

    public void prepareItem() {
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
        Log.d("id_anak",Integer.toString(sessionManager.getIdAnak()));
        mApiService.getpertumbuhan(sessionManager.getIdAnak())
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
                                Log.d("errorna asup te",rawresponse);
                                if (jsonObject.has("pertumbuhan")) {
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.

                                    JSONArray array = jsonObject.getJSONArray("pertumbuhan");

                                    for (int i=0; i<array.length();i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        mAdapter.add(
                                                object.getDouble("berat"),
                                                object.getDouble("tinggi"),
                                                object.getDouble("lingkar_kepala"),
                                                object.getString("bbu"),
                                                object.getString("pbu"),
                                                object.getString("bbt"),
                                                object.getString("lku"),
                                                object.getString("created"));
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





}
