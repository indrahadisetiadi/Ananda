package com.example.ging.starter_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ging.starter_project.model.Anak;
import com.example.ging.starter_project.util.SessionManager;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;


public class AdapterProfile extends RecyclerView.Adapter<AdapterProfile.MyViewHolder> {

    private Context mContext;
    private ArrayList<Anak> data_anak;

    private SessionManager sessionManager;

    public AdapterProfile(Context mContext) {
        this.mContext = mContext;
        data_anak = new ArrayList<>();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_profile_nama,txt_profile_umur,txt_profile_gender,btn_lihat_pertumbuhan,btn_lihat_perkembangan;;
        public LinearLayout img_anak;

        public MyViewHolder(View view) {
            super(view);
            txt_profile_nama = (TextView) view.findViewById(R.id.txt_profile_nama_anak);
            txt_profile_umur = (TextView) view.findViewById(R.id.txt_profile_umur_anak);
            txt_profile_gender = (TextView) view.findViewById(R.id.txt_profile_gender_anak);
            btn_lihat_perkembangan = (TextView) view.findViewById(R.id.btn_lihat_perkembangan);
            btn_lihat_pertumbuhan = (TextView) view.findViewById(R.id.btn_lihat_pertumbuhan);
            img_anak = (LinearLayout) view.findViewById(R.id.img_anak);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Anak anak = data_anak.get(position);
        holder.txt_profile_nama.setText(anak.getNama());
        // loading album cover using Glide library
        holder.txt_profile_gender.setText(anak.getGender());
        holder.txt_profile_umur.setText(anak.getUmur().toString() + " Bulan");
        if (anak.getGender().equals("Laki-Laki")) {
            holder.img_anak.setBackgroundResource(R.drawable.boy);
        }
        else {
            holder.img_anak.setBackgroundResource(R.drawable.girl);
        }
        holder.btn_lihat_pertumbuhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    Anak clickedDataItem = data_anak.get(pos);
                    sessionManager = new SessionManager(mContext);
                    sessionManager.setProfileAnak(clickedDataItem.getId_anak(),
                            clickedDataItem.getNama(),clickedDataItem.getGender(),clickedDataItem.getUmur());
                    MDToast.makeText(mContext, clickedDataItem.getId_anak().toString(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
                    ((MainActivity)mContext).changeMenu(R.id.navigation_pertumbuhan);
                }
            }
        });
        holder.btn_lihat_perkembangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    Anak clickedDataItem = data_anak.get(pos);
                    sessionManager = new SessionManager(mContext);
                    sessionManager.setProfileAnak(clickedDataItem.getId_anak(),
                            clickedDataItem.getNama(),clickedDataItem.getGender(),clickedDataItem.getUmur());
                    MDToast.makeText(mContext, clickedDataItem.getId_anak().toString(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
                    ((MainActivity)mContext).changeMenu(R.id.navigation_perkembangan);

                }
            }
        });
    }

    public void add(Integer id_anak, String nama, String gender, Integer umur ){
        data_anak.add(new Anak(id_anak,nama,gender,umur));
    }

    @Override
    public int getItemCount() {
        return data_anak.size();
    }

}

