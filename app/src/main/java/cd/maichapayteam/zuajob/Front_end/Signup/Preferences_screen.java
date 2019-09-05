package cd.maichapayteam.zuajob.Front_end.Signup;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;

import cd.maichapayteam.zuajob.Front_end.Home;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.RemoteDataSync;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Preferences_screen extends AppCompatActivity {


    Context context = this;
    ImageView btn_back_arrow;
    TextView btn_next;

    GridView list_item;
    GridView list_item_selected;


    ArrayList<String> l = new ArrayList<>();
    ArrayList<String> l2 = new ArrayList<>();


    private void Init_Components(){
        //TODO si l'utilisateur a moins de 18 ans n'afficher pas la caégorie sexe
        btn_back_arrow = findViewById(R.id.btn_back_arrow);
        btn_next = findViewById(R.id.btn_next);
        list_item = findViewById(R.id.list_item);
        list_item_selected = findViewById(R.id.list_item_selected);

        for (int i = 0; i < 10; i++) {
            l.add("Cat "+i);
        }

        ArrayAdapter a = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l);
        list_item.setAdapter(a);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_screen);
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

        /*list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                l2.add(l.get(position));
                l.remove(position);
                ArrayAdapter a1 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l);
                ArrayAdapter a2 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l2);
                list_item.setAdapter(a1);
                list_item_selected.setAdapter(a2);

            }
        });
        list_item_selected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                l.add(l2.get(position));
                l2.remove(position);
                ArrayAdapter a1 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l);
                ArrayAdapter a2 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,l2);
                list_item.setAdapter(a1);
                list_item_selected.setAdapter(a2);

            }
        });*/


        btn_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreationAccountAsync creationAccountAsync = new CreationAccountAsync();
                creationAccountAsync.execute();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, Password_screen.class);
        startActivity(i);
        finish();
    }

    class CreationAccountAsync extends AsyncTask<String, String, User> implements RemoteDataSync.LoadImageListener {

        @Override
        protected void onPreExecute() {
            //TODO : show a load dialog here
            super.onPreExecute();
        }

        @Override
        protected User doInBackground(String... strings) {
            String urlPhoto = "";
            String picture = Tool.getUserPreferences(Preferences_screen.this, "picture");
            if(!picture.equals("default")) {
                File file = new File(picture);
                String result = RemoteDataSync.uploadImage(file, null);
                if(!result.contains("error")) urlPhoto = result;
            }

            User user = new User();
            user.urlPhoto = urlPhoto;
            //TODO ajouter le champs ville sur la liste
            user.ville = "";
            user.adresse = Tool.getUserPreferences(Preferences_screen.this, "avenue");
            user.quartier = Tool.getUserPreferences(Preferences_screen.this, "quartier");
            user.commune = Tool.getUserPreferences(Preferences_screen.this, "commune");
            user.about = Tool.getUserPreferences(Preferences_screen.this, "apropos");
            user.email = Tool.getUserPreferences(Preferences_screen.this, "email");
            user.nom = Tool.getUserPreferences(Preferences_screen.this, "nom");
            user.prenom = Tool.getUserPreferences(Preferences_screen.this, "prenom");
            long birthday = 0;
            try {
                Date date = new SimpleDateFormat("").parse(Tool.getUserPreferences(Preferences_screen.this, "birthday"));
                birthday = date.getTime();
            } catch (ParseException e) {

            }
            user.birthday = birthday;
            user.sexe = Tool.getUserPreferences(Preferences_screen.this, "sexe");
            user.password = Tool.getUserPreferences(Preferences_screen.this, "passe");
            user.phone = Integer.parseInt(Tool.getUserPreferences(Preferences_screen.this, "phone"));
            user.codePays = Tool.getUserPreferences(Preferences_screen.this, "CountryCode");
            user.pays = Tool.getUserPreferences(Preferences_screen.this, "CountryName");
            user.type = Integer.parseInt(Tool.getUserPreferences(Preferences_screen.this, "type_user"));


            //TODO see how to send preferrences in server

            return RemoteDataSync.createUser(user);
        }

        @Override
        protected void onPostExecute(User user) {
            //TODO : dismiss a load dialog here
            if(user.error) {
                //TODO il y a erreur d'ajout, le signaler à l'utilisateur
                Log.e("CreateUser", user.errorMessage);
                Toast.makeText(Preferences_screen.this, user.errorMessage, Toast.LENGTH_LONG).show();
            } else {
                //Le user s'est incrit avec succès et ses informations sont enregistrées dans la base de données
                Intent i = new Intent(context, Home.class);
                startActivity(i);
                finish();
            }

            super.onPostExecute(user);
        }

        @Override
        public void OnResult(long id, String url) {

        }

        @Override
        public void OnProgress(long bytesUploaded, long totalBytes) {
            //TODO update onProgress in dialog
        }

        @Override
        public void OnError(String message) {

        }
    }

}
