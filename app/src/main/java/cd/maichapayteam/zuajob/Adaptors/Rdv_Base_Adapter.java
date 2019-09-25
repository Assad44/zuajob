package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Rdv_Base_Adapter extends BaseAdapter {
    Context context;

    public Rdv_Base_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
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

        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_rdv,null);

        final LinearLayout details_option = convertView.findViewById(R.id.details_option);
        TextView BTN_valider = convertView.findViewById(R.id.BTN_valider);
        TextView annuler_rdv = convertView.findViewById(R.id.annuler_rdv);
        TextView editer_heure = convertView.findViewById(R.id.editer_heure);
        TextView coter = convertView.findViewById(R.id.coter);

        details_option.setVisibility(View.GONE);


        editer_heure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        annuler_rdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(context,R.style.MyDialogTheme)
                        .setTitle("Confirmation")
                        .setMessage("Voulez-vous vraimment annuler ce rendez-vous ?")
                        .setCancelable(true)
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                final AlertDialog alert = a.create();
                alert.show();
            }
        });
        BTN_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation dropdup = AnimationUtils.loadAnimation(context,R.anim.move_up);
                final Animation dropdown = AnimationUtils.loadAnimation(context,R.anim.move_down);
                if (details_option.getVisibility() == View.GONE){
                    details_option.setAnimation(dropdown);
                    details_option.setVisibility(View.VISIBLE);
                }else{
                    details_option.setAnimation(dropdup);
                    details_option.setVisibility(View.GONE);
                }
            }
        });
        coter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View convertView  = LayoutInflater.from(context).inflate(R.layout.view_dialog_options,null);
                ImageView close = convertView.findViewById(R.id.close);
                TextView excellent = convertView.findViewById(R.id.excellent);
                TextView bon = convertView.findViewById(R.id.bon);
                TextView assez_bon = convertView.findViewById(R.id.assez_bon);
                TextView mauvais = convertView.findViewById(R.id.mauvais);
                TextView mediocre = convertView.findViewById(R.id.mediocre);


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
            }
        });


        return convertView;
    }

    private void details(final Sollicitation S, int profil){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_jobeurs_details,null);
        TextView S_categorie = convertView.findViewById(R.id.nom);
        TextView S_descriptions = convertView.findViewById(R.id.S_descriptions);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView share = convertView.findViewById(R.id.share);
        TextView comment = convertView.findViewById(R.id.comment);
        TextView like = convertView.findViewById(R.id.like);
        TextView realisation = convertView.findViewById(R.id.realisation);
        TextView number = convertView.findViewById(R.id.number);
        RatingBar Rating = convertView.findViewById(R.id.MyRating);
        ImageView avatar = convertView.findViewById(R.id.avatar);

        S_categorie.setText(S.getNomsUser());
        number.setText(S.getPhoneUser());
        S_descriptions.setText(S.getDescriptionService());
        S_prix.setText(S.getMontant()+ " "+ S.getDevise());
        realisation.setVisibility(View.GONE);
        avatar.setImageResource(profil);

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


        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(true)
                .setPositiveButton("Acceper la sollicitation", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Refuser la sollicitation", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Fermer", new DialogInterface.OnClickListener() {
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
