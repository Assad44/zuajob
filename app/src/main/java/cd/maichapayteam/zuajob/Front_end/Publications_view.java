package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Annonces_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Services_Base_Adapter;
import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GenerateData;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Publications_view extends AppCompatActivity {

    Context context = this;
    String title = "";
    GridView list;
    SearchView rechercher;
    SwipeRefreshLayout swipper;

    LinearLayout progressbar;

    ArrayList<Service> SERVICES = new ArrayList<>();
    List<Service> SERVICE_L = new ArrayList<>();
    ArrayList<Service> Search = new ArrayList<>();

    ArrayList<Annonce> ANNOCE = new ArrayList<>();
    List<Annonce> ANNOCE_L = new ArrayList<>();
    ArrayList<Annonce> SearchA = new ArrayList<>();

    Services_Base_Adapter serviceAdapter;
    int turn = 0;

    Annonces_Base_Adapter annonceAdapter;
    int turnA = 0;

    private void Init_Components(){
        list = findViewById(R.id.list);
        swipper = findViewById(R.id.swipper);
        rechercher = findViewById(R.id.rechercher);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.GONE);
    }

    private void Load_Header(){
        if (!getIntent().hasExtra("type")) onBackPressed();
        title = getIntent().getExtras().getString("type");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);
    }

    void Load_Annonce(){

        AsyncTask task = new AsyncTask() {
            int cout = list.getCount();
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                Toast.makeText(context, "---------- "+ cout , Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                ANNOCE_L = GenerateData.listRandomAnnonce(cout);
                for (Annonce c : ANNOCE_L){
                    ANNOCE.add(c);
                }
                //ANNOCE = (ArrayList<Annonce>) ANNOCE_L;
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                progressbar.setVisibility(View.GONE);

                if (null == ANNOCE) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    if (turnA != 0) {
                        annonceAdapter.notifyDataSetChanged();
                        return;
                    }
                    annonceAdapter = new Annonces_Base_Adapter(context, ANNOCE,"");
                    list.setAdapter(annonceAdapter);
                }


            }

        }.execute();

    }

    void Load_SERVICE(){
        AsyncTask task = new AsyncTask() {
            int cout = list.getCount();
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                Toast.makeText(context, "---------- "+ cout , Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                SERVICE_L.clear();
                SERVICE_L = GenerateData.listRandomService(cout);
                for (Service c : SERVICE_L){
                    SERVICES.add(c);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                progressbar.setVisibility(View.GONE);
                serviceAdapter = new Services_Base_Adapter(context, SERVICES);
                if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    Log.e("SSSSS", String.valueOf(SERVICES.size()));
                    if (turn != 0) {
                        serviceAdapter.notifyDataSetChanged();
                        return;
                    }
                    list.setAdapter(serviceAdapter);
                    list.setNumColumns(1);
                }
            }

        }.execute();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_view);
        Init_Components();

        // Todo ; launching methods
        Load_Header();
        if (title.equals("Services")) Load_SERVICE();
        else Load_Annonce();

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
                                Load_SERVICE();
                                turn = 1;
                                //progressbar.setVisibility(View.GONE);
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
                if (title.equals("Services")) Load_SERVICE();
                else Load_Annonce();
                swipper.setRefreshing(false);
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
                SearchA.clear();

                if (newText.equals("")) {
                    if (title.equals("Services")) {
                        list.setAdapter(new Services_Base_Adapter(context, SERVICES));
                        list.setNumColumns(1);
                    }
                    else list.setAdapter(new Annonces_Base_Adapter(context, ANNOCE,""));
                    return true;
                }

                if (title.equals("Services")){
                    for ( Service s : SERVICES ) {
                        if (
                                s.getNomsJobeur().toUpperCase().contains(newText.toUpperCase()) ||
                                String.valueOf(s.getMontant()).toUpperCase().equals(newText.toUpperCase())
                        ){
                            Search.add(s);
                        }
                    }
                    list.setAdapter(new Services_Base_Adapter(context, Search));
                    list.setNumColumns(1);
                }else{
                    for ( Annonce s : ANNOCE ) {
                        if (
                                s.getNomsUser().toUpperCase().contains(newText.toUpperCase()) ||
                                String.valueOf(s.getMontant()).toUpperCase().equals(newText.toUpperCase())
                        ){
                            SearchA.add(s);
                        }
                    }
                    list.setAdapter(new Annonces_Base_Adapter(context, SearchA,""));
                }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            Intent i = new Intent(context, Publication_blank.class);
            i.putExtra("type", title);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
