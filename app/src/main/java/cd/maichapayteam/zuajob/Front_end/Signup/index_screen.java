package cd.maichapayteam.zuajob.Front_end.Signup;

import android.Manifest;
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

import com.activeandroid.ActiveAndroid;

import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.Front_end.Home;
import cd.maichapayteam.zuajob.Front_end.Login;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.Models.Object.Users;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.RemoteDataSync;
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

        // Todo ; Initialisation des composants
        Init_Components();

        //TODO check if user exist, pass to home directly
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

        if (null != Users.getall(context)){
            Toast.makeText(context, "Profil determiné", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, Home.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(context, "Aucun Profil trouvé", Toast.LENGTH_SHORT).show();
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

    }

    private void CheckPermission() {
        if (
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.MEDIA_CONTENT_CONTROL) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.MEDIA_CONTENT_CONTROL
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

}
