package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Services_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Service> DATA;

    public Services_Base_Adapter(Context context, ArrayList<Service> DATA) {
        this.context = context;
        this.DATA = DATA;
    }

    @Override
    public int getCount() {
        return DATA.size();
    }

    @Override
    public Object getItem(int position) {
        return DATA.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_service,null);
        TextView S_categorie = convertView.findViewById(R.id.S_categorie);
        TextView S_descriptions = convertView.findViewById(R.id.S_descriptions);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView number = convertView.findViewById(R.id.number);
        TextView realisation = convertView.findViewById(R.id.realisation);
        RatingBar Rating = convertView.findViewById(R.id.MyRating);
        ImageView avatar = convertView.findViewById(R.id.avatar);
        LinearLayout element = convertView.findViewById(R.id.element);

        if (DATA == null ) {
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        }

        final Service S = DATA.get(position);

        // todo : Affects values to the componants
        S_categorie.setText(S.getNomsJobeur());
        number.setText(S.getPhoneJobeur());
        S_descriptions.setText(S.getCategorie()+" > "+ S.getSousCategorie());
        S_prix.setText(S.getMontant()+ " "+ S.getDevise());
        realisation.setText(S.getNombreRealisation()+" Réalisation (s)");

        int cote = S.getCote();
        int real = S.getNombreRealisation()*10;
        if (real == 0) real = 1;
        float rating = cote * 5 / real;
        Rating.setRating(cote);

        Tool.Load_Image2(context, avatar,S.getUrlImageJobeur());
        //Log.e("IMAGE_PROFIL", S.getUrlImageJobeur());

        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details(S);
            }
        });
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenu().add("Appeller "+S.getPhoneJobeur()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.getMenu().add("Ouvrir une conversation WhatsApp").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Tool.LAUNCH_WHATAP(context, S.getPhoneJobeur());
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        return convertView;
    }

    private void details(final Service S){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_service_details_random,null);
        TextView nom = convertView.findViewById(R.id.nom);
        TextView number = convertView.findViewById(R.id.number);
        TextView categorie = convertView.findViewById(R.id.categore);
        TextView S_descriptions = convertView.findViewById(R.id.description);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView realisation = convertView.findViewById(R.id.realisation);
        RatingBar Rating = convertView.findViewById(R.id.MyRating);
        ImageView avatar = convertView.findViewById(R.id.avatar);


        nom.setText(S.getNomsJobeur());
        categorie.setText(S.getCategorie()+" > "+S.getSousCategorie());
        number.setText(S.getPhoneJobeur());
        S_descriptions.setText(S.getDescription());
        S_prix.setText(S.getMontant()+ " "+ S.getDevise());
        realisation.setText(S.getNombreRealisation()+" Réalisation (s)");
        Tool.Load_Image2(context, avatar,S.getUrlImageJobeur());
        int cote = S.getCote();
        int real = S.getNombreRealisation()*10;
        if (real == 0) real = 1;
        float rating = cote * 5 / real;
        Rating.setRating(cote);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenu().add("Appeller "+S.getPhoneJobeur()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.getMenu().add("Ouvrir une conversation WhatsApp").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Tool.LAUNCH_WHATAP(context, S.getPhoneJobeur());
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(true)
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

            User u = GeneralClass.Currentuser;
            if (u.getId() != S.getIdJobeur()){
                a.setPositiveButton("Solliciter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long idS = S.getId();

                        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo info = manager.getActiveNetworkInfo();
                        if (info !=null && info.isConnected()){
                            SOLLICITER(idS);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(context, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        final AlertDialog alert = a.create();
        alert.show();

    }

    private void SOLLICITER(final long idS){
        AsyncTask aaa = new AsyncTask<Void, Void, Sollicitation>() {
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
                write_response.setText("Opération encours...");
                alert.show();
            }

            @Override
            protected Sollicitation doInBackground(Void... voids) {
                return ManageLocalData.solliciter(idS);
            }

            @Override
            protected void onPostExecute(Sollicitation service) {
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
