package com.example.ging.starter_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ging.starter_project.util.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKuisioner extends Fragment {


    public FragmentKuisioner() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_kuisioner_perkembangan,container,false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        Toolbar toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.left_arrow);
        toolbar.setTitle("KPSP");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.changeFragment(getFragmentManager(),R.id.fragment_container,new FragmentPerkembangan());
            }
        });
        return view;
    }

}
