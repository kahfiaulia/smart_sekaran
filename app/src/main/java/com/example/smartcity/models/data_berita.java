package com.example.smartcity.models;

public class data_berita {

    //Deklarasi Variable
    private String judul_berita;
    private String isi_berita;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJudulBerita() {
        return judul_berita;
    }

    public void setJudulBerita(String judul_berita) {
        this.judul_berita = judul_berita;
    }

    public String getIsiBerita() {
        return isi_berita;
    }

    public void setIsiBerita(String isi_berita) {
        this.isi_berita = isi_berita;
    }


    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_berita(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_berita(String judul_berita, String isi_berita) {
        this.judul_berita = judul_berita;
        this.isi_berita = isi_berita;
    }
}
