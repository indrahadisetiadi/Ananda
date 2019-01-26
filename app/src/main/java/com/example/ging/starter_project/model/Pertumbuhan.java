package com.example.ging.starter_project.model;

public class Pertumbuhan {
    Double berat_badan, tinggi_badan, lingkar_kepala;
    String bbu, pbu, bbpb, lku, date_pengkukuran;

    public Pertumbuhan(Double berat_badan, Double tinggi_badan, Double lingkar_kepala, String bbu, String pbu, String bbpb, String lku, String date_pengukuran) {
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.lingkar_kepala = lingkar_kepala;
        this.bbu = bbu;
        this.pbu = pbu;
        this.bbpb = bbpb;
        this.lku = lku;
        this.date_pengkukuran = date_pengukuran;
    }

    public String getDate_pengkukuran() {
        return date_pengkukuran;
    }

    public void setDate_pengkukuran(String date_pengkukuran) {
        this.date_pengkukuran = date_pengkukuran;
    }

    public Double getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(Double berat_badan) {
        this.berat_badan = berat_badan;
    }

    public Double getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(Double tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public Double getLingkar_kepala() {
        return lingkar_kepala;
    }

    public void setLingkar_kepala(Double lingkar_kepala) {
        this.lingkar_kepala = lingkar_kepala;
    }

    public String getBbu() {
        return bbu;
    }

    public void setBbu(String bbu) {
        this.bbu = bbu;
    }

    public String getPbu() {
        return pbu;
    }

    public void setPbu(String pbu) {
        this.pbu = pbu;
    }

    public String getBbpb() {
        return bbpb;
    }

    public void setBbpb(String bbpb) {
        this.bbpb = bbpb;
    }

    public String getLku() {
        return lku;
    }

    public void setLku(String lku) {
        this.lku = lku;
    }
}
