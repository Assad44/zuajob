package cd.maichapayteam.zuajob.Front_end.Mines;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Webpaiemnt;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;

public class Mes_rendez_vous_sollicitations extends AppCompatActivity {
    Context context = this;
    ListView list;
    Spinner type;
    SearchView rechercher;
    SwipeRefreshLayout swipper;
    LinearLayout sous, sous2;

    List<Sollicitation> SOLLICITATION_ATTENTE;
    List<Sollicitation> SOLLICITATION;

    private void Init_Components(){
        list = findViewById(R.id.list);
        sous = findViewById(R.id.sous);
        sous2 = findViewById(R.id.sous2);
        type = findViewById(R.id.type);
        swipper = findViewById(R.id.swiper);
    }

    void RDV_SOLLICITATION_ATTENTE() {
        new AsyncTask() {


            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                //Toast.makeText(context, "---------- "+ cout , Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                SOLLICITATION_ATTENTE = (List<Sollicitation>) ManageLocalData.mesRDVenAttente().get(1);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);

                if (null == SOLLICITATION_ATTENTE)
                    Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else {
                    for (final Sollicitation c : SOLLICITATION_ATTENTE) {
                        View convertView = LayoutInflater.from(context).inflate(R.layout.view_rdv_en_attente, null);

                        final CardView element = convertView.findViewById(R.id.element);
                        TextView date_time = convertView.findViewById(R.id.date_time);
                        TextView user = convertView.findViewById(R.id.user);
                        TextView advice = convertView.findViewById(R.id.advice);
                        TextView categorie = convertView.findViewById(R.id.categorie);
                        TextView BTN_confirmer = convertView.findViewById(R.id.BTN_confirmer);
                        ImageView annuler_rdv = convertView.findViewById(R.id.annuler_rdv);


                        user.setText(c.getNomsUser());
                        date_time.setText(c.getDateRDV() + " à " + c.getHeureRDV());
                        categorie.setText(c.getCategorie() + " | " + c.getSouscategorie());

                        if (c.isHaveSollicited() == true){
                            BTN_confirmer.setVisibility(View.VISIBLE);
                            advice.setVisibility(View.GONE);
                        }else{
                            BTN_confirmer.setVisibility(View.GONE);
                            advice.setVisibility(View.VISIBLE);
                        }

                        sous.addView(convertView, 0);

                        element.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        });


                        annuler_rdv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder a = new AlertDialog.Builder(context, R.style.MyDialogTheme)
                                        .setTitle("Confirmation")
                                        .setMessage("Voulez-vous vraimment annuler ce rendez-vous ?")
                                        .setCancelable(true)
                                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                refuser_sollicitaion(c.getId());
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
                        BTN_confirmer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                valider_sollicitaion(c.getId());
                            }
                        });

                    }

                }

            }

        }.execute();

    }

    private void valider_sollicitaion(final long id){
        AsyncTask aaa = new AsyncTask<Void, Void, Sollicitation>() {
            Service s = new Service();
            View convertView  = LayoutInflater.from(context).inflate(R.layout.view_progressebar,null);
            TextView write_response = convertView.findViewById(R.id.write_response);
            AlertDialog.Builder a = new AlertDialog.Builder(context)
                    .setView(convertView)
                    .setCancelable(false);
            // Setting dialogview
            final AlertDialog alert = a.create();

            @Override
            protected Sollicitation doInBackground(Void... voids) {
                return ManageLocalData.confirmerRDVbyUser(id,"");
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                write_response.setText("Opération encours...");
                alert.show();
            }

            @Override
            protected void onPostExecute(Sollicitation service) {
                alert.cancel();

                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                if (service.isError() == true ){
                    a.setMessage(service.getErrorMessage()+ " "+service.getErrorCode());

                }else{
                    a.setMessage("Opération réussi");
                    //Intent i = new Intent(context, Webpaiemnt.class);
                    //i.putExtra("HTML", service.getHtml());
                    //context.startActivityForResult(i, 5323);
                    //startActivityForResult(i, 5323);
                }
                //startActivity(new Intent(context, Publication_blank.class));
                //finish();
                a.show();
            }
        }.execute();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==5323) {
            RDV_SOLLICITATION_ATTENTE();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void refuser_sollicitaion(final long id){
        AsyncTask aaa = new AsyncTask<Void, Void, Sollicitation>() {
            Service s = new Service();
            View convertView  = LayoutInflater.from(context).inflate(R.layout.view_progressebar,null);
            TextView write_response = convertView.findViewById(R.id.write_response);
            AlertDialog.Builder a = new AlertDialog.Builder(context)
                    .setView(convertView)
                    .setCancelable(false);
            // Setting dialogview
            final AlertDialog alert = a.create();

            @Override
            protected Sollicitation doInBackground(Void... voids) {
                return ManageLocalData.refuserSollicitation(id);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                write_response.setText("Opération encours...");
                alert.show();
            }

            @Override
            protected void onPostExecute(Sollicitation service) {
                alert.cancel();
                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                if (service.isError() == true ){
                    a.setMessage(service.getErrorMessage()+ " "+service.getErrorCode());
                }else{
                    a.setMessage("Opération réussi");

                }
                a.show();

                startActivity(new Intent(context, Publication_blank.class));
                finish();
            }
        }.execute();

    }

    private void Send_cotation(final long idpos, final float rating, final String com){
        AsyncTask aaa = new AsyncTask<Void, Void, Sollicitation>() {
            Service s = new Service();
            View convertView  = LayoutInflater.from(context).inflate(R.layout.view_progressebar,null);
            TextView write_response = convertView.findViewById(R.id.write_response);
            AlertDialog.Builder a = new AlertDialog.Builder(context)
                    .setView(convertView)
                    .setCancelable(false);
            // Setting dialogview
            final AlertDialog alert = a.create();

            @Override
            protected Sollicitation doInBackground(Void... voids) {
                return ManageLocalData.serviceRenduBySollicitance(idpos, (int) rating, com);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                write_response.setText("Opération encours...");
                alert.show();
            }

            @Override
            protected void onPostExecute(Sollicitation service) {
                alert.cancel();
                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                if (service.isError() == true ){
                    a.setMessage(service.getErrorMessage()+ " "+service.getErrorCode());
                }else{
                    a.setMessage("Opération réussi");
                }
                a.show();

                startActivity(new Intent(context, Publication_blank.class));
                finish();
            }
        }.execute();

    }

    void RDV_SOLLICITER(){
        new AsyncTask() {

            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                SOLLICITATION = (List<Sollicitation>) ManageLocalData.mesRDV().get(1);
                /*if (SOLLICITATION.isEmpty()){
                    Sollicitation s = new Sollicitation();
                    s.setDate("2019-10-01");
                    SOLLICITATION.add(s);
                }*/
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == SOLLICITATION) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    for ( final Sollicitation c : SOLLICITATION) {
                        View convertView = LayoutInflater.from(context).inflate(R.layout.view_rdv, null);

                        final LinearLayout details_option = convertView.findViewById(R.id.details_option);
                        final CardView element = convertView.findViewById(R.id.element);
                        TextView user = convertView.findViewById(R.id.user);
                        TextView categorie = convertView.findViewById(R.id.categorie);
                        TextView date_time = convertView.findViewById(R.id.date_time);
                        TextView BTN_valider = convertView.findViewById(R.id.BTN_valider);
                        TextView annuler_rdv = convertView.findViewById(R.id.annuler_rdv);
                        TextView editer_heure = convertView.findViewById(R.id.editer_heure);
                        TextView coter = convertView.findViewById(R.id.coter);

                        details_option.setVisibility(View.GONE);
                        user.setText(c.getNomsUser());
                        date_time.setText(c.getDateRDV() + " à " + c.getHeureRDV());
                        categorie.setText(c.getCategorie() + " | " + c.getSouscategorie());

                        BTN_valider.setText("Confirmer le service rendu");
                        if (c.isMy() != true){
                            BTN_valider.setVisibility(View.VISIBLE);
                            //advice.setVisibility(View.GONE);
                        }else{
                            BTN_valider.setVisibility(View.GONE);
                            //advice.setVisibility(View.VISIBLE);
                        }

                        sous2.addView(convertView, 0);

                        coter.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(View v) {
                                View convertView  = LayoutInflater.from(context).inflate(R.layout.view_dialog_options,null);
                                final RatingBar rating = convertView.findViewById(R.id.rating);
                                final EditText com = convertView.findViewById(R.id.com);
                                final TextView text = convertView.findViewById(R.id.text);
                                text.setText("0.0 / 5");
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
                                                Send_cotation(c.getId(),cote,com.getText().toString());
                                                dialog.dismiss();
                                            }
                                        }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });;
                                final AlertDialog alert = a.create();
                                alert.show();
                            }
                        });
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
                                                refuser_sollicitaion(c.getId());
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
                        BTN_valider.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final Animation dropdup = AnimationUtils.loadAnimation(context,R.anim.move_up);
                                final Animation dropdown = AnimationUtils.loadAnimation(context,R.anim.move_down);
                                if (details_option.getVisibility() == View.GONE){
                                    details_option.setAnimation(dropdown);
                                    details_option.setVisibility(View.VISIBLE);
                                }else{
                                    details_option.setAnimation(dropdup);
                                    details_option.setVisibility(View.GONE);
                                }
                            }
                        });
                    }




                    //list.setAdapter(new Rdv_Base_Adapter(context, (ArrayList<Sollicitation>) SOLLICITATION));
                }

            }

        }.execute();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_rendez_vous);
        getSupportActionBar().setTitle("Mes rendez-vous");
        getSupportActionBar().setSubtitle("Pour mes sollicitaions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Init_Components();

        // Todo ; launching methods
        RDV_SOLLICITER();
        RDV_SOLLICITATION_ATTENTE();
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
                /*if (position == 0){
                    RDV_SOLLICITER();
                }else RDV_POSTULER();*/
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
