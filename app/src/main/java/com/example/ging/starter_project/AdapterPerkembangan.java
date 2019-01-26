package com.example.ging.starter_project;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ging.starter_project.model.Perkembangan;
import com.example.ging.starter_project.model.Pertumbuhan;
import com.example.ging.starter_project.util.SessionManager;

import java.util.ArrayList;

public class AdapterPerkembangan extends RecyclerView.Adapter<AdapterPerkembangan.MyViewHolder> {

    private Context mContext;
    private ArrayList<Perkembangan> data_perkembangan;
    private SessionManager sessionManager;
    public AdapterPerkembangan(Context mContext) {
        this.mContext = mContext;
        data_perkembangan = new ArrayList<>();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_nama_perkembangan, txt_umur_perkembangan, txt_gender_perkembangan,
        txt_hasil_perkembangan,txt_tanggal_pengukuran_perkembangan, lbl_status_perkembangan;
        public LinearLayout img_anak_perkembangan;

        public MyViewHolder(View view) {
            super(view);
            txt_nama_perkembangan = (TextView) view.findViewById(R.id.txt_nama_anak_perkembangan);
            txt_umur_perkembangan = (TextView) view.findViewById(R.id.txt_umur_anak_perkembangan);
            txt_gender_perkembangan = (TextView) view.findViewById(R.id.txt_gender_anak_perkembangan);
            txt_tanggal_pengukuran_perkembangan = (TextView) view.findViewById(R.id.txt_tanggal_pemeriksaan_perkembangan);
            txt_hasil_perkembangan = (TextView)view.findViewById(R.id.txt_status_perkembangan_anak);
            img_anak_perkembangan = (LinearLayout)view.findViewById(R.id.img_anak_perkembangan);
            lbl_status_perkembangan = (TextView)view.findViewById(R.id.lbl_status_perkembangan);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_perkembangan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Perkembangan perkembangan = data_perkembangan.get(position);
        sessionManager = new SessionManager(mContext);
        holder.txt_nama_perkembangan.setText(sessionManager.getNamaAnak());
        holder.txt_gender_perkembangan.setText(sessionManager.getGenderAnak());
        holder.txt_umur_perkembangan.setText(sessionManager.getUmurAnak().toString() + " Bulan");
        holder.txt_tanggal_pengukuran_perkembangan.setText(perkembangan.getTanggal_pengukuran());
        if (sessionManager.getGenderAnak().equals("Perempuan")) {
            holder.txt_nama_perkembangan.setTextColor(Color.parseColor("#FF4081"));
            holder.img_anak_perkembangan.setBackgroundResource(R.drawable.girl);
        }
        if (perkembangan.getHasil_perkembangan().equals("normal")) {

            holder.txt_hasil_perkembangan.setText("Perkembangan anak sesuai dengan tahap perkembanganya");
            holder.lbl_status_perkembangan.setTextColor(Color.parseColor("#1abb9a"));
            holder.txt_hasil_perkembangan.setTextColor(Color.parseColor("#1abb9a"));
        }

        else if (perkembangan.getHasil_perkembangan().equals("meragukan")) {

            holder.txt_hasil_perkembangan.setText("Perkembangan anak meragukan");
            holder.lbl_status_perkembangan.setTextColor(Color.parseColor("#FBAF5D"));
            holder.txt_hasil_perkembangan.setTextColor(Color.parseColor("#FBAF5D"));
        }

        else {

            holder.txt_hasil_perkembangan.setText("Kemungkinan ada penyimpangan");
            holder.lbl_status_perkembangan.setTextColor(Color.parseColor("#f64c73"));
            holder.txt_hasil_perkembangan.setTextColor(Color.parseColor("#f64c73"));
        }


        // loading album cover using Glide library

    }

    public void add(String hasil_perkembangan, String tanggal_pengukuran){
        data_perkembangan.add(new Perkembangan(hasil_perkembangan,tanggal_pengukuran));
    }

    @Override
    public int getItemCount() {
        return data_perkembangan.size();
    }

}

