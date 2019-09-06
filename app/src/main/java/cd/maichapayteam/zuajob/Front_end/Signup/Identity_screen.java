package cd.maichapayteam.zuajob.Front_end.Signup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;

import cd.maichapayteam.zuajob.Front_end.Home;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.FilePath;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Identity_screen extends AppCompatActivity {

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
                // saving in the preferences
                Tool.setUserPreferences(context,"nom",nom.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"prenom",prenom.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"birthday",birthday.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"sexe",genre.getSelectedItem().toString());
                // saving in the preferences
                Tool.setUserPreferences(context,"passe",passe.getText().toString().replace("'","''"));
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


    // OWN METHODS

    private boolean CheckingZone(){
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
