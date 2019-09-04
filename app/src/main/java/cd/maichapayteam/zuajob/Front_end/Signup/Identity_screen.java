package cd.maichapayteam.zuajob.Front_end.Signup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;

import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.FilePath;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Identity_screen extends AppCompatActivity {

    Context context = this;
    private static final int PICK_FILE_REQUEST = 12;

    ImageView btn_back_arrow;
    EditText nom, prenom, birthday;
    Spinner genre;
    ImageView Datepicker, picture, Pickpicture;
    TextView btn_next, ImageSrcPath;


    private void Init_Components(){
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        birthday = findViewById(R.id.birthday);
        genre = findViewById(R.id.genre);
        Datepicker = findViewById(R.id.Datepicker);
        picture = findViewById(R.id.picture);
        Pickpicture = findViewById(R.id.Pickpicture);
        ImageSrcPath = findViewById(R.id.ImageSrcPath);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_screen);
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
                // Todo : Checking empty zone
                if (CheckingZone() == false) return;

                // Todo: saving in the preferences
                Tool.setUserPreferences(context,"nom",nom.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"prenom",prenom.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"birthday",birthday.getText().toString().replace("'","''"));
                Tool.setUserPreferences(context,"sexe",genre.getSelectedItem().toString());
                if (!TextUtils.isEmpty(ImageSrcPath.getText().toString())){
                    Tool.setUserPreferences(context,"picture",ImageSrcPath.getText().toString());
                }else Tool.setUserPreferences(context,"picture","default");

                Intent i = new Intent(context, Password_screen.class);
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

        Pickpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPicture();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, PhoneVerif_screen.class);
        startActivity(i);
        finish();
    }


    // TODO : OWN METHODS

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
        }else return true;
    }

    private void pickPicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Choose image"),PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == PICK_FILE_REQUEST){
                if (data != null){
                    Uri selected_Data_Uri = data.getData();
                    String fil = FilePath.getPath(this, selected_Data_Uri);


                    try {
                        FileInputStream fs = new FileInputStream(fil);
                        byte[] imgbyte = new byte[fs.available()];
                        int i = fs.read(imgbyte);

                        // TODO ; Vérification de la taille du fichier
                        /*if (String.valueOf(imgbyte.length).length() >5){
                            Toast.makeText(context, "L'image est trop volumineuse", Toast.LENGTH_LONG).show();
                            return;
                        }*/
                        picture.setImageURI(Uri.parse(fil));
                        ImageSrcPath.setText(fil);

                        /*// TODO ; Conversion en base64
                        String encodedImage = Base64.encodeToString(imgbyte, Base64.DEFAULT);
                        Log.e("ENCODINGGGXXXXXX",encodedImage);
                        String avatar_64 = encodedImage;*/

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("IMAGE_PATH_ERR",e.getMessage());
                    }


                }

            }

        }
    }


}
