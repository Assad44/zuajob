package cd.maichapayteam.zuajob.Front_end.Signup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Addresses_screen extends AppCompatActivity {

    Context context = this;
    ImageView btn_back_arrow;
    TextView btn_next;
    EditText street,quartier,commune,about_U,email;


    /**
     * Methode pour la vérification des champs vide
     * @return boolean
     */
    private boolean CheckingZone(){
        if (TextUtils.isEmpty(street.getText().toString())){
            street.setError("Champ obligatoire");
            return false;
        }else if (TextUtils.isEmpty(quartier.getText().toString())){
            quartier.setError("Champ obligatoire");
            return false;
        }else if (TextUtils.isEmpty(commune.getText().toString())){
            commune.setError("Champ obligatoire");
            return false;
        }else return true;
    }

    /**
     * Methode void pour l'initialisation des composants graphique
     */
    private void Init_Components(){
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        about_U = findViewById(R.id.about_U);
        street = findViewById(R.id.street);
        quartier = findViewById(R.id.quartier);
        commune = findViewById(R.id.commune);
        email = findViewById(R.id.email);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresses_screen);
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

                // saving in the preferences
                Tool.setUserPreferences(context,"avenue",street.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"quartier",quartier.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"commune",commune.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"apropos",about_U.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"email",email.getText().toString().replace("'","''"));

                Intent i = new Intent(context, Preferences_screen.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Password_screen.class);
        startActivity(i);
        finish();
    }


}
