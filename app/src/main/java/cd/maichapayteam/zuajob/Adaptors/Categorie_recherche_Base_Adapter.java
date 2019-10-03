package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Front_end.Publication_annonces_by_category;
import cd.maichapayteam.zuajob.Front_end.Publication_services_by_category;
<<<<<<< HEAD
=======
import cd.maichapayteam.zuajob.Front_end.Sous_categories;
>>>>>>> origin/master
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Categorie_recherche_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<SousCategorie> DATA;

    public Categorie_recherche_Base_Adapter(Context context, ArrayList<SousCategorie> DATA) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.model_searche_resulte,null);
        TextView  souscategorie = convertView.findViewById(R.id.souscategorie);
        CardView element = convertView.findViewById(R.id.element);
        final LinearLayout block = convertView.findViewById(R.id.block);
        TextView open_annonces = convertView.findViewById(R.id.open_annonces);
        TextView open_services = convertView.findViewById(R.id.open_services);
        souscategorie.setText(DATA.get(position).getDesignation());

        block.setVisibility(View.GONE);

        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation dropdup = AnimationUtils.loadAnimation(context,R.anim.move_up);
                final Animation dropdown = AnimationUtils.loadAnimation(context,R.anim.move_down);
                if (block.getVisibility() == View.GONE){
                    block.setAnimation(dropdown);
                    block.setVisibility(View.VISIBLE);
                }else{
                    block.setAnimation(dropdup);
                    block.setVisibility(View.GONE);
                }
            }
        });

        open_annonces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Publication_annonces_by_category.class);
                i.putExtra("ID_CATEGORIE", DATA.get(position).getIdCategorie() );
                i.putExtra("CATEGORIE", "Recherche" );
                i.putExtra("ID_SOUSCATEGORIE", DATA.get(position).getId() );
                //Log.e("DDDDD","ssssssssssms "+ DATA.get(position).getId());
                i.putExtra("SOUSCATEGORIE", DATA.get(position).getDesignation() );
                context.startActivity(i);
            }
        });
        open_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Publication_services_by_category.class);
                i.putExtra("ID_CATEGORIE", DATA.get(position).getIdCategorie() );
                i.putExtra("CATEGORIE", "Recherche" );
                i.putExtra("ID_SOUSCATEGORIE", DATA.get(position).getId() );
                i.putExtra("SOUSCATEGORIE", DATA.get(position).getDesignation() );
                context.startActivity(i);
            }
        });
        
        
        return convertView;
    }
}
