package com.example.ging.starter_project.model;

public class Anak {
    Integer id_anak;
    String nama;
    Integer umur;
    String gender;

    public Anak(Integer id_anak, String nama, String gender, Integer umur)
    {
        this.id_anak = id_anak;
        this.nama = nama;
        this.gender = gender;
        this.umur = umur;
    }

    public Anak()
    {

    }

    public Integer getId_anak() {
        return id_anak;
    }

    public void setId_anak(Integer id_anak) {
        this.id_anak = id_anak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
