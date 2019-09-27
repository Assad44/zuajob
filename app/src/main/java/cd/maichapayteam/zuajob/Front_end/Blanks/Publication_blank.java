package cd.maichapayteam.zuajob.Front_end.Blanks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GenerateData;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Publication_blank extends AppCompatActivity {

    Context context = this;


    Spinner Publication_type,devise,categorie,sous_categorie;
    TextView description,montant,btn_validate;


    ArrayList<Categorie> DATA = new ArrayList<>();
    List<Categorie> DATA1 = new ArrayList<>();
    ArrayList<String> DATA2 = new ArrayList<>();

    ArrayList<String> SCAT = new ArrayList<>();
    ArrayList<String> SCAT_id = new ArrayList<>();
    List<SousCategorie> LSC = new ArrayList<>();

    private void Init_Components(){
        Publication_type = findViewById(R.id.Publication_type);
        devise = findViewById(R.id.deviseAnnonce);
        categorie = findViewById(R.id.categorie);
        sous_categorie = findViewById(R.id.sous_categorie);
        description = findViewById(R.id.description);
        montant = findViewById(R.id.montantAnnonce);
        btn_validate = findViewById(R.id.btn_validate);

        sous_categorie.setVisibility(View.GONE);
    }


    void Load_CAtegorie(){
        Categorie c = new Categorie();
        c.setDesignation("--Selectionner une catégorie-- ");
        DATA1 = ManageLocalData.listCategorie();
        DATA1.add(0, c);
        DATA = (ArrayList<Categorie>) DATA1;

        if (null == DATA) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
        else{
            for (Categorie s : DATA ) {
                DATA2.add(s.getDesignation());
            }
            Tool.setEntries(context,categorie, DATA2);
        }
    }

    private void Load_Header(){
        if (!getIntent().hasExtra("type")) onBackPressed();
        //title = getIntent().getExtras().getString("type");
        getSupportActionBar().setTitle("Nouvelle publication ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);
    }

    private boolean CheckinZones(){
        if (Publication_type.getSelectedItemPosition() == 0){
            Toast.makeText(context, "Veuillez selectionner le type de la publication", Toast.LENGTH_SHORT).show();
            return false;
        }else if (sous_categorie.getSelectedItem().toString().equals("Sous catégorie")){
            Toast.makeText(context, "Veuillez selectionner la catégorie de la publication", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(description.getText().toString())){
            description.setError("Veuillez remplir ce champs");
            return false;
        }else if (TextUtils.isEmpty(montant.getText().toString())){
            montant.setError("Veuillez remplir ce champs");
            return false;
        }else return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_blank);

        Init_Components();
        Load_Header();
        Load_CAtegorie();

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

        categorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    sous_categorie.setVisibility(View.VISIBLE);
                    String identifiant = String.valueOf(DATA.get(position).getId());
                    String designation = String.valueOf(DATA.get(position).getDesignation());
                    LSC = ManageLocalData.listSousCategorie(DATA.get(position));

                    for (SousCategorie s : LSC ) {
                        SCAT.add(s.getDesignation());
                        SCAT_id.add(String.valueOf(s.getId()));
                    }

                    Tool.setEntries(context,sous_categorie, SCAT);


                }else sous_categorie.setVisibility(View.GONE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckinZones() == false) return;
                Toast.makeText(context, "Correct datas", Toast.LENGTH_SHORT).show();

                // Todo : Checking publication type and create object
                if (Publication_type.getSelectedItemPosition() == 1){
                    service_publication();
                }else {
                    Annonce_publication();
                }
            }
        });

    }


    private void service_publication(){
        AsyncTask aaa = new AsyncTask<Void, Void, Service>() {
            Service s = new Service();
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
                write_response.setText("Publication du service encours...");
                s.setIdSousCategorie(Long.parseLong(SCAT_id.get(sous_categorie.getSelectedItemPosition())));
                s.setDescription(description.getText().toString().replace("'","''"));
                s.setDevise(devise.getSelectedItem().toString());
                s.setMontant(Integer.parseInt(montant.getText().toString()));
                alert.show();
            }

            @Override
            protected Service doInBackground(Void... voids) {
                return ManageLocalData.creerService(s);
            }


            @Override
            protected void onPostExecute(Service service) {
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
            }
        }.execute();

    }
    private void Annonce_publication(){
        AsyncTask aaa = new AsyncTask<Void, Void, Annonce>() {
            Annonce s = new Annonce();
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
                write_response.setText("Publication de l'annonce encours...");
                s.setIdSousCategorie(Long.parseLong(SCAT_id.get(sous_categorie.getSelectedItemPosition())));
                s.setDescription(description.getText().toString().replace("'","''"));
                s.setDevise(devise.getSelectedItem().toString());
                s.setMontant(Integer.parseInt(montant.getText().toString()));
                alert.show();
            }

            @Override
            protected Annonce doInBackground(Void... voids) {
                return ManageLocalData.creerAnnonce(s);
            }

            @Override
            protected void onPostExecute(Annonce annonce) {
                alert.cancel();
                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                if (annonce.isError() == true ){
                    a.setMessage(annonce.getErrorMessage()+ " "+annonce.getErrorCode());
                }else{
                    a.setMessage("Opération réussi");
                }
                a.show();
            }
        }.execute();


    }



    @Override
    public void onBackPressed() {
        Intent i;
        i = new Intent(context, Home.class);
        startActivity(i);
        finish();
    }

}
