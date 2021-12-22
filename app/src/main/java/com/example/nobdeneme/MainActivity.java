package com.example.nobdeneme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.nobdeneme.Adapters.AnaSayfaAdapter;
import com.example.nobdeneme.Models.DataEczaneler;
import com.example.nobdeneme.Models.DataIlceler;
import com.example.nobdeneme.Models.DataSehirler;
import com.example.nobdeneme.Models.Eczaneler;
import com.example.nobdeneme.Models.Ilceler;
import com.example.nobdeneme.Models.Sehirler;
import com.example.nobdeneme.Service.EczanelerAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private String BASE_URL = "https://www.nosyapi.com/apiv2/pharmacy/";
    Retrofit retrofit;
    EczanelerAPI eczanelerAPI;

    RecyclerView rw;
    private Toolbar toolbar1;

    ArrayList<Eczaneler> eczaneVeriler;
    ArrayList<Sehirler> sehirler;
    ArrayList<Ilceler> ilceler;
    String ilceAdiSlugUrl;

    ArrayList<String> sehirlerList;
    ArrayList<String> sehirlerListSlug;
    String[] ilcelerList;
    boolean[] secilenIlce;

    private AnaSayfaAdapter adapter;
    private FloatingActionButton fab;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapterSehir;
    private ArrayAdapter<String> arrayAdapterIlce;

    private ArrayList<Integer> mItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);


        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        fab = findViewById(R.id.fab);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        addCitiesOnSpinner();
        listCities();

        rw = findViewById(R.id.rw);

    }


    private void addCitiesOnSpinner() {

        eczanelerAPI = retrofit.create(EczanelerAPI.class);
        Call<DataSehirler> call1 = eczanelerAPI.iller();

        call1.enqueue(new Callback<DataSehirler>() {
            @Override
            public void onResponse(Call<DataSehirler> call, Response<DataSehirler> response) {
                if(response.isSuccessful()){

                    sehirler = new ArrayList<>(response.body().getData());
                    sehirlerList = new ArrayList<>();
                    sehirlerListSlug = new ArrayList<>();

                    for(int i =0; i< sehirler.size(); i++){
                        sehirlerList.add(sehirler.get(i).getSehirAd());
                        sehirlerListSlug.add(sehirler.get(i).getSehirSlug());
                    }

                    arrayAdapterSehir = new ArrayAdapter<>(MainActivity.this,
                           R.layout.my_selected_item, sehirlerList);
                    arrayAdapterSehir.setDropDownViewResource(R.layout.my_dropdown_item);
                    spinner.setAdapter(arrayAdapterSehir);
                }
            }
            @Override
            public void onFailure(Call<DataSehirler> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void listCities(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String sehirAdiSlug = sehirlerListSlug.get(position);
                String sehirAdiSlugUrl = "?city=" + sehirAdiSlug;

                listDistrictsOnDialogMenu(sehirAdiSlug);

                Call<DataEczaneler> call2 = eczanelerAPI.bulunulanIl(sehirAdiSlugUrl);

                call2.enqueue(new Callback<DataEczaneler>() {
                    @Override
                    public void onResponse(Call<DataEczaneler> call, Response<DataEczaneler> response) {

                        eczaneVeriler = new ArrayList<>(response.body().getData());
                        rw.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        rw.addItemDecoration(new DividerItemDecoration(rw.getContext(), DividerItemDecoration.VERTICAL));
                        adapter = new AnaSayfaAdapter(eczaneVeriler,MainActivity.this);
                        rw.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<DataEczaneler> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void listDistrictsOnDialogMenu(String sehirAdiSlug) {

        ilceAdiSlugUrl = "city?city=" + sehirAdiSlug;


        Call<DataIlceler> call3 = eczanelerAPI.ilceler(ilceAdiSlugUrl);
        call3.enqueue(new Callback<DataIlceler>() {
            @Override
            public void onResponse(Call<DataIlceler> call, Response<DataIlceler> response) {

                ilceler = new ArrayList<>(response.body().getData());
                secilenIlce = new boolean[ilceler.size()];
                ilcelerList = new String[ilceler.size()];

                for(int i = 0; i<ilceler.size(); i++){
                    ilcelerList[i] = ilceler.get(i).getIlceAd();
                }

                //?city=istanbul&county=avcilar
                arrayAdapterIlce = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, ilcelerList);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("İlçe Seçiniz");
                        builder.setAdapter(arrayAdapterIlce, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String ilceyeGoreUrl = "?city=" + sehirAdiSlug + "&county=" + ilceler.get(which).getIlceSlug();
                                Call<DataEczaneler> call4 = eczanelerAPI.bulunulanIl(ilceyeGoreUrl);
                                call4.enqueue(new Callback<DataEczaneler>() {
                                    @Override
                                    public void onResponse(Call<DataEczaneler> call, Response<DataEczaneler> response) {
                                        eczaneVeriler = new ArrayList<>(response.body().getData());
                                        rw.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        rw.addItemDecoration(new DividerItemDecoration(rw.getContext(), DividerItemDecoration.VERTICAL));
                                        adapter = new AnaSayfaAdapter(eczaneVeriler,MainActivity.this);
                                        rw.setAdapter(adapter);
                                    }

                                    @Override
                                    public void onFailure(Call<DataEczaneler> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }
                        });

                        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();

                    }

                });
            }

            @Override
            public void onFailure(Call<DataIlceler> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}








