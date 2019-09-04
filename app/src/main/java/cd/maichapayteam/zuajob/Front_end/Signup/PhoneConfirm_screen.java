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

public class PhoneConfirm_screen extends AppCompatActivity {

    Context context = this;
    ImageView btn_back_arrow;
    EditText PhoneCodeNumber;
    TextView btn_next,advice;


    private void Init_Components(){
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        PhoneCodeNumber = findViewById(R.id.PhoneCodeNumber);
        advice = findViewById(R.id.advice);

        String num = Tool.getUserPreferences(context, "phone");
        String code = Tool.getUserPreferences(context, "CountryCode");
        String advices = "Nous avons envoyer un SMS à votre numéro : \n"+ code +" "+num + "\nContenant le code de confirmation\n" +
                "Cela peu prendre un instant un petit instant";

        advice.setText(advices);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_confirm_screen);
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
                if (TextUtils.isEmpty(PhoneCodeNumber.getText().toString())){
                    PhoneCodeNumber.setError("Veuillez entrer le code de confirmation");
                    return;
                }

                // Todo : verify the code
                String code = PhoneCodeNumber.getText().toString();

                // Todo: saving in the preferences
                Tool.setUserPreferences(context,"phoneCode",PhoneCodeNumber.getText().toString());

                // Todo : goto next activity
                Intent i = new Intent(context, Identity_screen.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, PhoneVerif_screen.class);
        startActivity(i);
        finish();
    }



}
