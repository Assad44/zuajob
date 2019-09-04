package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.R;

public class Login extends AppCompatActivity {

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


                Intent i = new Intent(context, Home.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit == 0){
            Toast.makeText(context, "Appuyer encore pour quitter", Toast.LENGTH_SHORT).show();
            exit = 1;
        }else finish();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = 0;
            }
        }, 2000);
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

}
