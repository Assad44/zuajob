package cd.maichapayteam.zuajob.Front_end.Profils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;

import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Home;
import cd.maichapayteam.zuajob.Front_end.Signup.PhoneVerif_screen;
import cd.maichapayteam.zuajob.Front_end.Signup.index_screen;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.FilePath;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Myprofil extends AppCompatActivity {

    Context context = this;
    private static final int PICK_FILE_REQUEST = 12;
    TextView update_Adresses;
    ImageView picture, Pickpicture;

    Toolbar toolbar;

    private void Init_Components(){
        update_Adresses = findViewById(R.id.update_Adresses);
        picture = findViewById(R.id.picture);
        Pickpicture = findViewById(R.id.Pickpicture);
        /*btn_jobeur = findViewById(R.id.btn_jobeur);
        btn_simple_user = findViewById(R.id.btn_simple_user);
        btn_back_arrow = findViewById(R.id.btn_back_arrow);*/
        toolbar = findViewById(R.id.toolbar);
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

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update_Adresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_addresses_alert();
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
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.share) {
            Toast.makeText(context, "Not yet done", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_settings) {
            Toast.makeText(context, "Not yet done", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.nav_exit) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void Edit_addresses_alert(){
        View view  = LayoutInflater.from(context).inflate(R.layout.view_edit_adresse,null);
        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("Mettre a jours", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = a.create();
        alert.show();

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
