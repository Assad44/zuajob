package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Paramettres extends AppCompatActivity {

    Context context = this;
    User u = GeneralClass.Currentuser;

    LinearLayout about_U,changerpasse;
    TextView type_conte;
    CheckBox prestataire,notification,alarme;

    private void Init_Components(){
        //TODO si l'utilisateur a moins de 18 ans n'afficher pas la caégorie sexe
        notification = findViewById(R.id.notification);
        prestataire = findViewById(R.id.prestataire);
        about_U = findViewById(R.id.about_U);
        alarme = findViewById(R.id.alarme);
        type_conte = findViewById(R.id.type_conte);
        changerpasse = findViewById(R.id.changerpasse);

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
        if (u.getType() == 0) type_conte.setText("Demandeur ou chercheur de service");
        else type_conte.setText("Prestataire ou offreur des services");


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

        changerpasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasseWord();
            }
        });

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
        alarme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Tool.getUserPreferences(context,"alarme").equals("1"))
                    Tool.setUserPreferences(context,"alarme", "0");
                else
                    Tool.setUserPreferences(context,"alarme", "1");

                load();
            }
        });
        about_U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, About.class));
                finish();
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


    private void ChangePasseWord(){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_edit_passeword,null);
        final EditText old = convertView.findViewById(R.id.old);
        final EditText newP = convertView.findViewById(R.id.newP);
        final EditText CnewP = convertView.findViewById(R.id.CnewP);
        TextView submit= convertView.findViewById(R.id.submit);
        TextView cancel = convertView.findViewById(R.id.cancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(old.getText().toString())){
                    old.setError("Veuillez renseignez le mot de passe actuél");
                    return;
                }else if (TextUtils.isEmpty(newP.getText().toString())){
                    newP.setError("Veuillez renseignez le nouveau mot de passe");
                    return;
                }else if (TextUtils.isEmpty(CnewP.getText().toString())){
                    CnewP.setError("Veuillez renseignez confirmer le nouveau mot de passe");
                    return;
                }/*else if (!u.getPassword().equals(old.getText().toString())){
                    old.setError("le mot de passe actuel est incorrect");
                }*/else if (!newP.getText().toString().equals(CnewP.getText().toString())){
                    newP.setError("Les mots de passe ne correspondent pas");
                    CnewP.setError("Les mots de passe ne correspondent pas");
                }

                new AsyncTask<Void, Void, Boolean>() {
                    View convertView  = LayoutInflater.from(context).inflate(R.layout.view_progressebar,null);
                    TextView write_response = convertView.findViewById(R.id.write_response);
                    AlertDialog.Builder a = new AlertDialog.Builder(context)
                            .setView(convertView)
                            .setCancelable(false);
                    // Setting dialogview
                    final AlertDialog alert = a.create();

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        write_response.setText("Mise à jour de votre mote de passe encours...");
                        alert.show();
                    }

                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        return ManageLocalData.changePassword(old.getText().toString(), newP.getText().toString());
                    }

                    @Override
                    protected void onPostExecute(Boolean service) {
                        alert.cancel();
                        AlertDialog.Builder a = new AlertDialog.Builder(context)
                                .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        if (service != true ){
                            a.setMessage("Erreur");
                        }else{
                            a.setMessage("Opération réussi");
                        }
                        a.show();
                    }
                }.execute();

            }
        });

        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(false);
        final AlertDialog alert = a.create();
        alert.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });
    }


}
