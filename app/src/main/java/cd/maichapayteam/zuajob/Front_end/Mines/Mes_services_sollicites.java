package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Services_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Services_sollicites_Base_Adapter;
import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Mes_services_sollicites extends AppCompatActivity {

    Context context = this;
    ListView list;
    SearchView rechercher;
    SwipeRefreshLayout swipper;

    ArrayList<Service> SERVICES = new ArrayList<>();
    List<Service> SERVICE_L = new ArrayList<>();
    ArrayList<Service> Search = new ArrayList<>();

    Services_Base_Adapter serviceAdapter;

    private void Init_Components(){
        list = findViewById(R.id.list);
        rechercher = findViewById(R.id.rechercher);
        swipper = findViewById(R.id.swipper);
    }

    void Load_SERVICE(){
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
                //SERVICE_L /*ManageLocalData.sol(cout)*/;
                for (Service c : SERVICE_L){
                    SERVICES.add(c);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (SERVICES.isEmpty())Toast.makeText(context, "Aucune donnée" , Toast.LENGTH_SHORT).show();
                else{
                    Log.e("SSSSS", String.valueOf(SERVICES.size()));
                    serviceAdapter = new Services_Base_Adapter(context, SERVICES);
                    list.setAdapter(serviceAdapter);
                }
            }

        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_services_sollicites);
        
        getSupportActionBar().setTitle("Mes services sollicités");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        // Todo ; launching methods
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

        rechercher.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Search.clear();

                if (newText.equals("")) {
                    list.setAdapter(new Services_sollicites_Base_Adapter(context, SERVICES));
                    return true;
                }

                for ( Service s : SERVICES ) {
                    if (
                            s.getNomsJobeur().toUpperCase().contains(newText.toUpperCase()) ||
                                    String.valueOf(s.getMontant()).toUpperCase().equals(newText.toUpperCase())
                    ){
                        Search.add(s);
                    }
                }
                list.setAdapter(new Services_sollicites_Base_Adapter(context, Search));
                return true;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(context, Details_publication.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Home.class);
        startActivity(i);
        finish();
    }

}
