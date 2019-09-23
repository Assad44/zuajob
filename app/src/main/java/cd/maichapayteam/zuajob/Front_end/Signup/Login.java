package cd.maichapayteam.zuajob.Front_end.Signup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;

public class Login extends AppCompatActivity {

    ProgressDialog progressDialog;

    Context context = this;
    int exit = 0;
    TextView se_connecter,BTNsignin;
    EditText passe,phone;

    String number = "";
    String password = "";

    private void Init_Components(){
        se_connecter = findViewById(R.id.se_connecter);
        phone = findViewById(R.id.phone);
        passe = findViewById(R.id.passe);
        BTNsignin = findViewById(R.id.BTNsignin);
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

        BTNsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PhoneVerif_screen.class);
                startActivity(i);
                finish();
            }
        });

        se_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Todo : Checking empty zone
                if (CheckingZone() == false) return;


                number = "243" + phone.getText().toString();
                password = passe.getText().toString();

                LoginAsync loginAsync = new LoginAsync();
                loginAsync.execute();

                /*TestAsync testAsync = new TestAsync();
                testAsync.execute();*/

                //Intent i = new Intent(context, Home.class);
                //startActivity(i);
                //finish();
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

    public void Snack_log(String subtitle, int color){
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), subtitle, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        view.setBackgroundColor(color);
        snack.show();
    }
    public void Toast_log(Context context, String title, String subtitle, int color){
        View view = LayoutInflater.from(context).inflate(R.layout.view_toast_info, null);
        LinearLayout back = view.findViewById(R.id.back);
        TextView titre = view.findViewById(R.id.titre);
        TextView sous_titre = view.findViewById(R.id.sous);
        titre.setText(title);
        sous_titre.setText(subtitle);
        back.setBackgroundColor(getResources().getColor(color));
        Toast toast = Toast.makeText(context,"",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0,0);
        toast.setView(view);
        toast.show();
    }

    class LoginAsync extends AsyncTask<String, String, User> {
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_progressebar,null);
        TextView write_response = convertView.findViewById(R.id.write_response);
        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView);
        // Setting dialogview
        final AlertDialog alert = a.create();


        @Override
        protected void onPreExecute() {
            //TODO : show a load dialog here
            write_response.setText("Connexion encours...");

            Window window = alert.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
            alert.show();
            super.onPreExecute();
        }

        @Override
        protected User doInBackground(String... strings) {
            //return RemoteDataSync.confirmCode(numero, code);
            return ManageLocalData.login(number, password);
            //return null;
        }

        @Override
        protected void onPostExecute(User result) {
            alert.cancel();
            //TODO : dismiss a load dialog here
            if(result.isError() == false) {
                GeneralClass.Currentuser = result;
                Snack_log("Authentification reussi", context.getResources().getColor(R.color.colorAccent));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(context, Home.class);
                        startActivity(i);
                        finish();
                    }
                }, 1000);
            } else {
                /*TODO Incorrect password or phone number, report to the user */
                Snack_log("Authentification erronées", context.getResources().getColor(R.color.red));
                //Toast_log(context, "Error","Authentification erronées", R.color.red);
            }

            super.onPostExecute(result);
        }
    }

    class TestAsync extends AsyncTask<String, String, Boolean> {

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Connexion");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            progressDialog.setMessage("Connexion encours...");
            //return RemoteDataSync.confirmCode(numero, code);
            return ManageLocalData.changePassword("0000", password);
            //return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressDialog.dismiss();
            if(result) {
                Log.e("updatePassword", "update successfuly");
            } else {
                Log.e("updatePassword", "update failed");
            }

            super.onPostExecute(result);
        }
    }

}
