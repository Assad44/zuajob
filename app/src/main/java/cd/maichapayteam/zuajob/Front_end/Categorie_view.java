package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Categorie2_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Categorie_Base_Adapter;
import cd.maichapayteam.zuajob.Back_end.Config;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Categories;
import cd.maichapayteam.zuajob.Back_end.Online.Queries_select;
import cd.maichapayteam.zuajob.Back_end.Online.Update;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.DAOClass.CategorieDAO;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.GenerateData;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;

public class Categorie_view extends AppCompatActivity {

    Context context = this;
    GridView list;

    ArrayList<Categorie> DATA = new ArrayList<>();
    List<Categorie> DATA1 = new ArrayList<>();
    ArrayList<O_Categories> DATA2 = new ArrayList<>();


    private void Init_Components(){
        list = findViewById(R.id.list);
        Log.e("AN_TEST", "je suis bien dans cette activité");
    }

    void Load_online(){
        String query = Queries_select.get_all_categorie();
        DATA2.clear();
        Log.e("AN_TEST", query);
        Log.e("AN_TEST", Config.SERVER_PATH);
        Log.e("AN_TEST", Config.auth);

        AndroidNetworking.post(Config.SERVER_PATH)
                .addBodyParameter("auth", Config.auth)
                .addBodyParameter("method","1")
                .addBodyParameter("query", query)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("AN_TEST", response.toString());
                        try {
                            for(int i = 0 ; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                // TODO  parsing datas
                                O_Categories c = new O_Categories();
                                c.setId_categorie(jsonObject.getString("id_categorie"));
                                c.setId_categorie(jsonObject.getString("designation_categorie"));
                                c.setId_categorie(jsonObject.getString("description_categorie"));
                                c.setId_categorie(jsonObject.getString("urlImage_categorie"));
                                DATA2.add(c);
                            }
                            if (null == DATA2)
                                Toast.makeText(context, "DATA2 null", Toast.LENGTH_SHORT).show();
                            else if (DATA2.size()<1)
                                Toast.makeText(context, "DATA2 size < 1", Toast.LENGTH_SHORT).show();
                            else{
                            }
                            list.setAdapter(new Categorie2_Base_Adapter(context, DATA2 ));
                        }catch (Exception e){
                            Log.e("AN_TEST_ERR", e.getMessage());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("AN_TEST_ERR", anError.getMessage());
                    }
                });
    }

    void Load_CAtegorie(){
        /*for (int i = 0; i < 10; i++) {
            Categorie c = new Categorie();
            c.setDesignation("Categorie "+i);
            c.setDescription("Description "+i);
            DATA.add(c);
        }
        */

        DATA1 = GenerateData.listCategorie();
        DATA = (ArrayList<Categorie>) DATA1;

        if (null == DATA) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(context, "" + DATA.size(), Toast.LENGTH_SHORT).show();
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
