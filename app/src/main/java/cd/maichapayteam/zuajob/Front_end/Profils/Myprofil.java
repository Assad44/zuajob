package cd.maichapayteam.zuajob.Front_end.Profils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.bitmap.Transform;
import com.koushikdutta.ion.builder.AnimateGifMode;

import java.io.FileInputStream;
import java.io.IOException;

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
import pl.droidsonroids.gif.GifDrawable;

public class Myprofil extends AppCompatActivity {

    Context context = this;
    private static final int PICK_FILE_REQUEST = 12;
    TextView update_Adresses,details;
    ImageView picture;
    CardView evolution,details_user;
    ImageView Pickpicture;
    TextView nom,number,Sexe,nbr_real;
    RatingBar rating;
    TextView type_compte,address,typeswitcher;
    ProgressBar progressbar;

    Toolbar toolbar;

    private void Init_Components(){
        update_Adresses = findViewById(R.id.update_Adresses);
        picture = findViewById(R.id.picture);
        rating = findViewById(R.id.rating);
        progressbar = findViewById(R.id.progressbar);
        Pickpicture = findViewById(R.id.Pickpicture);
        nom = findViewById(R.id.nom);
        number = findViewById(R.id.number);
        details = findViewById(R.id.details);
        Sexe = findViewById(R.id.Sexe);
        nbr_real = findViewById(R.id.nbr_real);
        toolbar = findViewById(R.id.toolbar);
        type_compte = findViewById(R.id.type_compte);
        evolution = findViewById(R.id.evolution);
        details_user = findViewById(R.id.details_user);
        address = findViewById(R.id.address);
        typeswitcher = findViewById(R.id.typeswitcher);

        progressbar.setVisibility(View.GONE);
    }

    private void Profil_initialize(){

        User u = GeneralClass.Currentuser;

        nom.setText(
                u.getNom() + " "+u.getPrenom()
        );

        try {
            GifDrawable gifFromResource = new GifDrawable( context.getResources(), R.drawable.gif5);
            Ion.with(picture)
                    .placeholder(gifFromResource)
                    .error(R.drawable.avatar_error)
                    .animateGif(AnimateGifMode.ANIMATE)
                    .load(u.getUrlThumbnail());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e("IMAGE_PROFIL", u.getUrlThumbnail());
        Toast.makeText(context, u.getUrlThumbnail(), Toast.LENGTH_SHORT).show();

        number.setText("+"+ u.getPhone());
        Sexe.setText(
                u.getSexe()+ " / "+ u.getBirthday()
        );

        if (u.getType() == 0) {
            type_compte.setText("Demandeur ou chercheur de service");
            evolution.setVisibility(View.GONE);
            details_user.setVisibility(View.GONE);
        }else {
            type_compte.setText("Prestataire ou offreur des services");
            evolution.setVisibility(View.VISIBLE);
            details_user.setVisibility(View.VISIBLE);
            rating.setRating(u.getCote());
            nbr_real.setText(String.valueOf(u.getNombreRealisation()));
            details.setText(u.getDescription());
            if (u.getAdresse().equals("") && u.getCommune().equals("") && u.getQuartier().equals("")){
                address.setText("Aucune adresse sur vous");
            }else{
                address.setText(
                        "Ave./ "+u.getAdresse()+
                                " Q/ "+u.getQuartier()+
                                " C/ "+u.getCommune()
                );
            }
        }

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
            getSupportActionBar().setTitle("Mon profil");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            //getSupportActionBar().setIcon(R.drawable.ic_back_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Profil_initialize();
    }

    @Override
    protected void onRestart() {
        Profil_initialize();
        super.onRestart();
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
                        //picture.setImageURI(Uri.parse(fil));
                        //ImageSrcPath.setText(fil);


                        // TODO ; Conversion en base64
                        String encodedImage = Base64.encodeToString(imgbyte, Base64.DEFAULT);
                        Log.e("ENCODINGGGXXXXXX",encodedImage);
                        upload_image(encodedImage, picture);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("IMAGE_PATH_ERR",e.getMessage());
                    }


                }

            }

        }
    }

    private void upload_image(final String img, final ImageView picture){
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar.setVisibility(View.VISIBLE);
                try {
                    GifDrawable gifFromResource = new GifDrawable( context.getResources(), R.drawable.gif5);
                    picture.setImageDrawable(gifFromResource);
                } catch (IOException e) {
                    picture.setImageResource(R.drawable.gif5);
                    e.printStackTrace();
                }
            }

            @Override
            protected Boolean doInBackground(Void... voids) {
                return ManageLocalData.uploadImage(img,"jpg");
            }

            @Override
            protected void onPostExecute(Boolean o) {
                progressbar.setVisibility(View.GONE);
                if (o!= true ){
                    Toast.makeText(context, "Echec de la mise à jour du profil", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Le profil a été mis à jour", Toast.LENGTH_SHORT).show();
                    //Profil_initialize();
                }

            }
        }.execute();
    }

}