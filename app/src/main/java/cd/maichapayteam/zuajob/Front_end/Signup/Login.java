package cd.maichapayteam.zuajob.Front_end.Signup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;

public class Login extends AppCompatActivity {

    ProgressDialog progressDialog;

    Context context = this;
    int exit = 0;
    TextView se_connecter;
    EditText passe,phone;

    private void Init_Components(){
        se_connecter = findViewById(R.id.se_connecter);
        phone = findViewById(R.id.phone);
        passe = findViewById(R.id.passe);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // Todo ; Initialisation des composants
        Init_Components();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        se_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Todo : Checking empty zone
                if (CheckingZone() == false) return;


                /*LoginAsync loginAsync = new LoginAsync();
                loginAsync.execute();*/
                Intent i = new Intent(context, Home.class);
                startActivity(i);
                finish();
                //Toast.makeText(Login.this, User.findByPhoneNumer("897175763").password, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(context, Home.class);
        startActivity(i);
        finish();

        /*if (exit == 0){
            Toast.makeText(context, "Appuyer encore pour quitter", Toast.LENGTH_SHORT).show();
            exit = 1;
        }else finish();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = 0;
            }
        }, 2000);*/
    }

    private boolean CheckingZone(){
        if (TextUtils.isEmpty(phone.getText().toString())){
            phone.setError("Champ obligatoire");
            return false;
        }else if (TextUtils.isEmpty(passe.getText().toString())){
            passe.setError("Champ obligatoire");
            return false;
        }else return true;
    }

    class LoginAsync extends AsyncTask<String, String, User> {

        @Override
        protected void onPreExecute() {
            //TODO : show a load dialog here
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Connexion");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected User doInBackground(String... strings) {
            progressDialog.setMessage("Connexion encours...");
            //return RemoteDataSync.confirmCode(numero, code);
            //ManageLocalData.login(phone.getText().toString(), passe.getText().toString());
            return null;
        }

        @Override
        protected void onPostExecute(User result) {
            progressDialog.dismiss();
            //TODO : dismiss a load dialog here
            if(!result.error) {
                Intent i = new Intent(context, Home.class);
                startActivity(i);
                finish();
            } else {
                /*TODO Incorrect password or phone number, report to the user */
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                alertDialog.setTitle("Incription");
                alertDialog.setMessage("Le numéro de téléphone et le mot de passe fourni ne correspondent pas. Veuillez vérifier et réessayer SVP.");
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

}
