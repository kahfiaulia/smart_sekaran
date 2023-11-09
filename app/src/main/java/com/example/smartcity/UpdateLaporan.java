package com.example.smartcity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.models.data_laporan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateLaporan extends AppCompatActivity {

    //Deklarasi Variable
    private EditText namaPelaporBaru, isiLaporanBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekNamaPelapor, cekIsiLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data_laporan);
        getSupportActionBar().setTitle("Update Laporan");
        namaPelaporBaru = findViewById(R.id.new_nama_pelapor);
        isiLaporanBaru = findViewById(R.id.new_isi_laporan);
        update = findViewById(R.id.update);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekNamaPelapor = namaPelaporBaru.getText().toString();
                cekIsiLaporan = isiLaporanBaru.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekNamaPelapor) || isEmpty(cekIsiLaporan)){
                    Toast.makeText(UpdateLaporan.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //Menjalankan proses update data
                    data_laporan setLaporan = new data_laporan();
                    setLaporan.setNamaPelapor(namaPelaporBaru.getText().toString());
                    setLaporan.setIsiLaporan(isiLaporanBaru.getText().toString());

                    updateLaporan(setLaporan);
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
        final String getNamaPelapor = getIntent().getExtras().getString("dataNamaPelapor");
        final String getIsiLaporan = getIntent().getExtras().getString("dataIsiLaporan");
        namaPelaporBaru.setText(getNamaPelapor);
        isiLaporanBaru.setText(getIsiLaporan);
    }

    //Proses Update data yang sudah ditentukan
    private void updateLaporan(data_laporan laporan){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Laporan")
                .child(userID)
                .child("Laporan")
                .child(getKey)
                .setValue(laporan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        namaPelaporBaru.setText("");
                        isiLaporanBaru.setText("");
                        Toast.makeText(UpdateLaporan.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}