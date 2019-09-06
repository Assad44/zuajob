package cd.maichapayteam.zuajob.Front_end.Blanks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.Front_end.Home;
import cd.maichapayteam.zuajob.Front_end.Publications_view;
import cd.maichapayteam.zuajob.R;

public class Publication_blank extends AppCompatActivity {

    Context context = this;

    private void Init_Components(){
        //list = findViewById(R.id.list);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_blank);
        getSupportActionBar().setTitle("Publication d'un service");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

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
        Intent i;
        i = new Intent(context, Publications_view.class);
        startActivity(i);
        finish();
    }

}
