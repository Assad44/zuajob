package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Annonces_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Services_Base_Adapter;
import cd.maichapayteam.zuajob.BackEnd.Objects.Services;
import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.R;

public class Publications_view extends AppCompatActivity {

    Context context = this;
    String title = "";
    ListView list;
    SearchView rechercher;


    ArrayList<Services> SERVICES = new ArrayList<>();
    ArrayList<Services> Search = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
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
    void Load_SERVICE(){
        SERVICES.clear();
        for (int i = 0; i < 10; i++) {
            Services s = new Services();
            s.setNom_user("Deon Mass 00"+i);
            s.setDescription_services("Je fais bien le service mais j'aime qu'on respect mon travail");
            s.setPrix(String.valueOf(new Random().nextInt(50)));
            s.setNbr_cote(String.valueOf(new Random().nextInt(200)));
            s.setNbr_services(String.valueOf(new Random().nextInt(20)));
            SERVICES.add(s);
        }

        if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            if (title.equals("Services")) list.setAdapter(new Services_Base_Adapter(context, SERVICES));
            else list.setAdapter(new Annonces_Base_Adapter(context, SERVICES));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_view);
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
                    return true;
                }

                for ( Services s : SERVICES ) {
                    if (
                            s.getNom_user().toUpperCase().equals(newText.toUpperCase()) ||
                            s.getPrix().toUpperCase().equals(newText.toUpperCase())
                        ){
                        Search.add(s);
                    }
                }
                list.setAdapter(new Services_Base_Adapter(context, Search));
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
            i.putExtra("from", "servicesList");
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
