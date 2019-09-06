package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Cote;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Services_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Service> DATA;

    public Services_Base_Adapter(Context context, ArrayList<Service> DATA) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.modele_list_test,null);
        TextView S_categorie = convertView.findViewById(R.id.S_categorie);
        TextView S_descriptions = convertView.findViewById(R.id.S_descriptions);
        TextView S_prix = convertView.findViewById(R.id.S_prix);
        TextView realisation = convertView.findViewById(R.id.realisation);
        RatingBar Rating = convertView.findViewById(R.id.MyRating);

        if (DATA == null )
            Toast.makeText(context, "Aucune donnée", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(context, "Taile "+ DATA.size(), Toast.LENGTH_SHORT).show();
        }

        Service S = DATA.get(position);

        // todo : Affects values to the componants
        S_categorie.setText(S.sousCategorie.designation);
        S_descriptions.setText(S.description);
        S_prix.setText(String.valueOf(S.montant));
        realisation.setText(String.valueOf(S.nombreRealisation));

        int cote = 0;
        for (Cote c : S.cotes ) {
            cote += c.cote;
        }
        float rating = cote * 5 / (S.cotes.size()*10);
        Rating.setRating(rating);

        return convertView;
    }
}
