package com.example.ging.starter_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPertumbuhan extends Fragment {

    private RecyclerView recyclerView;
    private AdapterPertumbuhan mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public FragmentPertumbuhan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pertumbuhan,container,false);
        getActivity().setTitle("Perumbuhan Anak");
        recyclerView = (RecyclerView) view.findViewById(R.id.pertumbuhan_recycleview);
        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterPertumbuhan(container.getContext());
        recyclerView.setAdapter(mAdapter);
        prepareItem();

        FloatingActionButton fab = view.findViewById(R.id.btn_add_pertumbuhan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Pertumbuhan Anak", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;
    }

    public void prepareItem() {
        mAdapter.add(100.0,13.0,14.6,"normal", "normal", "normal", "normal");
        mAdapter.add(100.0,13.0,14.6,"normal", "tinggi", "gemuk", "hidrosepalus");
    }



}
