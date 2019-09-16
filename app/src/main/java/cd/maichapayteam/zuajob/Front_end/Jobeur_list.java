package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Jobeur_Base_Adapter;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.GenerateData;

public class Jobeur_list extends AppCompatActivity {

    Context context = this;
    GridView list;

    List<User> DATA = new ArrayList<User>();

    private void Init_Components(){
        list = findViewById(R.id.list);
        Load_Jobeur();
    }

    void Load_Jobeur(){
        DATA = GenerateData.listJobeurs(0);
        if (null == DATA) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else {
            //Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show();
            list.setAdapter(new Jobeur_Base_Adapter(context, DATA));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobeur_list);
        getSupportActionBar().setTitle("Liste jobeurs");
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                View view  = LayoutInflater.from(context).inflate(R.layout.view_jobeurs_details2,null);
                TextView nom = view.findViewById(R.id.nom);
                TextView number = view.findViewById(R.id.number);
                TextView realisation = view.findViewById(R.id.realisation);

                User u = DATA.get(position);
                nom.setText(u.getNom());
                number.setText(u.getPhone());
                //nom.setText(u.getnoreaPhone());
                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setView(view)
                        .setCancelable(false)
                        .setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                final AlertDialog alert = a.create();
                alert.show();

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
