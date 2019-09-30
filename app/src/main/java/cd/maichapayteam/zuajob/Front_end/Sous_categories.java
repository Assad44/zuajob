package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Sous_cat_Base_Adapter;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;

public class Sous_categories extends AppCompatActivity {

    Context context = this;
    ListView list;
    Spinner list_sous_cat,type;
    LinearLayout option_bar;
    SwipeRefreshLayout swiper;
    List<SousCategorie> LSC = new ArrayList<>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        list_sous_cat = findViewById(R.id.list_sous_cat);
        type = findViewById(R.id.type);
        option_bar = findViewById(R.id.option_bar);
        swiper = findViewById(R.id.swiper);

    }

    void Load_SCAT(){

        // TODO la tache asynchronne
        new AsyncTask<String, Void, String>(){
            Categorie sc = new Categorie();

            @Override

            protected void onPreExecute() {
                super.onPreExecute();
                sc.setId(Long.parseLong(getIntent().getExtras().getString("id")));
                swiper.setRefreshing(true);
            }

            @Override
            protected String doInBackground(String... strings) {
                LSC = ManageLocalData.listSousCategorie(sc);
                return null;
            }
            @Override
            protected void onPostExecute(String o) {
                swiper.setRefreshing(false);
                if (null == LSC) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    list.setAdapter(new Sous_cat_Base_Adapter(
                            context,
                            LSC,
                            getIntent().getExtras().getString("title"),
                            getIntent().getExtras().getString("id")));
                }
            }
        }.execute();

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
        Intent i = new Intent(context, Categorie_view.class);
        startActivity(i);
        finish();
    }

}
