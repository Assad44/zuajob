package cd.maichapayteam.zuajob.Front_end.Signup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Identity_screen extends AppCompatActivity {

    ProgressDialog progressDialog;

    Context context = this;
    private static final int PICK_FILE_REQUEST = 12;

    TextView btn_back_arrow;
    EditText nom, prenom, birthday;
    Spinner genre,type;
    EditText passe,Cpasse;
    ImageView Datepicker;
    TextView btn_next;


    private void Init_Components(){
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        nom = findViewById(R.id.nom);
        type = findViewById(R.id.type);
        passe = findViewById(R.id.passe);
        Cpasse = findViewById(R.id.Cpasse);
        prenom = findViewById(R.id.prenom);
        birthday = findViewById(R.id.birthday);
        genre = findViewById(R.id.genre);
        Datepicker = findViewById(R.id.Datepicker);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // Initialisation des composants
        Init_Components();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checking empty zone
                if (CheckingZone() == false) return;

                if (!passe.getText().toString().equals(Cpasse.getText().toString())){
                    Toast.makeText(context, "Les mots de passe ne concordent pas", Toast.LENGTH_SHORT).show();
                    return;
                }

                int t = 0;
                if (type.getSelectedItemPosition() == 1)
                    t = 1;
                else if (type.getSelectedItemPosition() == 2)
                    t = 0;

                // saving in the preferences
                Tool.setUserPreferences(context,"nom",nom.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"prenom",prenom.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"birthday",birthday.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"sexe",genre.getSelectedItem().toString());
                Tool.setUserPreferences(context,"passe",passe.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"type", String.valueOf(t));
                Tool.setUserPreferences(context,"statut", "done");
                Tool.setUserPreferences(context,"Firstuse","non");

                Intent i = new Intent(context, Home.class);
                startActivity(i);
                finish();

            }
        });
        Datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.Date_Picker(context, birthday);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, PhoneVerif_screen.class);
        startActivity(i);
        finish();
    }


    class InscriptionAsync extends AsyncTask<String, String, User> {

        User user;

        public InscriptionAsync(User user) {
            this.user = user;
        }

        @Override
        protected void onPreExecute() {
            //TODO : show a load dialog here
            progressDialog = new ProgressDialog(Identity_screen.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Inscription");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected User doInBackground(String... strings) {
            progressDialog.setMessage("Votre inscription est encours. Veuillez patienter SVP.");
            //return RemoteDataSync.confirmCode(numero, code);
            return ManageLocalData.createUser(user);
        }

        @Override
        protected void onPostExecute(User result) {
            progressDialog.dismiss();
            //TODO : dismiss a load dialog here
            if(result!=null) {

                Intent i = new Intent(context, Home.class);
                startActivity(i);
                finish();
            } else {
                /*TODO An orror are occured when signin, report to the user */
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Identity_screen.this);
                alertDialog.setTitle("Incription");
                alertDialog.setMessage(result.errorMessage);
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {

                    }
                });
                alertDialog.show();
            }

            super.onPostExecute(result);
        }
    }

    // OWN METHODS

    private boolean CheckingZone(){
        //TODO l'utilisateur doit séléctionner le type
        if (TextUtils.isEmpty(nom.getText().toString())){
            nom.setError("Champ obligatoire");
            return false;
        }else if (TextUtils.isEmpty(prenom.getText().toString())){
            prenom.setError("Champ obligatoire");
            return false;
        }else if (TextUtils.isEmpty(birthday.getText().toString())){
            birthday.setError("Champ obligatoire");
            return false;
        }else if (TextUtils.isEmpty(passe.getText().toString())){
            passe.setError("Champ obligatoire");
            return false;
        }else if (TextUtils.isEmpty(Cpasse.getText().toString())){
            Cpasse.setError("Champ obligatoire");
            return false;
        }else return true;
    }


}
