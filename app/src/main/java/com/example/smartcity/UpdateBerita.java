package com.example.smartcity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.models.data_berita;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateBerita extends AppCompatActivity {

    //Deklarasi Variable
    private EditText judulBeritaBaru, isiBeritaBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekJudulBerita, cekIsiBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data_berita);
        getSupportActionBar().setTitle("Update Berita");
        judulBeritaBaru = findViewById(R.id.new_judul_berita);
        isiBeritaBaru = findViewById(R.id.new_isi_berita);
        update = findViewById(R.id.update);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekJudulBerita = judulBeritaBaru.getText().toString();
                cekIsiBerita = isiBeritaBaru.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekJudulBerita) || isEmpty(cekIsiBerita)){
                    Toast.makeText(UpdateBerita.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //Menjalankan proses update data
                    data_berita setBerita = new data_berita();
                    setBerita.setJudulBerita(judulBeritaBaru.getText().toString());
                    setBerita.setIsiBerita(isiBeritaBaru.getText().toString());

                    updateBerita(setBerita);
                }
            }
        });
    }

    // Mengecek apakah ada data yang kosong, sebelum diupdate
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    //Menampilkan data yang akan di update
    private void getData(){
        final String getJudulBerita = getIntent().getExtras().getString("dataJudulBerita");
        final String getIsiBerita = getIntent().getExtras().getString("dataIsiBerita");

        judulBeritaBaru.setText(getJudulBerita);
        isiBeritaBaru.setText(getIsiBerita);
    }

    //Proses Update data yang sudah ditentukan
    private void updateBerita(data_berita berita){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Berita")
                .child(userID)
                .child("Berita")
                .child(getKey)
                .setValue(berita)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        judulBeritaBaru.setText("");
                        isiBeritaBaru.setText("");
                        Toast.makeText(UpdateBerita.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}