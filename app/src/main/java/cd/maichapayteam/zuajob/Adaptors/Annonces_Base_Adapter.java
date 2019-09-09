package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.BackEnd.Objects.Services;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.RoundedImageView;

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
        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_annonces,null);
        TextView description = convertView.findViewById(R.id.description);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView number = convertView.findViewById(R.id.number);
        TextView time = convertView.findViewById(R.id.time);
        RoundedImageView avatar = convertView.findViewById(R.id.avatar);
        LinearLayout element = convertView.findViewById(R.id.element);

        if (DATA == null ) {
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Taile "+ DATA.size(), Toast.LENGTH_SHORT).show();
        }

        final Services S = DATA.get(position);

        // todo : Affects values to the componants
        nom_user.setText(S.getNom_user());
        description.setText(S.getDescription_services());

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
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_annonce_details,null);
        TextView share = convertView.findViewById(R.id.share);
        TextView comment = convertView.findViewById(R.id.comment);
        TextView description = convertView.findViewById(R.id.description);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView number = convertView.findViewById(R.id.number);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView time = convertView.findViewById(R.id.time);
        RoundedImageView avatar = convertView.findViewById(R.id.avatar);

        nom_user.setText(S.getNom_user());
        description.setText(S.getDescription_services());
        S_prix.setText(S.getPrix().concat(" USD"));
        avatar.setImageResource(profil);

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
