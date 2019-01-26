package com.example.ging.starter_project;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ging.starter_project.util.FragmentHelper;
import com.example.ging.starter_project.util.SessionManager;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class FragmentPerkembangan extends Fragment {

    Dialog AddDialog;
    private RecyclerView recyclerView;
    private AdapterPerkembangan mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SessionManager sessionManager;
    FloatingActionMenu menu_information;
    FloatingActionButton btn_add_perkembangan;
    FloatingActionButton btn_informasi_perkembangan;

    public FragmentPerkembangan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_perkembangan,container,false);

        sessionManager = new SessionManager(getContext());
        ((MainActivity) getActivity()).getSupportActionBar().show();
        getActivity().setTitle("Perkembangan Anak - " + sessionManager.getNamaAnak());
        recyclerView = (RecyclerView) view.findViewById(R.id.perkembangan_recycleview);
        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterPerkembangan(container.getContext());
        recyclerView.setAdapter(mAdapter);
        AddDialog = new Dialog(getContext());
        prepareItem();

        menu_information = (FloatingActionMenu)view.findViewById(R.id.menu_perkembangan);
        btn_add_perkembangan = (FloatingActionButton)view.findViewById(R.id.btn_add_perkembangan);
        btn_add_perkembangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentHelper.changeFragment(getFragmentManager(),R.id.fragment_container,new FragmentKuisioner());
            }
        });
        btn_informasi_perkembangan = (FloatingActionButton)view.findViewById(R.id.btn_informasi_perkembangan);
        btn_informasi_perkembangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.changeFragment(getFragmentManager(),R.id.fragment_container,new FragmentAnjjuranGizi());
            }
        });
        return view;
    }


    public void prepareItem() {
        mAdapter.add("normal","2019-01-05");
        mAdapter.add("meragukan","2018-12-01");
        mAdapter.add("menyimpang","2018-12-01");
    }


}