package com.training.alif.geeksfarm.marketplace.entity;

public class Mahasiswa {
    private String namaDepan,namaBelakang,jurusan;
    private int umur;


    public Mahasiswa(String namaDepan, String namaBelakang, String jurusan, int umur) {
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.jurusan = jurusan;
        this.umur = umur;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public String getJurusan() {
        return jurusan;
    }

    public int getUmur() {
        return umur;
    }
}
