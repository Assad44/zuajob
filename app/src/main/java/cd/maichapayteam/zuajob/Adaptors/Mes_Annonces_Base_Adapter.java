package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
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
        TextView time = convertView.findViewById(R.id.time);
        TextView price = convertView.findViewById(R.id.price);
        TextView delate = convertView.findViewById(R.id.delate);
        TextView categorie = convertView.findViewById(R.id.categorie);
        LinearLayout element = convertView.findViewById(R.id.element);
        LinearLayout header = convertView.findViewById(R.id.header);

        final Annonce S = DATA.get(position);
        // todo : Affects values to the componants
        description.setText(S.getDescription());
        price.setText(S.getMontant()+ " "+ S.getDevise());
        categorie.setText(
                S.getCategorie()+" | "+S.getSousCategorie()
        );

        Log.e("TIMMMMMMMMMMMMM", S.getDatePublication());
        time.setText(Tool.formatingDate(S.getDatePublication()));

        if (DATA == null ) {
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        }

        if (S.isConfied() == true){
            confier.setText("Déjà confié");
            confier.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }else{
            confier.setText("Pas encore confié");
            confier.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }

        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details(S);
            }
        });
        delate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "..............", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }



    private void details(final Annonce S){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_annonce_details_mine,null);
        TextView share = convertView.findViewById(R.id.share);
        TextView comment = convertView.findViewById(R.id.comment);
        TextView description = convertView.findViewById(R.id.description);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView number = convertView.findViewById(R.id.number);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView delate = convertView.findViewById(R.id.delete);
        TextView postullants = convertView.findViewById(R.id.postullants);
        TextView time = convertView.findViewById(R.id.time);
        TextView categore = convertView.findViewById(R.id.categore);
        ImageView avatar = convertView.findViewById(R.id.avatar);

        if (S.isConfied() == true){
            nom_user.setText(S.getNomsUser());
            number.setText(S.getPhoneUser());
            postullants.setVisibility(View.GONE);
        }else{
            postullants.setVisibility(View.VISIBLE);
            nom_user.setText("Aucun jobeur n'a été habilité pour cette annonce");
            number.setText("");
        }
        Tool.Load_Image(context,avatar,S.getUrlImageUser());
        description.setText(S.getDescription());
        S_prix.setText(S.getMontant()+ " "+ S.getDevise());
        categore.setText(S.getCategorie()+ " | "+ S.getSousCategorie());
        time.setText(Tool.formatingDate(S.getDatePublication()));

        delate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "..............", Toast.LENGTH_SHORT).show();
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
        postullants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postullants(S);
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

    private void postullants(final Annonce id){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_list_postullants,null);
        final TextView count = convertView.findViewById(R.id.count);
        final GridView list= convertView.findViewById(R.id.list);
        final SwipeRefreshLayout swipper= convertView.findViewById(R.id.swipper);
        final ArrayList<Postuler>[] DATA_L = new ArrayList[]{new ArrayList<>()};

        AsyncTask task = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                DATA_L[0].clear();
                DATA_L[0] =  (ArrayList<Postuler>) ManageLocalData.listPostulants(id);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == DATA_L[0]) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (DATA_L[0].isEmpty()) Toast.makeText(context, "Aucune donnée"  , Toast.LENGTH_SHORT).show();
                else{
                    list.setAdapter(new Postullants_Base_Adapter(context, DATA_L[0]));
                    count.setText(list.getCount()+" Postullants");
                }
            }

        }.execute();

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

    }


}
