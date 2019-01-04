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
import com.example.ging.starter_project.util.SessionManager;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile,container,false);
        getActivity().setTitle("Profile Anak");
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
        mAdapter.add(1,"Budi Sudarsono","Laki-Laki",24);
        mAdapter.add(2,"Susi Susanti","Perempuan",34);
        mAdapter.add(4,"Alam Bahrul","Laki-Laki",14);
        mAdapter.add(3,"N'golo Kante","Laki-Laki",44);
        mAdapter.add(3,"Bini Yani","Perempuan",90);
    }

    public void viewAddFormAnak(View v) {
        final TextView btn_add_anak,btnCloseAddForm,txt_form_date_birth_anak;
        RadioButton rdLaki,rdPerempuan;
        final EditText txt_form_nama_anak;

        final Calendar myCalendar;
        myCalendar = Calendar.getInstance();

        AddDialog.setContentView(R.layout.popup_form_add_anak);
        btnCloseAddForm =(TextView) AddDialog.findViewById(R.id.btnCloseAddForm);
        btnCloseAddForm.setText("X");
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

                        String formatTanggal = "dd-MM-yyyy";
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
        AddDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AddDialog.show();
    }



}