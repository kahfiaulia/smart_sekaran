package com.example.smartcity;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.smartcity.R;
import com.example.smartcity.models.data_penduduk;

import java.util.Collections;

public class DataPenduduk extends AppCompatActivity implements View.OnClickListener{

    //Deklarasi Variable
    private ProgressBar progressBar;
    private EditText NIK, KK, Nama, tempatLahir, tanggalLahir, namaAyah, namaIbu, Pekerjaan, Agama, Goldar, Alamat, noHP;
    private FirebaseAuth auth;
    private Button Simpan, ShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_penduduk);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        //Inisialisasi ID (Button)
        Simpan = findViewById(R.id.save);
        Simpan.setOnClickListener(this);

        auth = FirebaseAuth.getInstance(); //Mendapakan Instance Firebase Autentifikasi

        //Inisialisasi ID (EditText)
        NIK = findViewById(R.id.nik);
        KK = findViewById(R.id.kk);
        Nama = findViewById(R.id.nama);
        tempatLahir = findViewById(R.id.tempatlahir);
        tanggalLahir = findViewById(R.id.tanggallahir);
        namaAyah = findViewById(R.id.namaayah);
        namaIbu = findViewById(R.id.namaibu);
        Pekerjaan = findViewById(R.id.pekerjaan);
        Agama = findViewById(R.id.agama);
        Goldar = findViewById(R.id.goldar);
        Alamat = findViewById(R.id.alamat);
        noHP = findViewById(R.id.nohp);
    }

    // Mengecek apakah ada data yang kosong
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                //Mendapatkan UserID dari pengguna yang Terautentikasi
                String getUserID = auth.getCurrentUser().getUid();

                //Mendapatkan Instance dari Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                //Menyimpan Data yang diinputkan User kedalam Variable
                String getNIK = NIK.getText().toString();
                String getKK = KK.getText().toString();
                String getNama = Nama.getText().toString();
                String getTempatLahir = tempatLahir.getText().toString();
                String getTanggalLahir = tanggalLahir.getText().toString();
                String getNamaAyah = namaAyah.getText().toString();
                String getNamaIbu = namaIbu.getText().toString();
                String getPekerjaan = Pekerjaan.getText().toString();
                String getAgama = Agama.getText().toString();
                String getGoldar = Goldar.getText().toString();
                String getAlamat = Alamat.getText().toString();
                String getNoHP = noHP.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                // Mengecek apakah ada data yang kosong
                if(isEmpty(getNIK) || isEmpty(getKK) || isEmpty(getNama) || isEmpty(getTempatLahir) || isEmpty(getTanggalLahir) || isEmpty(getNamaAyah) || isEmpty(getNamaIbu) || isEmpty(getPekerjaan) || isEmpty(getAgama) || isEmpty(getGoldar) || isEmpty(getAlamat) || isEmpty(getNoHP)){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(DataPenduduk.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                    Jika Tidak, maka data dapat diproses dan meyimpannya pada Database
                    Menyimpan data referensi pada Database berdasarkan User ID dari masing-masing Akun
                    */
                    getReference.child("Penduduk").child(getUserID).child("Penduduk").push()
                            .setValue(new data_penduduk(getNIK, getKK, getNama, getTempatLahir, getTanggalLahir, getNamaAyah, getNamaIbu, getPekerjaan, getAgama, getGoldar, getAlamat, getNoHP))
                            .addOnSuccessListener(this, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                                    NIK.setText("");
                                    KK.setText("");
                                    Nama.setText("");
                                    tempatLahir.setText("");
                                    tanggalLahir.setText("");
                                    namaAyah.setText("");
                                    namaIbu.setText("");
                                    Pekerjaan.setText("");
                                    Agama.setText("");
                                    Goldar.setText("");
                                    Alamat.setText("");
                                    noHP.setText("");
                                    Toast.makeText(DataPenduduk.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
        }
    }
}