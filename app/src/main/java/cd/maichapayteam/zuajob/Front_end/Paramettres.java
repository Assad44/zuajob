package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Front_end.Signup.Password_screen;
import cd.maichapayteam.zuajob.Front_end.Signup.Preferences_screen;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Paramettres extends AppCompatActivity {

    Context context = this;
    CheckBox prestataire,notification;

    private void Init_Components(){
        //TODO si l'utilisateur a moins de 18 ans n'afficher pas la caégorie sexe
        notification = findViewById(R.id.notification);
        prestataire = findViewById(R.id.prestataire);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paramettres);
        getSupportActionBar().setTitle("Mes paramettres");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();
        load();
    }

    void load(){
        if (Tool.getUserPreferences(context,"type").equals("1"))
            prestataire.setChecked(true);
        else
            prestataire.setChecked(false);

        if (Tool.getUserPreferences(context,"notification").equals("1"))
            notification.setChecked(true);
        else
            notification.setChecked(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        prestataire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Tool.getUserPreferences(context,"type").equals("1"))
                    Tool.setUserPreferences(context,"type", "0");
                else
                    Tool.setUserPreferences(context,"type", "1");

                load();
            }
        });
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Tool.getUserPreferences(context,"notification").equals("1"))
                    Tool.setUserPreferences(context,"notification", "0");
                else
                    Tool.setUserPreferences(context,"notification", "1");

                load();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Home.class);
        startActivity(i);
        finish();
    }

}
