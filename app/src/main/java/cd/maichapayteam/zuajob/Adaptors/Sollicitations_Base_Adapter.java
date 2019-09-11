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

import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Sollicitations_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Sollicitation> DATA;
    boolean recent = false;
    boolean aujourd = false;

    public Sollicitations_Base_Adapter(Context context, ArrayList<Sollicitation> DATA) {
        this.context = context;
        this.DATA = DATA;
        Tool.setUserPreferences(context, "recent","");
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

        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_sollicitation,null);

        TextView view = convertView.findViewById(R.id.view);
        TextView call = convertView.findViewById(R.id.call);
        TextView time = convertView.findViewById(R.id.time);
        TextView categorie = convertView.findViewById(R.id.categorie);
        TextView Scategorie = convertView.findViewById(R.id.Scategorie);
        ImageView avatar = convertView.findViewById(R.id.avatar);
        ImageView more = convertView.findViewById(R.id.more);
        TextView periode = convertView.findViewById(R.id.periode);
        LinearLayout periode_layout = convertView.findViewById(R.id.periode_layout);

        periode_layout.setVisibility(View.GONE);

        if (DATA == null ) {
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        }


        final Sollicitation S = DATA.get(position);

        // todo : Affects values to the componants
        time.setText(Tool.formatingDate(S.getDate()));

        // todo : Formatting date for headers
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        try {
            d1 = new Date(sdf1.parse(S.getDate()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String today = dateFormat.format(new Date());
        String dat   = dateFormat.format(d1);
        Log.e("DDDDD",S.getDate() +"   "+ today);

        if(dat.equals(today)) {
            if(!aujourd) {
                aujourd = true;
                DATA.get(position).setAujourd(true);
                S.setAujourd(true);
            }
        } else {
            if(!recent) {
                recent = true;
                DATA.get(position).setRecent(true);
                S.setRecent(true);
            }
        }

        if(S.isRecent()) {
            periode_layout.setVisibility(View.VISIBLE);
            periode.setText("Recent");
        }
        if(S.isAujourd()) {
            periode_layout.setVisibility(View.VISIBLE);
            periode.setText("Aujourd'hui");
        }

        categorie.setText(S.getCategorie());
        Scategorie.setText(S.getSouscategorie());

        int profil = 0;
        if (position%3 == 0)
            profil = R.drawable.avatar3;
        else{
            profil = R.drawable.avatar2;
        }
        avatar.setImageResource(profil);

        final int finalProfil = profil;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details(S, finalProfil);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
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

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenu().add("Accepter la sollicitation ").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.getMenu().add("Refuser la sollicitaion").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
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
