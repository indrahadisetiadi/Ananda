package com.example.ging.starter_project.model;

public class Perkembangan {
    String hasil_perkembangan, tanggal_pengukuran;

    public Perkembangan(String hasil_perkembangan, String tanggal_pengukuran) {
        this.hasil_perkembangan = hasil_perkembangan;
        this.tanggal_pengukuran = tanggal_pengukuran;
    }

    public Perkembangan() {

    }

    public String getHasil_perkembangan() {
        return hasil_perkembangan;
    }

    public void setHasil_perkembangan(String hasil_perkembangan) {
        this.hasil_perkembangan = hasil_perkembangan;
    }

    public String getTanggal_pengukuran() {
        return tanggal_pengukuran;
    }

    public void setTanggal_pengukuran(String tanggal_pengukuran) {
        this.tanggal_pengukuran = tanggal_pengukuran;
    }
}
