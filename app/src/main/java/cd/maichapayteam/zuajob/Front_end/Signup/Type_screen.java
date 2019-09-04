package cd.maichapayteam.zuajob.Front_end.Signup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Type_screen extends AppCompatActivity {

    Context context = this;
    TextView btn_jobeur,btn_simple_user;
    ImageView btn_back_arrow;

    private void Init_Components(){
        btn_jobeur = findViewById(R.id.btn_jobeur);
        btn_simple_user = findViewById(R.id.btn_simple_user);
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_screen);
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
        btn_jobeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.setUserPreferences(context, "type_user", "1");
                Intent i = new Intent(context, PhoneVerif_screen.class);
                startActivity(i);
                finish();
            }
        });
        btn_simple_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.setUserPreferences(context, "type_user", "0");
                Intent i = new Intent(context, PhoneVerif_screen.class);
                startActivity(i);
                finish();
            }
        });

        btn_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, index_screen.class);
        startActivity(i);
        finish();
    }


}
