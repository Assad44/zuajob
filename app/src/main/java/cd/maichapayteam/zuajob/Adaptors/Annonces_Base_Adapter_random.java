package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.RoundedImageView;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Annonces_Base_Adapter_random extends BaseAdapter {
    Context context;
    ArrayList<Annonce> DATA;

    public Annonces_Base_Adapter_random(Context context, ArrayList<Annonce> DATA, String mode) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_annonces_random,null);
        TextView description = convertView.findViewById(R.id.description);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView number = convertView.findViewById(R.id.number);
        TextView time = convertView.findViewById(R.id.time);
        TextView categorie = convertView.findViewById(R.id.categorie);
        final ImageView avatar = convertView.findViewById(R.id.avatar);
        LinearLayout element = convertView.findViewById(R.id.element);
        LinearLayout header = convertView.findViewById(R.id.header);

        //description.setVisibility(View.GONE);

        if (DATA == null ) {
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        }

        final Annonce S = DATA.get(position);
        // todo : Affects values to the componants
        nom_user.setText(S.getNomsUser());
        number.setText(S.getPhoneUser());



        description.setText(S.getDescription());
        categorie.setText(
                S.getCategorie()+">"+S.getSousCategorie()
        );

        Tool.Load_Image(context, avatar,S.getUrlImageUser());


        time.setText(Tool.formatingDate(S.getDatePublication()));

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

    private void details(final Annonce S){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_annonce_details_random,null);
        TextView share = convertView.findViewById(R.id.share);
        TextView comment = convertView.findViewById(R.id.comment);
        TextView description = convertView.findViewById(R.id.description);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView number = convertView.findViewById(R.id.number);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView postullants = convertView.findViewById(R.id.postullants);
        TextView time = convertView.findViewById(R.id.time);
        TextView categore = convertView.findViewById(R.id.categore);
        ImageView avatar = convertView.findViewById(R.id.avatar);

        postullants.setVisibility(View.GONE);

        Tool.Load_Image(context, avatar,S.getUrlImageUser());
        nom_user.setText(S.getNomsUser());
        number.setText(S.getPhoneUser());
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

        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(true)
                .setPositiveButton("Postuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        long idS = S.getId();
                        POSTULLER(idS);
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

    private void POSTULLER(final long idS){
        AsyncTask aaa = new AsyncTask<Void, Void, Postuler>() {
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
            protected Postuler doInBackground(Void... voids) {
                return ManageLocalData.postuler(idS);
            }

            @Override
            protected void onPostExecute(Postuler service) {
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
