package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Rdv_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Rdv_postuller_Base_Adapter;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;

public class Mes_rendez_vous extends AppCompatActivity {
    Context context = this;
    ListView list;
    Spinner type;
    SearchView rechercher;
    SwipeRefreshLayout swipper;
    LinearLayout sous;

    ArrayList<Sollicitation> SERVICES = new ArrayList<>();
    ArrayList<Sollicitation> Search = new ArrayList<>();


    List<Postuler> POSTULLER;
    List<Sollicitation> SOLLICITATION;

    private void Init_Components(){
        list = findViewById(R.id.list);
        sous = findViewById(R.id.sous);
        type = findViewById(R.id.type);
        swipper = findViewById(R.id.swiper);
    }

    void RDV_POSTULER(){

        new AsyncTask() {

            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                //Toast.makeText(context, "---------- "+ cout , Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                POSTULLER = (List<Postuler>) ManageLocalData.mesRDVenAttente().get(0);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);

                if (null == POSTULLER) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    list.setAdapter(new Rdv_postuller_Base_Adapter(context, (ArrayList<Postuler>) POSTULLER));
                }

            }

        }.execute();

    }
    void RDV_SOLLICITER(){

        new AsyncTask() {

            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                //Toast.makeText(context, "---------- "+ cout , Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                SOLLICITATION = (List<Sollicitation>) ManageLocalData.mesRDVenAttente().get(1);
                if (SOLLICITATION.isEmpty()){
                    Sollicitation s = new Sollicitation();
                    s.setDate("2019-10-01");
                    SOLLICITATION.add(s);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == SOLLICITATION) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    list.setAdapter(new Rdv_Base_Adapter(context, (ArrayList<Sollicitation>) SOLLICITATION));
                }

            }

        }.execute();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_rendez_vous);
        getSupportActionBar().setTitle("Mes rendez-vous");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        // Todo ; launching methods
        RDV_SOLLICITER();
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
                if (position == 0){
                    RDV_SOLLICITER();
                }else RDV_POSTULER();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
