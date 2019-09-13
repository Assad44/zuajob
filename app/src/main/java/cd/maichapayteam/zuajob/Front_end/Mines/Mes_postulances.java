package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Annonces_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Postullances_Base_Adapter;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Mes_postulances extends AppCompatActivity {

    Context context = this;
    GridView list;
    SearchView rechercher;

    ArrayList<Annonce> ANNOCE = new ArrayList<>();
    ArrayList<Annonce> SearchA = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        rechercher = findViewById(R.id.rechercher);
    }

    void Load_Annonce(){
        ANNOCE.clear();
        String description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
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
        }

        if (null == ANNOCE) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            list.setAdapter(new Postullances_Base_Adapter(context, ANNOCE,"mine"));
        }
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
        Load_Annonce();
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
                    list.setAdapter(new Annonces_Base_Adapter(context, ANNOCE,"mine"));
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
                list.setAdapter(new Annonces_Base_Adapter(context, SearchA,"mine"));
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
