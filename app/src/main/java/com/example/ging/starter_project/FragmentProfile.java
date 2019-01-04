package com.example.ging.starter_project;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ging.starter_project.model.Anak;
import com.example.ging.starter_project.model.Users;
import com.example.ging.starter_project.util.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentProfile extends Fragment {

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
        prepareItem();

        FloatingActionButton fab = view.findViewById(R.id.btn_add_profile_anak);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Profile Anak", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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


}