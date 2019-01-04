package com.example.ging.starter_project;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ging.starter_project.model.Anak;
import com.example.ging.starter_project.model.Pertumbuhan;
import com.example.ging.starter_project.util.SessionManager;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

public class AdapterPertumbuhan extends RecyclerView.Adapter<AdapterPertumbuhan.MyViewHolder> {

    private Context mContext;
    private ArrayList<Pertumbuhan> data_pertumbuhan;
    private SessionManager sessionManager;
    public AdapterPertumbuhan(Context mContext) {
        this.mContext = mContext;
        data_pertumbuhan = new ArrayList<>();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_nama_pertumbuhan, txt_umur_pertumbuhan, txt_gender_pertumbuhan,
                txt_berat_badan_pertumbuhan, txt_bbu, txt_panjang_badan_pertumbuhan, txt_pbu,
                txt_bbpb, txt_lingkar_kepala_pertumbuhan, txt_lku;
        public LinearLayout linear_decoration;

        public MyViewHolder(View view) {
            super(view);
            txt_nama_pertumbuhan = (TextView) view.findViewById(R.id.txt_nama_pertumbuhan);
            txt_umur_pertumbuhan = (TextView) view.findViewById(R.id.txt_umur_pertumbuhan);
            txt_gender_pertumbuhan = (TextView) view.findViewById(R.id.txt_gender_pertumbuhan);
            txt_berat_badan_pertumbuhan = (TextView) view.findViewById(R.id.txt_berat_badan_pertumbuhan);
            txt_bbu = (TextView) view.findViewById(R.id.txt_bbu_pertumbuhan);
            txt_panjang_badan_pertumbuhan = (TextView) view.findViewById(R.id.txt_panjang_badan_pertumbuhan);
            txt_pbu = (TextView) view.findViewById(R.id.txt_pbu_pertumbuhan);
            txt_bbpb = (TextView) view.findViewById(R.id.txt_bbpb_pertumbuhan);
            txt_lingkar_kepala_pertumbuhan = (TextView) view.findViewById(R.id.txt_lingkar_kepala_pertumbuhan);
            txt_lku = (TextView) view.findViewById(R.id.txt_lku_pertumbuhan);
            linear_decoration = (LinearLayout)view.findViewById(R.id.linear_decoration);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_pertumbuhan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Pertumbuhan pertumbuhan = data_pertumbuhan.get(position);
        sessionManager = new SessionManager(mContext);
        holder.txt_nama_pertumbuhan.setText(sessionManager.getNamaAnak());
        holder.txt_gender_pertumbuhan.setText(sessionManager.getGenderAnak());
        holder.txt_umur_pertumbuhan.setText(sessionManager.getUmurAnak().toString() + " Bulan");
        holder.txt_berat_badan_pertumbuhan.setText(pertumbuhan.getBerat_badan().toString());
        holder.txt_bbu.setText("Berat Badan Menurut Umur - " +pertumbuhan.getBbu());
        holder.txt_panjang_badan_pertumbuhan.setText(pertumbuhan.getTinggi_badan().toString());
        holder.txt_pbu.setText("Panjang Badan Menurut Umur - " + pertumbuhan.getPbu());
        holder.txt_bbpb.setText("Berat Badan Menurut Panjang Badan - " + pertumbuhan.getBbpb());
        holder.txt_lku.setText("Lingkar Kepala Menurut Umur - " + pertumbuhan.getLku());
        holder.txt_lingkar_kepala_pertumbuhan.setText(pertumbuhan.getLingkar_kepala().toString());
        if (sessionManager.getGenderAnak().equals("Perempuan")) {
            holder.linear_decoration.setBackgroundColor(Color.parseColor("#FF4081"));
            holder.txt_nama_pertumbuhan.setTextColor(Color.parseColor("#FF4081"));
        }
        // loading album cover using Glide library

    }

    public void add(Double bb, Double tb, Double lk, String bbu, String pbu, String bbpb, String lku ){
        data_pertumbuhan.add(new Pertumbuhan(bb,tb,lk,bbu,pbu,bbpb,lku));
    }

    @Override
    public int getItemCount() {
        return data_pertumbuhan.size();
    }

}

