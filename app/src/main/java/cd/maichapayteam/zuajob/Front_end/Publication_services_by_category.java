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

import cd.maichapayteam.zuajob.Adaptors.Annonces_Base_Adapter_random;
import cd.maichapayteam.zuajob.Adaptors.Services_Base_Adapter;
import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GenerateData;

public class Publication_services_by_category extends AppCompatActivity {

    Context context = this;
    String title = "";
    GridView list;
    SearchView rechercher;
    SwipeRefreshLayout swipper;

    LinearLayout progressbar;

    ArrayList<Service> SERVICES = new ArrayList<>();
    List<Service> SERVICE_L = new ArrayList<>();
    ArrayList<Service> Search = new ArrayList<>();


    Services_Base_Adapter serviceAdapter;
    int turn = 0;

    private void Init_Components(){
        list = findViewById(R.id.list);
        swipper = findViewById(R.id.swipper);
        rechercher = findViewById(R.id.rechercher);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.GONE);
    }

    private void Load_Header(){
        if (!getIntent().hasExtra("ID_CATEGORIE")) onBackPressed();
        getSupportActionBar().setTitle(getIntent().getExtras().getString("CATEGORIE"));
        getSupportActionBar().setSubtitle(getIntent().getExtras().getString("SOUSCATEGORIE"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);
    }

    void Load_SERVICE(){
        AsyncTask task = new AsyncTask() {
            int cout = list.getCount();
            int ids = (int) getIntent().getExtras().getLong("ID_SOUSCATEGORIE");
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                SERVICE_L.clear();
                SERVICE_L = GenerateData.listNewService(cout, ids);
                for (Service c : SERVICE_L){
                    SERVICES.add(c);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                progressbar.setVisibility(View.GONE);
                if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    Log.e("SSSSS", String.valueOf(SERVICES.size()));
                    Toast.makeText(context, "---------- "+ SERVICES.size() , Toast.LENGTH_SHORT).show();
                    if (turn != 0) {
                        serviceAdapter.notifyDataSetChanged();
                        return;
                    }
                    serviceAdapter = new Services_Base_Adapter(context, SERVICES);
                    turn = 1;
                    list.setAdapter(serviceAdapter);
                    list.setNumColumns(1);
                }
            }

        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_services_by_category);

        Init_Components();

        // Todo ; launching methods
        Load_Header();
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
                Load_SERVICE();
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
                    list.setAdapter(new Services_Base_Adapter(context, SERVICES));
                    list.setNumColumns(1);
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
                list.setAdapter(new Services_Base_Adapter(context, Search));
                list.setNumColumns(1);

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
        Intent i = new Intent(context, Sous_categories.class);
        i.putExtra("title",getIntent().getExtras().getString("CATEGORIE"));
        i.putExtra("id",getIntent().getExtras().getString("ID_CATEGORIE"));
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



