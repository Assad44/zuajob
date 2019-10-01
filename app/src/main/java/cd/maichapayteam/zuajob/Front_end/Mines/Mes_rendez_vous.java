package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Adaptors.Rdv_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Sollicitations_Base_Adapter;
import cd.maichapayteam.zuajob.Front_end.Categorie_view;
import cd.maichapayteam.zuajob.Front_end.Sous_categories;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;
import pl.droidsonroids.gif.GifDrawable;

public class Mes_rendez_vous extends AppCompatActivity {
    Context context = this;
    ListView list;
    Spinner type;
    SearchView rechercher;
    SwipeRefreshLayout swipper;
    LinearLayout sous;

    ArrayList<Sollicitation> SERVICES = new ArrayList<>();
    ArrayList<Sollicitation> Search = new ArrayList<>();


    List<Postuler> POSTULLER;
    List<Sollicitation> SOLLICITATION;

    private void Init_Components(){
        list = findViewById(R.id.list);
        sous = findViewById(R.id.sous);
        type = findViewById(R.id.type);
        swipper = findViewById(R.id.swiper);
    }

    void RDV_POSTULER(){

        new AsyncTask() {

            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                //Toast.makeText(context, "---------- "+ cout , Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                POSTULLER = (List<Postuler>) ManageLocalData.mesRDVenAttente().get(0);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);

                if (null == POSTULLER) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    sous.removeAllViews();
                    for ( final Postuler c : POSTULLER) {
                        LayoutInflater inflater = LayoutInflater.from(context);
                        View responses = inflater.inflate(R.layout.view_rdv, null);
                        sous.addView(responses, 0);

                    }
                }

            }

        }.execute();

    }
    void RDV_SOLLICITER(){

        new AsyncTask() {

            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                //Toast.makeText(context, "---------- "+ cout , Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                SOLLICITATION = (List<Sollicitation>) ManageLocalData.mesRDVenAttente().get(1);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);

                if (null == SOLLICITATION) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    sous.removeAllViews();
                    for ( final Sollicitation c : SOLLICITATION) {
                        LayoutInflater inflater = LayoutInflater.from(context);
                        View convertView = inflater.inflate(R.layout.view_rdv, null);

                        final LinearLayout details_option = convertView.findViewById(R.id.details_option);
                        final CardView element = convertView.findViewById(R.id.element);
                        TextView BTN_valider = convertView.findViewById(R.id.BTN_valider);
                        TextView annuler_rdv = convertView.findViewById(R.id.annuler_rdv);
                        TextView editer_heure = convertView.findViewById(R.id.editer_heure);
                        TextView coter = convertView.findViewById(R.id.coter);


                        annuler_rdv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder a = new AlertDialog.Builder(context,R.style.MyDialogTheme)
                                        .setTitle("Confirmation")
                                        .setMessage("Voulez-vous vraimment annuler ce rendez-vous ?")
                                        .setCancelable(true)
                                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                final AlertDialog alert = a.create();
                                alert.show();
                            }
                        });


                        coter.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(View v) {
                                View convertView  = LayoutInflater.from(context).inflate(R.layout.view_dialog_options,null);
                                final RatingBar rating = convertView.findViewById(R.id.rating);
                                final TextView text = convertView.findViewById(R.id.text);
                                text.setVisibility(View.GONE);

                                rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                    @Override
                                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                        text.setVisibility(View.VISIBLE);
                                        text.setText(String.valueOf( rating ).concat(" / 5"));
                                    }
                                });

                                AlertDialog.Builder a = new AlertDialog.Builder(context)
                                        .setView(convertView)
                                        .setCancelable(true)
                                        .setPositiveButton("Coter", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                float cote = rating.getRating();
                                                dialog.dismiss();
                                            }
                                        })
                                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });;
                                final AlertDialog alert = a.create();
                                alert.show();
                            }
                        });

                        sous.addView(convertView, 0);

                    }
                }

            }

        }.execute();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_rendez_vous);
        getSupportActionBar().setTitle("Mes rendez-vous");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        // Todo ; launching methods
        RDV_SOLLICITER();
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

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    RDV_SOLLICITER();
                }else RDV_POSTULER();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
