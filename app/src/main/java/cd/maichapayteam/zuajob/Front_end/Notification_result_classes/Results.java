package cd.maichapayteam.zuajob.Front_end.Notification_result_classes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import cd.maichapayteam.zuajob.Adaptors.Annonces_Base_Adapter_random;
import cd.maichapayteam.zuajob.Adaptors.Notifications_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Postullants_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Sollicitants_Base_Adapter;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Notification;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import pl.droidsonroids.gif.GifDrawable;

public class Results extends AppCompatActivity {

    Context context = this;
    ListView list;
    SearchView rechercher;
    SwipeRefreshLayout swipper;

    ArrayList<Sollicitation> SOLLICITANT = new ArrayList<>();
    ArrayList<Postuler> POSTULLANT = new ArrayList<>();
    ArrayList<Notification> NOTIF = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        rechercher = findViewById(R.id.rechercher);
        swipper = findViewById(R.id.swipper);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Init_Components();

        Notifications();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Home.class);
        startActivity(i);
        finish();
    }


    // TODO Load list notif


    private void Notifications(){
        AsyncTask task = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                NOTIF.clear();
                NOTIF =   (ArrayList<Notification>) ManageLocalData.getListNotifications();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == NOTIF) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (NOTIF.isEmpty()) Toast.makeText(context, "Aucune donnée"  , Toast.LENGTH_SHORT).show();
                else{
                    list.setAdapter(new Notifications_Base_Adapter(context, NOTIF));
                    //count.setText(list.getCount()+" Postullants");
                }
            }

        }.execute();
    }


    // TODO Load postullant
    private void postullants(final Annonce id){
        getSupportActionBar().setSubtitle("Les postullants");

        AsyncTask task = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                POSTULLANT.clear();
                POSTULLANT =  (ArrayList<Postuler>) ManageLocalData.listPostulants(id);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == POSTULLANT) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (POSTULLANT.isEmpty()) Toast.makeText(context, "Aucune donnée"  , Toast.LENGTH_SHORT).show();
                else{
                    list.setAdapter(new Postullants_Base_Adapter(context, POSTULLANT));
                    //count.setText(list.getCount()+" Postullants");
                }
            }

        }.execute();
    }
    
    // TODO Load sollicitant
    private void SOLLICITANTS(final Service id){
        getSupportActionBar().setSubtitle("Les sollicitants");

        AsyncTask task = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                SOLLICITANT.clear();
                SOLLICITANT =  (ArrayList<Sollicitation>) ManageLocalData.listSollicitant(id);
                return null;
            }
            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == SOLLICITANT) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (SOLLICITANT.isEmpty()) Toast.makeText(context, "Aucune donnée"  , Toast.LENGTH_SHORT).show();
                else{
                    list.setAdapter(new Sollicitants_Base_Adapter(context, SOLLICITANT));
                }
            }
        }.execute();


    }

    

}
