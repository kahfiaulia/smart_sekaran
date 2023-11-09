package com.example.smartcity.models;

public class data_penduduk {

    //Deklarasi Variable
    private String nik;
    private String kk;
    private String nama;
    private String tempatLahir;
    private String tanggalLahir;
    private String namaAyah;
    private String namaIbu;
    private String pekerjaan;
    private String agama;
    private String goldar;
    private String alamat;
    private String noHP;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNIK() {
        return nik;
    }

    public void setNIK(String nik) {
        this.nik = nik;
    }

    public String getKK() {
        return kk;
    }

    public void setKK(String kk) {
        this.kk = kk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getNamaAyah() {
        return namaAyah;
    }

    public void setNamaAyah(String namaAyah) {
        this.namaAyah = namaAyah;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getGoldar() {
        return goldar;
    }

    public void setGoldar(String goldar) {
        this.goldar = goldar;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_penduduk(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_penduduk(String nik, String kk, String nama, String tempatLahir, String tanggalLahir, String namaAyah, String namaIbu, String pekerjaan, String agama, String goldar, String alamat, String noHP) {
        this.nik = nik;
        this.kk = kk;
        this.nama = nama;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.namaAyah = namaAyah;
        this.namaIbu = namaIbu;
        this.pekerjaan = pekerjaan;
        this.agama = agama;
        this.goldar = goldar;
        this.alamat = alamat;
        this.noHP = noHP;

    }
}
