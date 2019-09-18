package cd.maichapayteam.zuajob.Front_end.Signup;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Main;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.Tool;

public class index_screen extends AppCompatActivity {

    Context context = this;
    private static final int PERM_CALL_ID = 1;

    TextView btn_se_connecter, btn_sinscrire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_screen2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // Todo : According Permissions
        CheckPermission();

        //TODO check if user exist, pass to home directly
        /*if (!Tool.User_Preferences(context).getString("Firstuse", "null").equals("null")){
            if (!Tool.User_Preferences(context).getString("statut", "null").equals("null")){
                // Todo : Launche Home activity
                Intent i = new Intent(context, Home.class);
                startActivity(i);
                finish();
            }
        }*/

        if (null != GeneralClass.Currentuser){
            Intent i = new Intent(context, Home.class);
            startActivity(i);
            finish();
        }

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
        btn_sinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PhoneVerif_screen.class);
                startActivity(i);
                finish();
            }
        });
        btn_se_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Login.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void Init_Components() {
        btn_sinscrire = findViewById(R.id.btn_sinscrire);
        btn_se_connecter = findViewById(R.id.btn_se_connecter);

        if (!Tool.getUserPreferences(context, "FisrtUse").equals("")){
            Intent i = new Intent(context, Home.class);
            startActivity(i);
            finish();
        }

        /*if (Tool.getUserPreferences(context,"phone").equals("")){
            Toast.makeText(context, "Profil determiné", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, Home.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(context, "Aucun Profil trouvé", Toast.LENGTH_SHORT).show();
        }*/
/*
        User myProfil = User.myProfile();

        if(myProfil!=null) {
            Intent i = new Intent(context, Home.class);
            startActivity(i);
            finish();
        }else{
            Intent i = new Intent(context, Details_publication.class);
            startActivity(i);
            finish();
        }*/


        //TestAsync testAsync = new TestAsync();
        //testAsync.execute();


    }

    private void CheckPermission() {
        if (
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.BROADCAST_SMS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.MEDIA_CONTENT_CONTROL,
                    Manifest.permission.BROADCAST_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS
            }, PERM_CALL_ID);
            return;
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERM_CALL_ID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private class TestAsync extends AsyncTask<String, String, User> {

        @Override
        protected User doInBackground(String... strings) {
            //RemoteDataSync.getRandomUser(100);

            return UserDAO.getInstance(GeneralClass.applicationContext).find(53);

            //return null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            AlertDialog.Builder adb = new AlertDialog.Builder(index_screen.this);

            StringBuilder stringBuilder = new StringBuilder();
            if(user!=null) {
                stringBuilder.append("Id: ");
                stringBuilder.append(user.getId());
                stringBuilder.append("\n");
                stringBuilder.append("Prenom: ");
                stringBuilder.append(user.getPrenom());
                stringBuilder.append("\n");
                stringBuilder.append("Nom: ");
                stringBuilder.append(user.getNom());
                stringBuilder.append("\n");
                stringBuilder.append("Password: ");
                stringBuilder.append(user.getPassword());
                stringBuilder.append("\n");
                stringBuilder.append("Code pays: ");
                stringBuilder.append(user.getCodePays());
                stringBuilder.append("\n");
                stringBuilder.append("About: ");
                stringBuilder.append(user.getAbout());
                stringBuilder.append("\n");
                stringBuilder.append("Adresse: ");
                stringBuilder.append(user.getAdresse());
                stringBuilder.append("\n");
                stringBuilder.append("Auth code: ");
                stringBuilder.append(user.getAuthCode());
                stringBuilder.append("\n");
                stringBuilder.append("Birthday: ");
                stringBuilder.append(user.getBirthday());
                stringBuilder.append("\n");
                stringBuilder.append("Commune: ");
                stringBuilder.append(user.getCommune());
                stringBuilder.append("\n");
                stringBuilder.append("Email: ");
                stringBuilder.append(user.getEmail());
                stringBuilder.append("\n");
                stringBuilder.append("Num identité: ");
                stringBuilder.append(user.getNumIdentite());
                stringBuilder.append("\n");
                stringBuilder.append("Phone: ");
                stringBuilder.append(user.getPhone());
                stringBuilder.append("\n");
                stringBuilder.append("Quartier: ");
                stringBuilder.append(user.getQuartier());
                stringBuilder.append("\n");
                stringBuilder.append("Sexe: ");
                stringBuilder.append(user.getSexe());
                stringBuilder.append("\n");
                stringBuilder.append("Type: ");
                stringBuilder.append(user.getType());
                stringBuilder.append("\n");
                stringBuilder.append("Type identité: ");
                stringBuilder.append(user.getTypeIdentite());
                stringBuilder.append("\n");
                stringBuilder.append("Url: ");
                stringBuilder.append(user.getUrlPhoto());
                stringBuilder.append("\n");
                stringBuilder.append("Ville: ");
                stringBuilder.append(user.getVille());
                stringBuilder.append("\n");
            } else {
                stringBuilder.append("Le user est null");
            }

            adb.setMessage(stringBuilder);

            adb.show();

            Toast.makeText(index_screen.this, "Liste des users size : " + UserDAO.getInstance(GeneralClass.applicationContext).count(), Toast.LENGTH_LONG).show();

        }
    }

}
