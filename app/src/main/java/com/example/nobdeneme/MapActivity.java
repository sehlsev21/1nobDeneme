package com.example.nobdeneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


public class MapActivity extends AppCompatActivity {

    Toolbar toolbar2;
    private int izinKontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        toolbar2 = findViewById(R.id.toolbar2);

        izinKontrol = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(izinKontrol != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},100);
        }else{

        }

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Double lati = bundle.getDouble("lati");
            Double longi = bundle.getDouble("longi");
            String eczaneAdi = bundle.getString("eczaneAdi");
            toolbar2.setTitle(eczaneAdi);
            System.out.println(lati + " " + longi + " " + eczaneAdi);
        }
        setSupportActionBar(toolbar2);

        toolbar2.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "İZİN VERİLDİ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "İZİN VERİLMEDİ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}