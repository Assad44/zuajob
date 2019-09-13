package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.RoundedImageView;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Mes_Annonces_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Annonce> DATA;

    public Mes_Annonces_Base_Adapter(Context context, ArrayList<Annonce> DATA, String mode) {
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
        TextView confier = convertView.findViewById(R.id.confier);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView number = convertView.findViewById(R.id.number);
        TextView time = convertView.findViewById(R.id.time);
        TextView categorie = convertView.findViewById(R.id.categorie);
        RoundedImageView avatar = convertView.findViewById(R.id.avatar);
        LinearLayout element = convertView.findViewById(R.id.element);
        LinearLayout header = convertView.findViewById(R.id.header);


        if (DATA == null ) {
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        }

        header.setVisibility(View.GONE);
        categorie.setTextSize(17);

        final Annonce S = DATA.get(position);
        // todo : Affects values to the componants
        nom_user.setText(S.getNomsUser());
        number.setText(S.getPhoneUser());
        description.setText(S.getDescription());
        categorie.setText(
                S.getCategorie()+">"+S.getSousCategorie()
        );

        int profil = 0;
        if (position%3 == 0){
            profil = R.drawable.avatar3;
            confier.setText("Tache déjà confiée");
            confier.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            confier.setVisibility(View.VISIBLE);
            S.setConfied(true);
        }else{
            profil = R.drawable.avatar2;
            confier.setText("Tache non confiée");
            confier.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            confier.setVisibility(View.VISIBLE);
            S.setConfied(false);
        }
        avatar.setImageResource(profil);

        time.setText(Tool.formatingDate(S.getDatePublication()));

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
                popupMenu.getMenu().add("Appeller "+S.getPhoneUser()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
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

    private void details(final Annonce S, int profil){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_annonce_details,null);
        TextView share = convertView.findViewById(R.id.share);
        TextView comment = convertView.findViewById(R.id.comment);
        TextView description = convertView.findViewById(R.id.description);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView number = convertView.findViewById(R.id.number);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView time = convertView.findViewById(R.id.time);
        TextView postullants = convertView.findViewById(R.id.postullants);
        TextView categore = convertView.findViewById(R.id.categore);
        RoundedImageView avatar = convertView.findViewById(R.id.avatar);


        if (S.isConfied == true){
            avatar.setImageResource(profil);
            nom_user.setText(S.getNomsUser());
            number.setText(S.getPhoneUser());
        }else{
            //avatar.setImageResource(profil);
            nom_user.setText("Aucun jobeur n'a été habilité pour cette annonce");
            number.setText("");
        }

        description.setText(S.getDescription());
        S_prix.setText(S.getMontant()+ " "+ S.getDevise());
        categore.setText(S.getCategorie()+ ">"+ S.getSousCategorie());
        time.setText(Tool.formatingDate(S.getDatePublication()));

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenu().add("Appeller "+S.getPhoneUser()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
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

        postullants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Postulant_list();
            }
        });


        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(true)
                .setPositiveButton("Postuler", new DialogInterface.OnClickListener() {
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


    }


    private void Postulant_list(){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.model_postulances,null);
        GridView list = convertView.findViewById(R.id.list);
        list.setAdapter(new Test_Base_Adapter(context, R.layout.view_postullants));
        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(true)
                .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = a.create();
        alert.show();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                details_jobeur();

            }
        });
    }

    private void details_jobeur(){
        View view  = LayoutInflater.from(context).inflate(R.layout.view_jobeurs_details2,null);

        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = a.create();
        alert.show();
    }



}
