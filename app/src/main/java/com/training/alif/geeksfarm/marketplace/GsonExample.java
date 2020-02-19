package com.training.alif.geeksfarm.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.training.alif.geeksfarm.marketplace.entity.Mahasiswa;

public class GsonExample extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_example);
        tv = findViewById(R.id.id);
        Gson jsonConverter = new Gson();

        //Serialisasi

        Mahasiswa mhs = new Mahasiswa(
                "Alif",
                "Rizky",
                "Informatika",
                20
        );
        //Serialisasi
        String json = "{\"jurusan\":\"Informatika\",\"namaBelakang\":\"Rizky\",\"namaDepan\":\"Alif\",\"umur\":20}";
        Mahasiswa maha = jsonConverter.fromJson(json,Mahasiswa.class);

    }
}
