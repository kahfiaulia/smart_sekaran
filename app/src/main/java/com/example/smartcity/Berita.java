package com.example.smartcity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.models.data_berita;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Berita extends AppCompatActivity implements View.OnClickListener{

    //Deklarasi Variable
    private ProgressBar progressBar;
    private EditText JudulBerita, IsiBerita;
    private FirebaseAuth auth;
    private Button Simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_berita);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        //Inisialisasi ID (Button)
        Simpan = findViewById(R.id.save);
        Simpan.setOnClickListener(this);

        auth = FirebaseAuth.getInstance(); //Mendapakan Instance Firebase Autentifikasi

        //Inisialisasi ID (EditText)
        JudulBerita = findViewById(R.id.judul_berita);
        IsiBerita = findViewById(R.id.isi_berita);
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
                String getJudulBerita = JudulBerita.getText().toString();
                String getIsiBerita = IsiBerita.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                // Mengecek apakah ada data yang kosong
                if(isEmpty(getJudulBerita) || isEmpty(getIsiBerita)){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(Berita.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                    Jika Tidak, maka data dapat diproses dan meyimpannya pada Database
                    Menyimpan data referensi pada Database berdasarkan User ID dari masing-masing Akun
                    */
                    getReference.child("Berita").child(getUserID).child("Berita").push()
                            .setValue(new data_berita(getJudulBerita, getIsiBerita))
                            .addOnSuccessListener(this, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                                    JudulBerita.setText("");
                                    IsiBerita.setText("");
                                    Toast.makeText(Berita.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
        }
    }
}