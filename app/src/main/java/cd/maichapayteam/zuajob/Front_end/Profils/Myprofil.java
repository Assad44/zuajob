package cd.maichapayteam.zuajob.Front_end.Profils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Home;
import cd.maichapayteam.zuajob.Front_end.Signup.PhoneVerif_screen;
import cd.maichapayteam.zuajob.Front_end.Signup.index_screen;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Myprofil extends AppCompatActivity {

    Context context = this;
    TextView btn_jobeur,btn_simple_user;
    ImageView btn_back_arrow;

    Toolbar toolbar;


    private void Init_Components(){
        /*btn_jobeur = findViewById(R.id.btn_jobeur);
        btn_simple_user = findViewById(R.id.btn_simple_user);
        btn_back_arrow = findViewById(R.id.btn_back_arrow);*/
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofil);
        // Initialisation des composants
        Init_Components();

        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            //getSupportActionBar().setIcon(R.drawable.ic_back_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.publication) {
            Intent i = new Intent(context, Publication_blank.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.share) {
            Toast.makeText(context, "Not yet done", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_settings) {
            Toast.makeText(context, "Not yet done", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.nav_exit) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
