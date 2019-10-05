package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Annonces_Base_Adapter_random;
import cd.maichapayteam.zuajob.Adaptors.Mes_Annonces_Base_Adapter;
import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;

public class Mes_annonces extends AppCompatActivity {

    Context context = this;
    GridView list;
    SearchView rechercher;

    SwipeRefreshLayout swipper;

    LinearLayout progressbar;

    ArrayList<Annonce> ANNOCE = new ArrayList<>();
    List<Annonce> ANNOCE_L = new ArrayList<>();
    ArrayList<Annonce> SearchA = new ArrayList<>();

    Mes_Annonces_Base_Adapter annonceAdapter;
    int turnA = 0;

    private void Init_Components(){
        list = findViewById(R.id.list);
        rechercher = findViewById(R.id.rechercher);
        swipper = findViewById(R.id.swipper);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.GONE);
    }

    void Load_Annonce(){
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
                ANNOCE_L.clear();
                ANNOCE_L = ManageLocalData.listMesAnnonces();
                for (Annonce c : ANNOCE_L){
                    ANNOCE.add(c);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);

                if (null == ANNOCE) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (ANNOCE.isEmpty()) Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
                else{
                    //Toast.makeText(context, "---------- "+ ANNOCE.size() , Toast.LENGTH_SHORT).show();
                    if (turnA != 0) {
                        annonceAdapter.notifyDataSetChanged();
                        return;
                    }
                    annonceAdapter = new Mes_Annonces_Base_Adapter(context, ANNOCE,"");
                    list.setAdapter(annonceAdapter);
                    list.setNumColumns(1);
                    turnA = 1;
                }

            }

        }.execute();
    }

    private void refresh(){
        // Todo ; launching methods
        Load_Annonce();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_annonces);

        getSupportActionBar().setTitle("Mes annonces");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        refresh();
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
                Load_Annonce();
            }
        });

        rechercher.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                SearchA.clear();

                if (newText.equals("")) {
                    list.setAdapter(new Annonces_Base_Adapter_random(context, ANNOCE,""));
                    return true;
                }

                for ( Annonce s : ANNOCE ) {
                    if (
                            s.getNomsUser().toUpperCase().contains(newText.toUpperCase()) ||
                                    String.valueOf(s.getMontant()).toUpperCase().equals(newText.toUpperCase())
                    ){
                        SearchA.add(s);
                    }
                }
                list.setAdapter(new Annonces_Base_Adapter_random(context, SearchA,""));
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
            i.putExtra("type", "other");
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
