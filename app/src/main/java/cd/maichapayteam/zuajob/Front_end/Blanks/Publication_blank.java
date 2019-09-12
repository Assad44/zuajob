package cd.maichapayteam.zuajob.Front_end.Blanks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cd.maichapayteam.zuajob.Front_end.Publications_view;
import cd.maichapayteam.zuajob.R;

public class Publication_blank extends AppCompatActivity {

    Context context = this;
    String title = "";

    private void Init_Components(){
        //list = findViewById(R.id.list);
    }

    private void Load_Header(){
        if (!getIntent().hasExtra("type")) onBackPressed();
        title = getIntent().getExtras().getString("type");
        getSupportActionBar().setTitle("Publication "+title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_blank);

        Init_Components();
        Load_Header();

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
        if (!getIntent().hasExtra("type")) onBackPressed();
        title = getIntent().getExtras().getString("type");
        if (title.equals("Services")) {
            i = new Intent(context, Publications_view.class);
            i.putExtra("type", "Services");
        }else{
            i = new Intent(context, Publications_view.class);
            i.putExtra("type", "Annonces");
        }
        startActivity(i);
        finish();
    }

}
