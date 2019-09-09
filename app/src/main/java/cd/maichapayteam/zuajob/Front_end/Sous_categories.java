package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Services_Base_Adapter;
import cd.maichapayteam.zuajob.BackEnd.Objects.Services;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Sous_categories extends AppCompatActivity {

    Context context = this;
    ListView list;
    Spinner list_sous_cat;
    ArrayList<String> SCAT = new ArrayList<>();
    ArrayList<Services> SERVICES = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        list_sous_cat = findViewById(R.id.list_sous_cat);
    }

    void Load_SCAT(){
        for (int i = 0; i < 10; i++) {
            SCAT.add("Sous Categorie "+i+1);
        }
        Tool.setEntries(context,list_sous_cat, SCAT);
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
            Toast.makeText(context, "" + SERVICES.size(), Toast.LENGTH_SHORT).show();
            list.setAdapter(new Services_Base_Adapter(context, SERVICES));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sous_categories);
        if (!getIntent().hasExtra("title")) onBackPressed();
        getSupportActionBar().setTitle(getIntent().getExtras().getString("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();
        Load_SCAT();
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

        list_sous_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Load_SERVICE();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Categorie_view.class);
        startActivity(i);
        finish();
    }

}
