package com.example.ging.starter_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.ging.starter_project.util.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAnjjuranGizi extends Fragment {


    public FragmentAnjjuranGizi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_anjuran_gizi,container,false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        Toolbar toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Title");
        toolbar.setNavigationIcon(R.drawable.left_arrow);
        toolbar.setTitle("Informasi Anjuran Gizi");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.changeFragment(getFragmentManager(),R.id.fragment_container,new FragmentPertumbuhan());
            }
        });
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getActivity(), "Back from fragment", Toast.LENGTH_SHORT).show();
        return true;
    }
}
