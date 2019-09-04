package cd.maichapayteam.zuajob.Front_end.Signup;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Password_screen extends AppCompatActivity {


    Context context = this;
    EditText passe,Cpasse;
    TextView btn_next;
    ImageView btn_back_arrow;

    private void Init_Components(){
        passe = findViewById(R.id.passe);
        Cpasse = findViewById(R.id.Cpasse);
        btn_next = findViewById(R.id.btn_next);
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // Initialisation des composants
        Init_Components();
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

                // Verifying passwords
                if (!passe.getText().toString().equals(Cpasse.getText().toString())){
                    Snackbar.make(v, "Les mots de passe ne concordent pas", Snackbar.LENGTH_LONG)
                            .setAction("Fermer", null).show();
                    //Toast.makeText(context, "Les mots de passe ne concordent pas", Toast.LENGTH_SHORT).show();
                    return;
                }
                // saving in the preferences
                Tool.setUserPreferences(context,"passe",passe.getText().toString().replace("'","''"));

                Intent i;
                if (Tool.getUserPreferences(context, "type_user").equals("1"))
                    i = new Intent(context, Addresses_screen.class);
                else
                    i = new Intent(context, Preferences_screen.class);

                startActivity(i);
                finish();

            }
        });


    }

    private boolean CheckingZone(){
        if (TextUtils.isEmpty(passe.getText().toString())){
            passe.setError("Champ obligatoire");
            return false;
        }else if (TextUtils.isEmpty(Cpasse.getText().toString())){
            Cpasse.setError("Champ obligatoire");
            return false;
        }else return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Identity_screen.class);
        startActivity(i);
        finish();
    }


}
