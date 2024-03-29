package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Categorie_Base_Adapter;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Categories;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;

public class Categorie_view extends AppCompatActivity {

    Context context = this;
    GridView list;
    SwipeRefreshLayout swipper;

    ArrayList<Categorie> DATA = new ArrayList<>();
    List<Categorie> DATA1 = new ArrayList<>();
    ArrayList<O_Categories> DATA2 = new ArrayList<>();


    private void Init_Components(){
        list = findViewById(R.id.list);
        swipper = findViewById(R.id.swipper);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setTitle("Les catégories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Init_Components();
        //Load_online();
        LoadCategories loadCategories = new LoadCategories();
        loadCategories.execute();
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
                LoadCategories loadCategories = new LoadCategories();
                loadCategories.execute();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Home.class);
        startActivity(i);
        finish();
    }

    private class LoadCategories extends AsyncTask<String, String, List<Categorie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipper.setRefreshing(true);
        }

        @Override
        protected List<Categorie> doInBackground(String... strings) {
            return ManageLocalData.listCategorie();
        }

        @Override
        protected void onPostExecute(List<Categorie> categories) {
            swipper.setRefreshing(false);
            //DATA1 = ManageLocalData.listCategorie();
            DATA.clear();
            DATA = (ArrayList<Categorie>) categories;

            if (null == DATA) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
            else{
                //Toast.makeText(context, "" + DATA.size(), Toast.LENGTH_SHORT).show();
                list.setAdapter(new Categorie_Base_Adapter(context, DATA ));
            }

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.e("DDDDDDDDDDDDD", String.valueOf(DATA.get(position).getId()));
                    Log.e("DDDDDDDDDDDDD", String.valueOf(DATA.get(position).getDesignation()));
                    String identifiant = String.valueOf(DATA.get(position).getId());
                    String designation = String.valueOf(DATA.get(position).getDesignation());
                    Intent i = new Intent(context, Sous_categories.class);
                    i.putExtra("id",identifiant);
                    i.putExtra("title",designation);
                    startActivity(i);
                    finish();
                }
            });

            super.onPostExecute(categories);
        }
    }

}
