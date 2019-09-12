package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Sollicitations_Base_Adapter;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Mes_Sollicitations extends AppCompatActivity {

    Context context = this;
    ListView list;
    SearchView rechercher;

    ArrayList<Sollicitation> SERVICES = new ArrayList<>();
    ArrayList<Sollicitation> Search = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        rechercher = findViewById(R.id.rechercher);
    }

    void Load_SOLLICITATION(){
        SERVICES.clear();
        String description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

        int j = 11;
        for (int i = 0; i < 10; i++) {
            Sollicitation s = new Sollicitation();
            s.setNomsUser(Tool.Versions()[i]);
            s.setDescriptionService(description);
            s.setMontant(new Random().nextInt(50));
            s.setCategorie("Catégorie "+i);
            s.setSouscategorie("Sous catégorie "+i);
            s.setDevise("USD");
            s.setPhoneUser("+243 81 451 10 83");
            String m = String.valueOf(j);
            if (m.length()<= 1){
                m = "0"+m;
            }
            s.setDate("2019-09-"+m+" 02:42:00");
            j = j-1;
            SERVICES.add(s);
        }


        if (null == SERVICES) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            list.setAdapter(new Sollicitations_Base_Adapter(context, SERVICES));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes__sollicitations);

        getSupportActionBar().setTitle("Mes sollicitations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        // Todo ; launching methods
        Load_SOLLICITATION();
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
                    list.setAdapter(new Sollicitations_Base_Adapter(context, SERVICES));
                    return true;
                }

                for ( Sollicitation s : SERVICES ) {
                    if (
                            s.getNomsUser().toUpperCase().contains(newText.toUpperCase()) ||
                                    String.valueOf(s.getMontant()).toUpperCase().equals(newText.toUpperCase())
                    ){
                        Search.add(s);
                    }
                }
                list.setAdapter(new Sollicitations_Base_Adapter(context, Search));
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Home.class);
        startActivity(i);
        Tool.setUserPreferences(context, "recent","");
        finish();
    }

}
