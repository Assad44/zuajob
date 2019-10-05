package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Mes_Services_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Service> DATA;

    public Mes_Services_Base_Adapter(Context context, ArrayList<Service> DATA) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_service_mine,null);
        TextView S_categorie = convertView.findViewById(R.id.S_categorie);
        TextView SS_categorie = convertView.findViewById(R.id.SS_categorie);
        final TextView sollicitations = convertView.findViewById(R.id.sollicitations);
        TextView S_descriptions = convertView.findViewById(R.id.S_descriptions);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        LinearLayout element = convertView.findViewById(R.id.element);

        TextView retirer = convertView.findViewById(R.id.retirer);
        TextView editer = convertView.findViewById(R.id.editer);

        if (DATA == null ) {
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        }

        final Service S = DATA.get(position);

        // todo : Affects values to the componants
        S_categorie.setText(S.getSousCategorie());
        S_descriptions.setText(S.getDescription());
        SS_categorie.setText(" Catégorie : "+ S.getSousCategorie());
        S_prix.setText(S.getMontant()+ " "+ S.getDevise());

        sollicitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SOLLICITANTS(S);
            }
        });

        return convertView;
    }

    private void SOLLICITANTS(final Service id){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_list_postullants,null);
        final TextView count = convertView.findViewById(R.id.count);
        final TextView confier = convertView.findViewById(R.id.confier);
        final GridView list= convertView.findViewById(R.id.list);
        final SwipeRefreshLayout swipper= convertView.findViewById(R.id.swipper);
        final ArrayList<Sollicitation>[] DATA_L = new ArrayList[]{new ArrayList<>()};

        confier.setText("Liste de sollicitants");

        AsyncTask task = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                swipper.setRefreshing(true);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                DATA_L[0].clear();
                DATA_L[0] =  (ArrayList<Sollicitation>) ManageLocalData.listSollicitant(id);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                swipper.setRefreshing(false);
                if (null == DATA_L[0]) Toast.makeText(context, "Null DATA", Toast.LENGTH_SHORT).show();
                else if (DATA_L[0].isEmpty()) Toast.makeText(context, "Aucune donnée"  , Toast.LENGTH_SHORT).show();
                else{
                    list.setAdapter(new Sollicitants_Base_Adapter(context, DATA_L[0]));
                    count.setText(list.getCount()+" Sollicitants");
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
