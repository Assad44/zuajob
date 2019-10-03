package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Postullances_Base_Adapter;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;

public class Mes_postulances extends AppCompatActivity {

    Context context = this;
    ListView list;
    SwipeRefreshLayout swipper;
    SearchView rechercher;

    ArrayList<Postuler> ANNOCE = new ArrayList<>();
    List<Postuler> ANNOCE_L = new ArrayList<>();
    ArrayList<Postuler> SearchA = new ArrayList<>();

    Postullances_Base_Adapter PostulerAdapter;
    int turnA = 0;
    
    private void Init_Components(){
        list = findViewById(R.id.list);
        rechercher = findViewById(R.id.rechercher);
        swipper = findViewById(R.id.swipper);
    }

    void Load_Postuler(){
        AsyncTask task = new AsyncTask() {
            int cout = list.getCount();
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                //Toast.makeText(context, "---------- "+ cout , Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                ANNOCE_L = ManageLocalData.listMesPostulances();
                if (ANNOCE_L.isEmpty()){
                    Postuler s = new Postuler();
                    s.setDate("2019-09-28");
                    s.setDeviseAnnonce("USD");
                    s.setNomsUser("XXXXXXXXXX");
                    s.setPhoneUser("XXXXXXXXXX");
                    ANNOCE_L.add(s);
                }
                for (Postuler c : ANNOCE_L){
                    ANNOCE.add(c);
                }
                //ANNOCE = (ArrayList<Postuler>) ANNOCE_L;
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);

                if (null == ANNOCE) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (ANNOCE.isEmpty())Toast.makeText(context, "Aucune donnée" , Toast.LENGTH_SHORT).show();
                else{
                    PostulerAdapter = new Postullances_Base_Adapter(context, ANNOCE);
                    list.setAdapter(PostulerAdapter);
                    turnA = 1;
                }

            }

        }.execute();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_postulances);

        getSupportActionBar().setTitle("Mes postullances");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        // Todo ; launching methods
        Load_Postuler();
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
                SearchA.clear();

                if (newText.equals("")) {
                    list.setAdapter(new Postullances_Base_Adapter(context, ANNOCE));
                    return true;
                }

                for ( Postuler s : ANNOCE ) {
                    if (
                            s.getNomsUser().toUpperCase().contains(newText.toUpperCase()) ||
                                    String.valueOf(s.getMontantAnnonce()).toUpperCase().equals(newText.toUpperCase())
                    ){
                        SearchA.add(s);
                    }
                }
                list.setAdapter(new Postullances_Base_Adapter(context, SearchA));
                return true;
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
