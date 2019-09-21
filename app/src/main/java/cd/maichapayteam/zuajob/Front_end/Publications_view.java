package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
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

    ArrayList<Service> SERVICES = new ArrayList<>();
    List<Service> SERVICE_L = new ArrayList<>();
    ArrayList<Service> Search = new ArrayList<>();

    ArrayList<Annonce> ANNOCE = new ArrayList<>();
    List<Annonce> ANNOCE_L = new ArrayList<>();
    ArrayList<Annonce> SearchA = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        swipper = findViewById(R.id.swipper);
        rechercher = findViewById(R.id.rechercher);
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
        ANNOCE.clear();
        /*String description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
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
        }*/


        ANNOCE_L = GenerateData.listRandomAnnonce(list.getCount());
        ANNOCE = (ArrayList<Annonce>) ANNOCE_L;

        if (null == ANNOCE) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            list.setAdapter(new Annonces_Base_Adapter(context, ANNOCE,""));
        }
    }

    void Load_SERVICE(){
        SERVICES.clear();
        /*String description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

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
        }*/

        SERVICE_L = GenerateData.listRandomService(list.getCount());
        Log.e("FFFFFFFF", String.valueOf(SERVICE_L.size()));
        Toast.makeText(context, String.valueOf(SERVICE_L.size()), Toast.LENGTH_SHORT).show();
        SERVICES = (ArrayList<Service>) SERVICE_L;
        if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            list.setAdapter(new Services_Base_Adapter(context, SERVICES));
            list.setNumColumns(1);
        }

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
