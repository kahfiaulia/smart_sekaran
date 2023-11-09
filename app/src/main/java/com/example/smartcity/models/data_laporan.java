package com.example.smartcity.models;

public class data_laporan {

    //Deklarasi Variable
    private String nama_pelapor;
    private String isi_laporan;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamaPelapor() {
        return nama_pelapor;
    }

    public void setNamaPelapor(String nama_pelapor) {
        this.nama_pelapor = nama_pelapor;
    }

    public String getIsiLaporan() {
        return isi_laporan;
    }

    public void setIsiLaporan(String isi_laporan) {
        this.isi_laporan = isi_laporan;
    }

       //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_laporan(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_laporan(String nama_pelapor, String isi_laporan) {
        this.nama_pelapor = nama_pelapor;
        this.isi_laporan = isi_laporan;
    }
}
