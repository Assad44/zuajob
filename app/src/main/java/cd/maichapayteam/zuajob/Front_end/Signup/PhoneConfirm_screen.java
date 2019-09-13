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
import android.widget.TextView;

import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.IncomingSms;
import cd.maichapayteam.zuajob.Tools.Tool;

public class PhoneConfirm_screen extends AppCompatActivity implements IncomingSms.ZuaJobMessageListener {

    ProgressDialog progressDialog;

    Context context = this;
    ImageView btn_back_arrow;
    EditText PhoneCodeNumber;
    TextView btn_next,advice;

    String code = "";
    String numero = "";


    private void Init_Components(){
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        PhoneCodeNumber = findViewById(R.id.PhoneCodeNumber);
        advice = findViewById(R.id.advice);

        //TODO : Ajouter un lien si le message n'est pas envoyé que l'utilisateur puisse demander un autre message

        numero = Tool.getUserPreferences(context, "CountryCode") +" "+ Tool.getUserPreferences(context, "phone");

        IncomingSms incomingSms = new IncomingSms(this, this, code+numero);

        String advices = "Nous avons envoyé un SMS à votre numéro : \n"+ numero + "\nContenant le code de confirmation\n" +
                "Si la détection automatique ne fonctionne pas, saisissez manuellement le code réçu";
        advice.setText(advices);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_confirm_screen);
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
                // Checking Empty field
                if (TextUtils.isEmpty(PhoneCodeNumber.getText().toString())){
                    PhoneCodeNumber.setError("Veuillez entrer le code de confirmation");
                    return;
                }

                // verify the code
                code = PhoneCodeNumber.getText().toString();

                /*Tool.setUserPreferences(context,"phoneCode",PhoneCodeNumber.getText().toString());
                // goto next activity
                Intent i = new Intent(context, Identity_screen.class);
                startActivity(i);
                finish();*/
                CheckingCodeAsync checkingCodeAsync = new CheckingCodeAsync(PhoneCodeNumber.getText().toString());
                checkingCodeAsync.execute();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, PhoneVerif_screen.class);
        startActivity(i);
        finish();
    }


    @Override
    public void OnCorrectConfirmationCode() {
        passToNextActivity();
    }

    @Override
    public void OnIncorrectConfirmationCode() {
        showIncorrectConfirmationCode();
    }

    @Override
    public void OnNewMessage(String message) {

    }

    class CheckingCodeAsync extends AsyncTask<String, String, Boolean> {

        String code;

        public CheckingCodeAsync(String code) {
            this.code = code;
        }

        @Override
        protected void onPreExecute() {
            //TODO : show a load dialog here
            progressDialog = new ProgressDialog(PhoneConfirm_screen.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Confirmation du code");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            progressDialog.setMessage("La confirmation du code saisi est encours...");
            //return RemoteDataSync.confirmCode(numero, code);
            return code.equals("123456");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressDialog.dismiss();
            //TODO : dismiss a load dialog here
            if(aBoolean) {
                passToNextActivity();
            } else {
                showIncorrectConfirmationCode();
            }

            super.onPostExecute(aBoolean);
        }
    }

    void passToNextActivity() {
        // saving in the preferences
        Tool.setUserPreferences(context,"phoneCode",PhoneCodeNumber.getText().toString());
        // goto next activity
        Intent i = new Intent(context, Identity_screen.class);
        startActivity(i);
        finish();
    }

    void showIncorrectConfirmationCode() {
        /*TODO Confirmation code is incorrect, report to the user */
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PhoneConfirm_screen.this);
        alertDialog.setTitle("Code incorrect");
        alertDialog.setMessage("Le code de confirmation saisi est incorrect.");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {

            }
        });
        alertDialog.show();
    }

}
