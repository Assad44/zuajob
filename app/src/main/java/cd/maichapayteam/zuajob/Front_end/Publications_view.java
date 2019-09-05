package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Adaptors.Services_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Test_Base_Adapter;
import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.R;

public class Publications_view extends AppCompatActivity {

    Context context = this;
    ListView list;

    private void Init_Components(){
        list = findViewById(R.id.list);
    }

    private void Load_Datas(){
        if (!getIntent().hasExtra("type")) onBackPressed();

        if (getIntent().getExtras().getString("type").equals("annonces")){
            ArrayList<Service> DATA = Service.listService();
            list.setAdapter(new Services_Base_Adapter(context, DATA));
        }else{

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_view);
        getSupportActionBar().setTitle("Publications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        // Todo ; launching methods
        Load_Datas();
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
}
