package com.example.smartcity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.models.data_laporan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Laporan extends AppCompatActivity implements View.OnClickListener{

    //Deklarasi Variable
    private ProgressBar progressBar;
    private EditText NamaPelapor, IsiLaporan;
    private FirebaseAuth auth;
    private Button Simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_laporan);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        //Inisialisasi ID (Button)
        Simpan = findViewById(R.id.save);
        Simpan.setOnClickListener(this);

        auth = FirebaseAuth.getInstance(); //Mendapakan Instance Firebase Autentifikasi

        //Inisialisasi ID (EditText)
        NamaPelapor = findViewById(R.id.nama_pelapor);
        IsiLaporan = findViewById(R.id.isi_laporan);
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
                String getNamaPelapor = NamaPelapor.getText().toString();
                String getIsiLaporan = IsiLaporan.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                // Mengecek apakah ada data yang kosong
                if(isEmpty(getNamaPelapor) || isEmpty(getIsiLaporan)){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(Laporan.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                    Jika Tidak, maka data dapat diproses dan meyimpannya pada Database
                    Menyimpan data referensi pada Database berdasarkan User ID dari masing-masing Akun
                    */
                    getReference.child("Laporan").child(getUserID).child("Laporan").push()
                            .setValue(new data_laporan(getNamaPelapor, getIsiLaporan))
                            .addOnSuccessListener(this, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                                    NamaPelapor.setText("");
                                    IsiLaporan.setText("");
                                    Toast.makeText(Laporan.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
        }
    }
}