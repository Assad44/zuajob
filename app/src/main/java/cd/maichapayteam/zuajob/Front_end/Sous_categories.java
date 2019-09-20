package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Annonces_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Services_Base_Adapter;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GenerateData;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Sous_categories extends AppCompatActivity {

    Context context = this;
    ListView list;
    Spinner list_sous_cat,type;
    LinearLayout option_bar;
    SwipeRefreshLayout swiper;
    ArrayList<String> SCAT = new ArrayList<>();
    List<SousCategorie> LSC = new ArrayList<>();

    ArrayList<Service> SERVICES = new ArrayList<>();
    ArrayList<SousCategorie> SC = new ArrayList<>();


    ArrayList<Annonce> ANNOCE = new ArrayList<>();
    ArrayList<Annonce> SearchA = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        list_sous_cat = findViewById(R.id.list_sous_cat);
        type = findViewById(R.id.type);
        option_bar = findViewById(R.id.option_bar);
        swiper = findViewById(R.id.swiper);

    }

    void Load_SCAT(){
        /*for (int i = 0; i < 10; i++) {
            SCAT.add("Sous Categorie "+i+1);
        }*/

        Categorie sc = new Categorie();
        sc.setId(Long.parseLong(getIntent().getExtras().getString("id")));

        // TODO la tache asynchronne
        new AsyncTask<String, Void, String>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                swiper.setRefreshing(true);
            }

            @Override
            protected String doInBackground(String... strings) {
                return null;
            }
            @Override
            protected void onPostExecute(String o) {
                swiper.setRefreshing(false);

            }
        }.execute();
        LSC = GenerateData.listSousCategorie(sc);

        for (SousCategorie s : LSC ) {
            SCAT.add(s.getDesignation());
            Log.e("DDDDDDDDDDDDD___", s.getDesignation());
        }

        Tool.setEntries(context,list_sous_cat, SCAT);
    }
    void Load_SERVICE(){
        SERVICES.clear();
        type.setSelection(1);
        String description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

        for (int i = 0; i < 10; i++) {
            Service s = new Service();
            s.setNomsJobeur(Tool.Versions()[i]);
            s.setDescription(description);
            s.setMontant(new Random().nextInt(50));
            s.setCategorie("Catégorie "+i);
            s.setSousCategorie("Sous catégorie "+i);
            s.setDevise("USD");
            s.setPhoneJobeur("+243 81 451 10 83");
            s.setCote(new Random().nextInt(200));
            s.setNombreRealisation(new Random().nextInt(20));
            SERVICES.add(s);
        }

        if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            list.setAdapter(new Services_Base_Adapter(context, SERVICES));
        }

    }
    void Load_Annonce(){
        ANNOCE.clear();
        type.setSelection(0);
        String description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        for (int i = 0; i < 10; i++) {
            Annonce s = new Annonce();
            s.setNomsUser(Tool.Versions()[i]);
            s.setDescription(description);
            s.setMontant(new Random().nextInt(50));
            s.setCategorie("Catégorie "+i);
            s.setSousCategorie("Sous catégorie "+i);
            s.setDatePublication("2019-09-09 23:57:00");
            s.setDevise("USD");
            s.setPhoneUser("+243 81 451 10 83");
            ANNOCE.add(s);
        }

        if (null == ANNOCE) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            list.setAdapter(new Annonces_Base_Adapter(context, ANNOCE,""));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sous_categories);
        if (!getIntent().hasExtra("title")) onBackPressed();
        getSupportActionBar().setTitle(getIntent().getExtras().getString("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();
        Load_SCAT();
        Load_SERVICE();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (type.getSelectedItem().toString().equals("Services")) Load_SERVICE();
                else Load_Annonce();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        list_sous_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Load_SERVICE();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Categorie_view.class);
        startActivity(i);
        finish();
    }


}
