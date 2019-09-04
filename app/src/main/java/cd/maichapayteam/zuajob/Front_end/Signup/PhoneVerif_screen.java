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

import com.hbb20.CountryCodePicker;

import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

public class PhoneVerif_screen extends AppCompatActivity {

    Context context = this;
    ImageView btn_back_arrow;
    CountryCodePicker contryCode;
    EditText PhoneNumber;
    TextView btn_next;

    private void Init_Components(){
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        contryCode = findViewById(R.id.contryCode);
        contryCode.setCountryForNameCode("CD");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verif_screen);
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
                if (TextUtils.isEmpty(PhoneNumber.getText().toString())){
                    PhoneNumber.setError("Veuillez entrer votre numéro de téléphone");
                    return;
                }else if (PhoneNumber.getText().toString().length() < 9){
                    PhoneNumber.setError("Veuillez entrer un numéro de téléphone correct");
                    return;
                }

                // Todo: saving in the preferences
                Tool.setUserPreferences(context,"phone",PhoneNumber.getText().toString());
                Tool.setUserPreferences(context,"CountryCode",contryCode.getSelectedCountryCodeWithPlus());
                Tool.setUserPreferences(context,"CountryName",contryCode.getSelectedCountryName());
                Intent i = new Intent(context, PhoneConfirm_screen.class);
                startActivity(i);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Type_screen.class);
        startActivity(i);
        finish();
    }


}
