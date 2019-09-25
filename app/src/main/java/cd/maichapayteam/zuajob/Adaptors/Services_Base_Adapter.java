package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import cd.maichapayteam.zuajob.R;
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
        S_descriptions.setText(S.getSousCategorie()+" > "+ S.getSousCategorie());
        S_prix.setText(S.getMontant()+ " "+ S.getDevise());
        realisation.setText(S.getNombreRealisation()+" Réalisation (s)");

        int cote = S.getCote();
        int real = S.getNombreRealisation()*10;
        if (real == 0) real = 1;
        float rating = cote * 5 / real;
        Rating.setRating(cote);

        int profil = 0;
        if (position%3 == 0)
            profil = R.drawable.avatar3;
        else{
            profil = R.drawable.avatar2;
        }
        avatar.setImageResource(profil);

        final int finalProfil = profil;
        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details(S, finalProfil);
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
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        return convertView;
    }


    private void details(final Service S, int profil){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_jobeurs_details,null);
        TextView nom = convertView.findViewById(R.id.nom);
        TextView S_descriptions = convertView.findViewById(R.id.S_descriptions);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView share = convertView.findViewById(R.id.share);
        TextView comment = convertView.findViewById(R.id.comment);
        TextView like = convertView.findViewById(R.id.like);
        TextView realisation = convertView.findViewById(R.id.realisation);
        TextView number = convertView.findViewById(R.id.number);
        TextView categorie = convertView.findViewById(R.id.categorie);
        RatingBar Rating = convertView.findViewById(R.id.MyRating);
        ImageView avatar = convertView.findViewById(R.id.avatar);

        nom.setText(S.getNomsJobeur());
        categorie.setText(S.getCategorie()+" > "+S.getSousCategorie());
        number.setText(S.getPhoneJobeur());
        S_descriptions.setText(S.getDescription());
        S_prix.setText(S.getMontant()+ " "+ S.getDevise());
        realisation.setText(S.getNombreRealisation()+" Réalisation (s)");
        avatar.setImageResource(profil);
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
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(true)
                .setPositiveButton("Solliciter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long idS = S.getId();
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


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "La fonctionnalité est prévue pour une versions ulterieure", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "La fonctionnalité est prévue pour une versions ulterieure", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "La fonctionnalité est prévue pour une versions ulterieure", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }



}
