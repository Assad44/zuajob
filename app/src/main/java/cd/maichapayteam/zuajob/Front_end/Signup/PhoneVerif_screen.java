package cd.maichapayteam.zuajob.Front_end.Signup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import cd.maichapayteam.zuajob.Tools.GenerateData;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.RemoteDataSync;
import cd.maichapayteam.zuajob.Tools.Tool;

public class PhoneVerif_screen extends AppCompatActivity {

    //ProgressDialog progressDialog;
    Context context = this;
    ImageView btn_back_arrow;
    CountryCodePicker contryCode;
    EditText PhoneNumber;
    TextView btn_next;
    ProgressBar progressbar;

    String numero = "";
    String codeCountry = "" ;
    String countryName = "";

    private void Init_Components(){
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        contryCode = findViewById(R.id.contryCode);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.GONE);
        contryCode.setCountryForNameCode("CD");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verif_screen);
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

                // Todo : Checking Empty field
                if (TextUtils.isEmpty(PhoneNumber.getText().toString())){
                    PhoneNumber.setError("Veuillez entrer votre numéro de téléphone");
                    return;
                }else if (PhoneNumber.getText().toString().length() < 9){
                    PhoneNumber.setError("Veuillez entrer un numéro de téléphone correct");
                    return;
                }

                numero = PhoneNumber.getText().toString();
                codeCountry = contryCode.getSelectedCountryCodeWithPlus();
                countryName = contryCode.getSelectedCountryName();

                /*String phone  = codeCountry+numero;
                Auth(phone);*/

                //Tool.setUserPreferences(context,"phone",numero);
                //Tool.setUserPreferences(context,"CountryCode",codeCountry);
                //Tool.setUserPreferences(context,"CountryName",countryName);
                //Intent i = new Intent(context, PhoneConfirm_screen.class);
                //startActivity(i);
                //RemoteDataSync.sendSMS(codeCountry+numero);
                //finish();
                CheckingNumberAsync checkingNumberAsync = new CheckingNumberAsync();
                checkingNumberAsync.execute();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, index_screen.class);
        startActivity(i);
        finish();
    }

    class CheckingNumberAsync extends AsyncTask<String, String, Boolean> {

        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_progressebar,null);
        TextView write_response = convertView.findViewById(R.id.write_response);
        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView);
        // Setting dialogview
        final AlertDialog alert = a.create();

        @Override
        protected void onPreExecute() {
            //TODO : show a load dialog here
            write_response.setText("Vérification du numéro de téléphone...");

            Window window = alert.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
            alert.show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            //progressDialog.setMessage("Vérification de votre numéro de téléphone en cours...");
            //return RemoteDataSync.checkNumero(codeCountry+numero);
            //return GenerateData.checkNumero(numero);
            return ManageLocalData.checkNumero(codeCountry+numero);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //TODO : dismiss a load dialog here
            alert.cancel();
            if(!aBoolean) {
                Tool.setUserPreferences(context,"phone",numero);
                Tool.setUserPreferences(context,"CountryCode",codeCountry);
                Tool.setUserPreferences(context,"CountryName",countryName);
                Intent i = new Intent(context, PhoneConfirm_screen.class);
                startActivity(i);
                finish();
            } else {
                /*TODO : show a dialog "This phone number already exists. Do you want to connect?"
                    If he wants to connect we open the connection activity, otherwise we leave the application */
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PhoneVerif_screen.this);
                alertDialog.setTitle("Numéro existe");
                alertDialog.setMessage("Ce numéro existe déjà dans notre base de donnée. Si ce numéro vous appartient, vous devez plutôt vous connecter. Voullez-vous vous connecter ?");
                alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {

                        Intent i = new Intent(context, Login.class);
                        startActivity(i);
                        finish();
                    }
                });
                alertDialog.show();
            }

            super.onPostExecute(aBoolean);
        }
    }


}
