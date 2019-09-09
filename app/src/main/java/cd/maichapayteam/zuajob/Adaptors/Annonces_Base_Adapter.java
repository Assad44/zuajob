package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.BackEnd.Objects.Services;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Annonces_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Services> DATA;

    public Annonces_Base_Adapter(Context context, ArrayList<Services> DATA) {
        this.context = context;
        this.DATA = DATA;
    }

    @Override
    public int getCount() {
        return DATA.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_test,null);
        TextView S_categorie = convertView.findViewById(R.id.S_categorie);
        TextView S_descriptions = convertView.findViewById(R.id.S_descriptions);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView realisation = convertView.findViewById(R.id.realisation);
        RatingBar Rating = convertView.findViewById(R.id.MyRating);
        ImageView avatar = convertView.findViewById(R.id.avatar);
        LinearLayout element = convertView.findViewById(R.id.element);



        if (DATA == null ) {
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Taile "+ DATA.size(), Toast.LENGTH_SHORT).show();
        }

        final Services S = DATA.get(position);

        // todo : Affects values to the componants
        S_categorie.setText(S.getNom_user());
        S_descriptions.setText(S.getDescription_services());
        S_prix.setText(S.getPrix().concat(" USD"));
        realisation.setText(S.getNbr_services().concat(" Réalisation (s)"));

        int cote = Integer.parseInt(S.getNbr_cote());
        int real = (Integer.parseInt(S.getNbr_services())*10);
        if (real == 0) real = 1;
        float rating = cote * 5 / real;
        Rating.setRating(rating);

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

        return convertView;
    }


    private void details(Services S, int profil){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_jobeurs_details,null);
        TextView S_categorie = convertView.findViewById(R.id.nom);
        TextView S_descriptions = convertView.findViewById(R.id.S_descriptions);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView realisation = convertView.findViewById(R.id.realisation);
        RatingBar Rating = convertView.findViewById(R.id.MyRating);
        ImageView avatar = convertView.findViewById(R.id.avatar);

        S_categorie.setText(S.getNom_user());
        S_descriptions.setText(S.getDescription_services());
        S_prix.setText(S.getPrix().concat(" USD"));
        realisation.setText(S.getNbr_services().concat(" Réalisation (s)"));
        avatar.setImageResource(profil);
        int cote = Integer.parseInt(S.getNbr_cote());
        float rating = cote * 5 / (Integer.parseInt(S.getNbr_services())*10);
        Rating.setRating(rating);

        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(false)
                .setPositiveButton("Solliciter", new DialogInterface.OnClickListener() {
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

}
