package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cd.maichapayteam.zuajob.Front_end.Publication_annonces_by_category;
import cd.maichapayteam.zuajob.Front_end.Publication_services_by_category;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Sous_cat_Base_Adapter extends BaseAdapter {
    Context context;
    List<SousCategorie> LSC;
    String cat, id;

    public Sous_cat_Base_Adapter(Context context, List<SousCategorie> LSC, String cat, String id) {
        this.context = context;
        this.LSC = LSC;
        this.cat = cat;
        this.id = id;
    }

    @Override
    public int getCount() {
        return LSC.size();
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

        convertView = LayoutInflater.from(context).inflate(R.layout.model_sous_cat,null);
        TextView scat = convertView.findViewById(R.id.scat);
        TextView open_annonces = convertView.findViewById(R.id.open_annonces);
        TextView open_services = convertView.findViewById(R.id.open_services);
        final LinearLayout block = convertView.findViewById(R.id.block);
        
        block.setVisibility(View.GONE);

        scat.setText(LSC.get(position).getDesignation()/*.concat(" "+LSC.get(position).getId())*/);

        scat.setOnClickListener(new View.OnClickListener() {
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
                i.putExtra("ID_CATEGORIE", id );
                i.putExtra("CATEGORIE", cat );
                i.putExtra("ID_SOUSCATEGORIE", LSC.get(position).getId() );
                //Log.e("DDDDD","ssssssssssms "+ LSC.get(position).getId());
                i.putExtra("SOUSCATEGORIE", LSC.get(position).getDesignation() );
                context.startActivity(i);
            }
        });
        open_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Publication_services_by_category.class);
                i.putExtra("ID_CATEGORIE", id );
                i.putExtra("CATEGORIE", cat );
                i.putExtra("ID_SOUSCATEGORIE", LSC.get(position).getId() );
                i.putExtra("SOUSCATEGORIE", LSC.get(position).getDesignation() );
                context.startActivity(i);
            }
        });
        
        return convertView;
    }
}
