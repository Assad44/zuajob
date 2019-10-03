package cd.maichapayteam.zuajob.Front_end.Notification_result_classes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;

import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.R;

public class Results extends AppCompatActivity {

    Context context = this;
    ListView list;
    SearchView rechercher;
    SwipeRefreshLayout swipper;


    private void Init_Components(){
        list = findViewById(R.id.list);
        rechercher = findViewById(R.id.rechercher);
        swipper = findViewById(R.id.swipper);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setTitle("Notification");
        getSupportActionBar().setElevation(0);

        Init_Components();

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
