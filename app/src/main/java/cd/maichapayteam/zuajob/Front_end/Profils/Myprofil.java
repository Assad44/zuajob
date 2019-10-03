package cd.maichapayteam.zuajob.Front_end.Profils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;

import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Paramettres;
import cd.maichapayteam.zuajob.Front_end.Signup.index_screen;
import cd.maichapayteam.zuajob.Home;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.FilePath;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.RoundedImageView;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Myprofil extends AppCompatActivity {

    Context context = this;
    private static final int PICK_FILE_REQUEST = 12;
    TextView update_Adresses,details;
    RoundedImageView picture;
    ImageView Pickpicture;
    TextView nom,number,Sexe;
    TextView type_compte,quartier,commune,pays,email,typeswitcher;

    Toolbar toolbar;

    private void Init_Components(){
        update_Adresses = findViewById(R.id.update_Adresses);
        picture = findViewById(R.id.picture);
        Pickpicture = findViewById(R.id.Pickpicture);
        nom = findViewById(R.id.nom);
        number = findViewById(R.id.number);
        details = findViewById(R.id.details);
        Sexe = findViewById(R.id.Sexe);
        toolbar = findViewById(R.id.toolbar);
        type_compte = findViewById(R.id.type_compte);
        /*pays = findViewById(R.id.pays);
        street = findViewById(R.id.street);
        quartier = findViewById(R.id.quartier);
        commune = findViewById(R.id.commune);
        email = findViewById(R.id.email);*/
        typeswitcher = findViewById(R.id.typeswitcher);
    }


    private void Profil_initialize(){

        User u = GeneralClass.Currentuser;

        nom.setText(
                u.getNom() + " "+u.getPrenom()
        );
        number.setText("+"+ u.getPhone());
        Sexe.setText(
                u.getSexe()+ " / "+ u.getBirthday()
        );

        if (u.getDescription().equals("")) details.setText("Aucun détails sur votre profil ");
        else details.setText(u.getDescription());

        if (u.getType() == 0) type_compte.setText("Demandeur ou chercheur de service");
        else type_compte.setText("Prestataire ou offreur des services");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofil);
        // Initialisation des composants
        Init_Components();

        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            //getSupportActionBar().setIcon(R.drawable.ic_back_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Profil_initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Pickpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPicture();
            }
        });
        typeswitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tool.getUserPreferences(context,"type").equals("1"))
                    Tool.setUserPreferences(context,"type", "0");
                else
                    Tool.setUserPreferences(context,"type", "1");
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Home.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.publication) {
            Intent i = new Intent(context, Publication_blank.class);
            i.putExtra("type", "other");
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.share) {
            Tool.SHARE(context,getResources().getString(R.string.Share_message));
            return true;
        }
        if (id == R.id.action_settings) {
            Intent i = new Intent(context, Paramettres.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.nav_exit) {
            exit_alert();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    void exit_alert(){
        TextView sortie;
        TextView cancel;
        View view  = LayoutInflater.from(context).inflate(R.layout.view_exit,null);
        sortie     = view.findViewById(R.id.sortie);
        cancel     = view.findViewById(R.id.cancel);
        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false);
        final AlertDialog alert = a.create();
        alert.show();

        sortie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageLocalData.deconnection();
                GeneralClass.Currentuser = null;
                startActivity(new Intent(context, index_screen.class));
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });
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
                        //ImageSrcPath.setText(fil);

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
