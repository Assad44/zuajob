package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Adaptors.Categorie_Base_Adapter;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.R;

public class Categorie_view extends AppCompatActivity {

    Context context = this;
    GridView list;

    ArrayList<Categorie> DATA = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
    }

    void Load_CAtegorie(){
        for (int i = 0; i < 10; i++) {
            Categorie c = new Categorie();
            c.setDesignation("Categorie "+i);
            c.setDescription("Description "+i);
            DATA.add(c);
        }
        if (null == DATA) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(context, "" + DATA.size(), Toast.LENGTH_SHORT).show();
            list.setAdapter(new Categorie_Base_Adapter(context, DATA ));
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(context, Sous_categories.class);
                i.putExtra("title",DATA.get(position).getDesignation());
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setTitle("Les catégories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Init_Components();
        Load_CAtegorie();
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

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Home.class);
        startActivity(i);
        finish();
    }



}
