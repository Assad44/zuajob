package cd.maichapayteam.zuajob;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cd.maichapayteam.zuajob.Adaptors.Annonces_Base_Adapter_random;
import cd.maichapayteam.zuajob.Adaptors.Categorie_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Categorie_recherche_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Sous_cat_Base_Adapter;
import cd.maichapayteam.zuajob.Adaptors.Test_Base_Adapter;
import cd.maichapayteam.zuajob.Front_end.About;
import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Categorie_view;
import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.Front_end.Jobeur_list;
import cd.maichapayteam.zuajob.Front_end.Mines.Mes_Sollicitations;
import cd.maichapayteam.zuajob.Front_end.Mines.Mes_annonces;
import cd.maichapayteam.zuajob.Front_end.Mines.Mes_postulances;
import cd.maichapayteam.zuajob.Front_end.Mines.Mes_rendez_vous;
import cd.maichapayteam.zuajob.Front_end.Mines.Mes_services;
import cd.maichapayteam.zuajob.Front_end.Mines.Mes_services_sollicites;
import cd.maichapayteam.zuajob.Front_end.Paramettres;
import cd.maichapayteam.zuajob.Front_end.Profils.Myprofil;
import cd.maichapayteam.zuajob.Front_end.Publications_Annonces;
import cd.maichapayteam.zuajob.Front_end.Publications_services;
import cd.maichapayteam.zuajob.Front_end.Signup.index_screen;
import cd.maichapayteam.zuajob.Front_end.Sous_categories;
import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.GenerateData;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.RemoteDataSync;
import cd.maichapayteam.zuajob.Tools.Tool;
import pl.droidsonroids.gif.GifDrawable;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    User u = GeneralClass.Currentuser;

    int exit = 0;
    int sub_M = 0;
    ListView list;
    Toolbar toolbar;
    CardView catégorie_H;
    FloatingActionButton fab;
    DrawerLayout drawer;
    NavigationView navigationView;
    SearchView rechercher;
    LinearLayout search_bar,sous;
    TextView BTN_categorie,BTN_jober,BTN_annonces,BTN_services;
    SwipeRefreshLayout swiper;
    CardView about;

    ArrayList<Categorie> DATA = new ArrayList<>();
    List<SousCategorie> LSC = new ArrayList<>();
    ArrayList<SousCategorie> SearchSC = new ArrayList<>();
    List<Categorie> DATA1 = new ArrayList<>();
    //HorizontalListView hlistview;

    private void Init_Components(){
        //myProfile = User.myProfile();µµ*

        //TODO modifier le screen par rapport au profil de l'utilisateur

        fab = findViewById(R.id.fab);
        /*phone = findViewById(R.id.phone);
        nom = findViewById(R.id.nom);*/
        swiper = findViewById(R.id.swiper);
        sous = findViewById(R.id.sous);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        rechercher = findViewById(R.id.rechercher);
        search_bar = findViewById(R.id.search_bar);
        about = findViewById(R.id.about);

        BTN_services = findViewById(R.id.BTN_services);
        BTN_categorie = findViewById(R.id.BTN_categorie);
        catégorie_H = findViewById(R.id.catégorie_H);
        BTN_jober = findViewById(R.id.BTN_jober);
        BTN_annonces = findViewById(R.id.BTN_annonces);
        list = findViewById(R.id.list);

        //fab.setVisibility(View.GONE);
        search_bar.setVisibility(View.GONE);

        rechercher.setIconified(true);
        //if (Tool.getUserPreferences(context,"type").equals("0"))
        //    BTN_annonces.setVisibility(View.GONE);

        //if (Tool.getUserPreferences(context,"type").equals("1"))
        //    BTN_jober.setVisibility(View.GONE);


        //Toast.makeText(Home.this, "Liste des users size : " + UserDAO.getInstance(GeneralClass.applicationContext).count(), Toast.LENGTH_LONG).show();

        //Log.e("Home", "Liste des users size : " + UserDAO.getInstance(GeneralClass.applicationContext).count());

        TestAsync testAsync = new TestAsync();
        testAsync.execute();
    }

    private void LoadCategories(){
        new AsyncTask<String, String, List<Categorie>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<Categorie> doInBackground(String... strings) {
                return ManageLocalData.listCategorie();
            }

            @Override
            protected void onPostExecute(List<Categorie> categories) {
                DATA = (ArrayList<Categorie>) categories;

                if (null == DATA) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else{
                    int i = 0;
                    for ( final Categorie c : DATA) {
                        LayoutInflater inflater = LayoutInflater.from(context);
                        View responses = inflater.inflate(R.layout.view_categorie, null);
                        CardView element = responses.findViewById(R.id.element);
                        TextView title = responses.findViewById(R.id.title);
                        ImageView img = responses.findViewById(R.id.img);
                        TextView description   = responses.findViewById(R.id.description);

                        title.setText( c.getDesignation());
                        description.setText(c.getDescription());
                        if (i%2 == 0)img.setImageResource(R.drawable.pub);
                        else img.setImageResource(R.drawable.pub4);

                        // Todo : Chargement des images par Ion librairy
                        //Tool.Load_Image(context,img,"");
                        try {
                            GifDrawable gifFromResource = new GifDrawable( context.getResources(), R.drawable.gif4);
                            Ion.with(img)
                                    .placeholder(gifFromResource)
                                    .error(R.drawable.baccc)
                                    .animateGif(AnimateGifMode.ANIMATE)
                                    .load("");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                        sous.addView(responses, 0);

                        final int finalI = i;
                        element.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (finalI != 0){
                                    Intent i = new Intent(context, Sous_categories.class);
                                    i.putExtra("title",c.getDesignation());
                                    String identifiant = String.valueOf(c.getId());
                                    i.putExtra("id",identifiant);
                                    startActivity(i);
                                    finish();
                                }else{
                                    startActivity(new Intent(context, Categorie_view.class));
                                    finish();
                                }
                            }
                        });

                        if (i == 0){
                            title.setText("Autres catégories");
                            description.setText("");
                            img.setImageResource(R.drawable.pub);
                        }
                        if (i == 3){
                            break;
                        }
                        i++;
                    }
                }
            }
        }.execute();
    }

    private void LoadSousCategorie(){
        // TODO la tache asynchronne
        new AsyncTask<String, Void, String>(){
            Categorie sc = new Categorie();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                sc.setId(1);
                swiper.setRefreshing(true);
            }

            @Override
            protected String doInBackground(String... strings) {
                LSC = ManageLocalData.listSousCategorie(sc);
                return null;
            }
            @Override
            protected void onPostExecute(String o) {
                swiper.setRefreshing(false);
            }
        }.execute();

    }

    private void Header_initialize(){
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(u.getNom());
            /*nom.setText(Tool.getUserPreferences(context, "nom"));
            String numero = Tool.getUserPreferences(context, "CountryCode") +" "+ Tool.getUserPreferences(context, "phone");
            phone.setText(numero);*/
            //getSupportActionBar().setIcon(R.drawable.ic_humburger);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // TODO ; Visibilities
        if (u.getType() == 0){// utilisateur simple
            BTN_annonces.setVisibility(View.GONE);
            navigationView.inflateMenu(R.menu.activity_home_drawer_simple_user);

        }else{
            BTN_jober.setVisibility(View.GONE);
            navigationView.inflateMenu(R.menu.activity_home_drawer);
            requiered_location();
        }


        Tool.Set_Alarm(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar(toolbar);

        if (GeneralClass.Currentuser==null){
            // Todo : Launche Home activity
            Intent i = new Intent(context, index_screen.class);
            startActivity(i);
            finish();
        }

        Init_Components();
        Header_initialize();

        // Todo loading caterogies
        LoadCategories();
        LoadSousCategorie();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //drawer.openDrawer(GravityCompat.START);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Publication_blank.class);
                i.putExtra("type", "other");
                startActivity(i);
                finish();
            }
        });

        rechercher.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() < 1){
                    list.setVisibility(View.GONE);
                    search_bar.setVisibility(View.GONE);
                }else if(newText.equals("*")){
                    list.setAdapter(new Categorie_recherche_Base_Adapter(context, (ArrayList<SousCategorie>) LSC ));
                }else{
                    list.setVisibility(View.VISIBLE);
                    search_bar.setVisibility(View.VISIBLE);
                    // TODO Filtrage
                    SearchSC.clear();
                    for ( SousCategorie s : LSC ) {
                        if (
                                s.getDesignation().toUpperCase().contains(newText.toUpperCase())
                        ){
                            SearchSC.add(s);
                        }
                    }
                    list.setAdapter(new Categorie_recherche_Base_Adapter(context, SearchSC));
                }
                return true;
            }
        });

        rechercher.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_bar.setVisibility(View.VISIBLE);
            }
        });
        rechercher.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                search_bar.setVisibility(View.GONE);
                Toast.makeText(context, "closed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(context, Details_publication.class));
                finish();
            }
        });
        BTN_categorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Categorie_view.class));
                finish();
            }
        });
        catégorie_H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Categorie_view.class));
                finish();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, About.class));
                finish();
            }
        });
        BTN_annonces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Publications_Annonces.class);
                i.putExtra("type", "Annonces");
                startActivity(i);
                finish();
            }
        });
        BTN_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Publications_services.class);
                i.putExtra("type", "Services");
                startActivity(i);
                finish();
            }
        });
        BTN_jober.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Jobeur_list.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {
            //exit_alert();
            if (exit == 0) {
                Toast.makeText(context, "Appuyer encore pour quitter", Toast.LENGTH_SHORT).show();
                exit = 1;
            } else finish();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = 0;
                }
            }, 2000);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
        if (id == R.id.profil) {
            Intent i = new Intent(context, Myprofil.class);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean m = true;
        if (id == R.id.nav_annonce) {
            Intent i = new Intent(context, Mes_annonces.class);
            startActivity(i);
            finish();
            m = true;
        } else if (id == R.id.nav_services) {
            Intent i = new Intent(context, Mes_services.class);
            startActivity(i);
            finish();
            m = true;
        } else if (id == R.id.nav_rdv) {
            Intent i = new Intent(context, Mes_rendez_vous.class);
            startActivity(i);
            finish();
            m = true;


        } else if (id == R.id.nav_rdv_jobeur) {
            m = true;
        } else if (id == R.id.nav_rdv_annonce) {
            Intent i = new Intent(context, Mes_rendez_vous.class);
            startActivity(i);
            finish();
            m = true;
        } else if (id == R.id.nav_rdv_service) {
            Intent i = new Intent(context, Mes_rendez_vous.class);
            startActivity(i);
            finish();
            m = true;


        }else if (id == R.id.nav_service_soliicioation) {
            Intent i = new Intent(context, Mes_services_sollicites.class);
            startActivity(i);
            finish();
            m = true;
        } else if (id == R.id.nav_soliicioation) {
            Intent i = new Intent(context, Mes_Sollicitations.class);
            startActivity(i);
            finish();
            m = true;
        } else if (id == R.id.nav_postullance) {
            Intent i = new Intent(context, Mes_postulances.class);
            startActivity(i);
            finish();
            m = true;
        }

        if (m == true){
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    class TestAsync extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            //List<Annonce> annonceList = RemoteDataSync.getMesAnnonces();
            //Log.e("Test", "Liste mes annonces size: " + annonceList.size());
            return null;
        }

    }


    /**
     * Requierement
     */
    private void requiered_location(){
        if (null == u) return;
        if (u.getType() == 1){
            if (u.getAdresse().equals("")){
                View convertView  = LayoutInflater.from(context).inflate(R.layout.view_requiere_adresse,null);
                TextView BTN_add = convertView.findViewById(R.id.BTN_add);
                ImageView close = convertView.findViewById(R.id.close);

                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setView(convertView)
                        .setCancelable(true);
                final AlertDialog alert = a.create();
                alert.show();

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.cancel();
                    }
                });

                BTN_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Edit_addresses_alert();
                    }
                });


            }

        }

    }


    void Edit_addresses_alert(){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_edit_adresse,null);
        final TextView street = convertView.findViewById(R.id.street);
        final TextView quartier = convertView.findViewById(R.id.quartier);
        final TextView commune = convertView.findViewById(R.id.commune);
        final TextView email = convertView.findViewById(R.id.email);
        final TextView about_U = convertView.findViewById(R.id.about_U);

        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(false)
                .setPositiveButton("Mettre a jours", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Todo : Checking empty zone
                        if (TextUtils.isEmpty(street.getText().toString())){
                            street.setError("Veuillez fournir cette information");
                            return;
                        }else if (TextUtils.isEmpty(quartier.getText().toString())){
                            quartier.setError("Veuillez fournir cette information");
                            return;
                        }else if (TextUtils.isEmpty(commune.getText().toString())){
                            commune.setError("Veuillez fournir cette information");
                            return;
                        }else if (TextUtils.isEmpty(email.getText().toString())){
                            email.setError("Veuillez fournir cette information");
                            return;
                        }else if (TextUtils.isEmpty(about_U.getText().toString())){
                            about_U.setError("Veuillez fournir cette information");
                            return;
                        }


                        u.setAdresse(street.getText().toString().replace("'","''"));
                        u.setQuartier(quartier.getText().toString().replace("'","''"));
                        u.setCommune(quartier.getText().toString().replace("'","''"));
                        u.setEmail(email.getText().toString().replace("'","''"));
                        u.setDescription(about_U.getText().toString().replace("'","''"));

                        // Todo : methode pour mette a jour les information du user
                        update();

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

    private void update(){
        AsyncTask aaa = new AsyncTask<Void, Void, User>() {
            Service s = new Service();
            View convertView  = LayoutInflater.from(context).inflate(R.layout.view_progressebar,null);
            TextView write_response = convertView.findViewById(R.id.write_response);
            AlertDialog.Builder a = new AlertDialog.Builder(context)
                    .setView(convertView)
                    .setCancelable(false);
            // Setting dialogview
            final AlertDialog alert = a.create();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                write_response.setText("Mise à jour des information en cours...");
                alert.show();
            }

            @Override
            protected User doInBackground(Void... voids) {
                return ManageLocalData.updateUser(u);
            }

            @Override
            protected void onPostExecute(User service) {
                alert.cancel();
                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                if (service.isError() == true ){
                    a.setMessage(service.getErrorMessage()+ " "+service.getErrorCode());
                }else{
                    a.setMessage("Opération réussi");

                }
                a.show();
            }
        }.execute();

    }






}