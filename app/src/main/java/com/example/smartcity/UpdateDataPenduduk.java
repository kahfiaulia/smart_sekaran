package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.smartcity.R;
import com.example.smartcity.models.data_penduduk;

public class UpdateDataPenduduk extends AppCompatActivity {

    //Deklarasi Variable
    private EditText nikBaru, kkBaru, namaBaru, tempatLahirBaru, tanggalLahirBaru, namaAyahBaru, namaIbuBaru, pekerjaanBaru, agamaBaru, goldarBaru, alamatBaru, noHPBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekNIK, cekKK, cekNama, cekTempatLahir, cekTanggalLahir, cekNamaAyah, cekNamaIbu, cekPekerjaan, cekAgama, cekGoldar, cekAlamat, cekNoHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data_penduduk);
        getSupportActionBar().setTitle("Update Data Penduduk");
        nikBaru = findViewById(R.id.new_nik);
        kkBaru = findViewById(R.id.new_kk);
        namaBaru = findViewById(R.id.new_nama);
        tempatLahirBaru = findViewById(R.id.new_tempat_lahir);
        tanggalLahirBaru = findViewById(R.id.new_tanggal_lahir);
        namaAyahBaru = findViewById(R.id.new_nama_ayah);
        namaIbuBaru = findViewById(R.id.new_nama_ibu);
        pekerjaanBaru = findViewById(R.id.new_pekerjaan);
        agamaBaru = findViewById(R.id.new_agama);
        goldarBaru = findViewById(R.id.new_goldar);
        alamatBaru = findViewById(R.id.new_alamat);
        noHPBaru = findViewById(R.id.new_no_hp);
        update = findViewById(R.id.update);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekNIK = nikBaru.getText().toString();
                cekKK = kkBaru.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekNIK) || isEmpty(cekKK)){
                    Toast.makeText(UpdateDataPenduduk.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //Menjalankan proses update data
                    data_penduduk setPenduduk = new data_penduduk();
                    setPenduduk.setNIK(nikBaru.getText().toString());
                    setPenduduk.setKK(kkBaru.getText().toString());
                    setPenduduk.setNama(namaBaru.getText().toString());
                    setPenduduk.setTempatLahir(tempatLahirBaru.getText().toString());
                    setPenduduk.setTanggalLahir(tanggalLahirBaru.getText().toString());
                    setPenduduk.setNamaAyah(namaAyahBaru.getText().toString());
                    setPenduduk.setNamaIbu(namaIbuBaru.getText().toString());
                    setPenduduk.setPekerjaan(pekerjaanBaru.getText().toString());
                    setPenduduk.setAgama(agamaBaru.getText().toString());
                    setPenduduk.setGoldar(goldarBaru.getText().toString());
                    setPenduduk.setAlamat(alamatBaru.getText().toString());
                    setPenduduk.setAgama(agamaBaru.getText().toString());
                    setPenduduk.setNoHP(noHPBaru.getText().toString());

                    updatePenduduk(setPenduduk);
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
        final String getNIK = getIntent().getExtras().getString("dataNIK");
        final String getKK = getIntent().getExtras().getString("dataKK");
        final String getNama = getIntent().getExtras().getString("dataNama");
        final String getTempatLahir = getIntent().getExtras().getString("dataTempatLahir");
        final String getTanggalLahir = getIntent().getExtras().getString("dataTanggalLahir");
        final String getNamaAyah = getIntent().getExtras().getString("dataNamaAyah");
        final String getNamaIbu = getIntent().getExtras().getString("dataNamaIbu");
        final String getPekerjaan = getIntent().getExtras().getString("dataPekerjaan");
        final String getAgama = getIntent().getExtras().getString("dataAgama");
        final String getGoldar = getIntent().getExtras().getString("dataGoldar");
        final String getAlamat = getIntent().getExtras().getString("dataAlamat");
        final String getNoHP = getIntent().getExtras().getString("dataNoHP");
        nikBaru.setText(getNIK);
        kkBaru.setText(getKK);
        namaBaru.setText(getNama);
        tempatLahirBaru.setText(getTempatLahir);
        tanggalLahirBaru.setText(getTanggalLahir);
        namaAyahBaru.setText(getNamaAyah);
        namaIbuBaru.setText(getNamaIbu);
        pekerjaanBaru.setText(getPekerjaan);
        agamaBaru.setText(getAgama);
        goldarBaru.setText(getGoldar);
        alamatBaru.setText(getAlamat);
        noHPBaru.setText(getNoHP);
    }

    //Proses Update data yang sudah ditentukan
    private void updatePenduduk(data_penduduk penduduk){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Penduduk")
                .child(userID)
                .child("Penduduk")
                .child(getKey)
                .setValue(penduduk)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nikBaru.setText("");
                        kkBaru.setText("");
                        namaBaru.setText("");
                        tempatLahirBaru.setText("");
                        tanggalLahirBaru.setText("");
                        namaAyahBaru.setText("");
                        namaIbuBaru.setText("");
                        pekerjaanBaru.setText("");
                        agamaBaru.setText("");
                        goldarBaru.setText("");
                        alamatBaru.setText("");
                        noHPBaru.setText("");
                        Toast.makeText(UpdateDataPenduduk.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}