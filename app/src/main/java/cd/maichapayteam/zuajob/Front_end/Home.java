package cd.maichapayteam.zuajob.Front_end;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.Adaptors.Test_Base_Adapter;
import cd.maichapayteam.zuajob.Front_end.Blanks.Publication_blank;
import cd.maichapayteam.zuajob.Front_end.Details.Details_publication;
import cd.maichapayteam.zuajob.Front_end.Mines.Mes_annonces;
import cd.maichapayteam.zuajob.Front_end.Profils.Myprofil;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.RemoteDataSync;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    int exit = 0;
    ListView list;
    Toolbar toolbar;
    CardView catégorie_H;
    FloatingActionButton fab;
    DrawerLayout drawer;
    NavigationView navigationView;
    SearchView rechercher;
    LinearLayout search_bar;
    TextView BTN_categorie,BTN_jober,BTN_annonces,BTN_services;

    User myProfile;

    //HorizontalListView hlistview;

    private void Init_Components(){
        //myProfile = User.myProfile();

        //TODO modifier le screen par rapport au profil de l'utilisateur

        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        rechercher = findViewById(R.id.rechercher);
        search_bar = findViewById(R.id.search_bar);

        BTN_services = findViewById(R.id.BTN_services);
        BTN_categorie = findViewById(R.id.BTN_categorie);
        catégorie_H = findViewById(R.id.catégorie_H);
        BTN_jober = findViewById(R.id.BTN_jober);
        BTN_annonces = findViewById(R.id.BTN_annonces);
        list = findViewById(R.id.list);

        fab.setVisibility(View.GONE);
        search_bar.setVisibility(View.GONE);

        rechercher.setIconified(true);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Init_Components();
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(Tool.getUserPreferences(context,"nom"));
            //getSupportActionBar().setIcon(R.drawable.ic_humburger);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //drawer.openDrawer(GravityCompat.START);
        toggle.syncState();

        //navigationView.inflateMenu(R.menu.activity_home_drawer);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    protected void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(context).inflate(R.layout.view_dialog_options,null);
                AlertDialog.Builder a = new AlertDialog.Builder(context)
                        .setCancelable(true)
                        .setView(v);
                final AlertDialog aa = a.create();
                aa.show();

                TextView cancel = v.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        aa.dismiss();
                    }
                });
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
                }else{
                    list.setVisibility(View.VISIBLE);
                    search_bar.setVisibility(View.VISIBLE);
                    list.setAdapter(new Test_Base_Adapter(context, R.layout.model_searche_resulte));
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
        BTN_annonces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Publications_view.class);
                i.putExtra("type", "Annonces");
                startActivity(i);
                finish();
            }
        });
        BTN_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Publications_view.class);
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
            exit_alert();
            /*if (exit == 0){
                Toast.makeText(context, "Appuyer encore pour quitter", Toast.LENGTH_SHORT).show();
                exit = 1;
            }else finish();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = 0;
                }
            }, 2000);*/
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
            Toast.makeText(context, "Not yet done", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_settings) {
            Toast.makeText(context, "Not yet done", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.nav_exit) {
            //onBackPressed();
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Déconnexion");
            adb.setMessage("Êtes-vous sûr de vouloir vous déconnecter ?");
            adb.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    User.deconnect();
                    finish();
                }
            });
            adb.setNegativeButton("Non", null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        
        if (id == R.id.nav_annonce) {
            Intent i = new Intent(context, Mes_annonces.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_services) {

        } else if (id == R.id.nav_rdv) {

        } else if (id == R.id.nav_soliicioation) {

        } else if (id == R.id.nav_discuusion) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class CheckAsync extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            RemoteDataSync.getListCategorie();
            return null;
        }

    }

}
