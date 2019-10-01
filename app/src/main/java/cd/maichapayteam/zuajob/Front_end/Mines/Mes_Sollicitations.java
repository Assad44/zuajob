package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Services_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Sollicitations_Base_Adapter;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Mes_Sollicitations extends AppCompatActivity {

    Context context = this;
    ListView list;
    SearchView rechercher;
    SwipeRefreshLayout swipper;

    ArrayList<Sollicitation> SERVICES = new ArrayList<>();
    List<Sollicitation> SERVICE_L = new ArrayList<>();
    ArrayList<Sollicitation> Search = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        rechercher = findViewById(R.id.rechercher);
        swipper = findViewById(R.id.swipper);
    }

    void Load_SOLLICITATION(){
        AsyncTask task = new AsyncTask() {
            int cout = list.getCount();
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                SERVICE_L.clear();
                SERVICE_L = ManageLocalData.listMesSollicitations();
                if (SERVICE_L.isEmpty()){
                    Sollicitation s = new Sollicitation();
                    s.setNomsUser("Deon Mass");
                    String description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
                    s.setDescriptionService(description);
                    s.setMontant(new Random().nextInt(50));
                    s.setCategorie("Catégorie ");
                    s.setSouscategorie("Sous catégorie ");
                    s.setDevise("USD");
                    s.setPhoneUser("+243 81 451 10 83");
                    s.setDate("2019-09-28 12:42:00");
                    SERVICE_L.add(s);
                }
                for (Sollicitation c : SERVICE_L){
                    SERVICES.add(c);
                }
                return null;
            }
            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (SERVICES.isEmpty())Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
                else{
                    Log.e("SSSSS", String.valueOf(SERVICES.size()));
                    Toast.makeText(context, "---------- "+ SERVICES.size() , Toast.LENGTH_SHORT).show();
                    list.setAdapter(new Sollicitations_Base_Adapter(context, SERVICES));
                }
            }

        }.execute();
    }

    void Load_SOLLICITATION2(){
        SERVICES.clear();



        if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes__sollicitations);

        getSupportActionBar().setTitle("Mes sollicitations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        // Todo ; launching methods
        Load_SOLLICITATION();
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

        swipper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Load_SOLLICITATION();
            }
        });

        rechercher.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Search.clear();

                if (newText.equals("")) {
                    list.setAdapter(new Sollicitations_Base_Adapter(context, SERVICES));
                    return true;
                }

                for ( Sollicitation s : SERVICES ) {
                    if (
                            s.getNomsUser().toUpperCase().contains(newText.toUpperCase()) ||
                                    String.valueOf(s.getMontant()).toUpperCase().equals(newText.toUpperCase())
                    ){
                        Search.add(s);
                    }
                }
                list.setAdapter(new Sollicitations_Base_Adapter(context, Search));
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Home.class);
        startActivity(i);
        Tool.setUserPreferences(context, "recent","");
        finish();
    }

}
