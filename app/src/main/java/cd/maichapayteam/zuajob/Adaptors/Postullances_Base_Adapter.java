package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
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

import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Postullances_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Postuler> DATA;

    public Postullances_Base_Adapter(Context context, ArrayList<Postuler> DATA) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_postullances,null);
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

        final Postuler S = DATA.get(position);
        // todo : Affects values to the componants
        nom_user.setText(S.getNomsUser());
        number.setText(S.getPhoneUser());


        description.setText(S.getDescriptionAnnonce());
        categorie.setText(
               "Les categories ne s'y retrouve pas" /*S.getCategorie()+">"+S.getSousCategorie()*/
        );

        Tool.Load_Image2(context, avatar,S.getUrlImageUser());


        time.setText(Tool.formatingDate(S.getDate()));

        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //details(S);
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

}
