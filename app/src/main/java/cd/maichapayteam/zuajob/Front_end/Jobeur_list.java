package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Jobeur_Base_Adapter;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Jobeur_list extends AppCompatActivity {

    Context context = this;
    GridView list;
    SearchView rechercher;
    SwipeRefreshLayout swipper;

    LinearLayout progressbar;


    List<User> DATA_L = new ArrayList<User>();
    List<User> DATA = new ArrayList<User>();
    List<User> Search = new ArrayList<User>();

    Jobeur_Base_Adapter jobeurAdapter;
    int turn = 0;

    private void Init_Components(){
        list = findViewById(R.id.list);
        swipper = findViewById(R.id.swipper);
        rechercher = findViewById(R.id.rechercher);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.GONE);
        
        Load_Jobeur();
    }

    void Load_Jobeur(){
        AsyncTask task = new AsyncTask() {
            int cout = list.getCount();
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                DATA_L.clear();
                DATA_L = ManageLocalData.listJobeurs(cout);
                for (User c : DATA_L){
                    DATA.add(c);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                progressbar.setVisibility(View.GONE);
                if (null == DATA) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (DATA.isEmpty()) Toast.makeText(context, "Aucune donnée"  , Toast.LENGTH_SHORT).show();
                else{
                    Log.e("SSSSS", String.valueOf(DATA.size()));
                    if (turn != 0) {
                        jobeurAdapter.notifyDataSetChanged();
                        return;
                    }
                    jobeurAdapter = new Jobeur_Base_Adapter(context, DATA);
                    turn = 1;
                    list.setAdapter(jobeurAdapter);
                }
            }

        }.execute();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobeur_list);
        getSupportActionBar().setTitle("Liste jobeurs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();
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

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    int last=view.getLastVisiblePosition();// la position de la derniere elements par
                    int total= view.getCount();// Le nombre total d'element contenue de la list
                    if(last+2>total){
                        progressbar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Load_Jobeur();
                            }
                        }, 2000);

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });


        swipper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Load_Jobeur();
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
                    list.setAdapter(new Jobeur_Base_Adapter(context, DATA));
                    return true;
                }

                for ( User s : DATA ) {
                    if (
                            s.getNom().toUpperCase().contains(newText.toUpperCase())
                    ){
                        Search.add(s);
                    }
                }
                list.setAdapter(new Jobeur_Base_Adapter(context, Search));

                return true;
            }
        });
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                View view  = LayoutInflater.from(context).inflate(R.layout.view_jobeurs_details2,null);
                TextView nom = view.findViewById(R.id.nom);
                TextView number = view.findViewById(R.id.number);
                ImageView avatar = view.findViewById(R.id.avatar);
                TextView age = view.findViewById(R.id.AGE);
                TextView S_descriptions = view.findViewById(R.id.S_descriptions);


                User u = DATA.get(position);
                nom.setText(u.getPrenom()+" "+ u.getNom());
                number.setText(u.getPhone());
                if (u.getDescription().equals("")) S_descriptions.setText(u.getDescription());
                Tool.Load_Image(context,avatar,DATA.get(position).getUrlPhoto());

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                try {
                    Date d1 = new Date(sdf1.parse(DATA.get(position).getBirthday()).getTime());
                    int old = Integer.parseInt(sdf2.format(new Date())) - Integer.parseInt(sdf2.format(d1));
                    age.setText(String.valueOf(old).concat(" an(s)"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setView(view)
                        .setCancelable(false)
                        .setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                final AlertDialog alert = a.create();
                alert.show();

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
