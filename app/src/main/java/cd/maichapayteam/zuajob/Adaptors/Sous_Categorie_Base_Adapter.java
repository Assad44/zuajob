package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import cd.maichapayteam.zuajob.BackEnd.Objects.Categories;
import cd.maichapayteam.zuajob.BackEnd.Objects.Sous_Categories;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Sous_Categorie_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Sous_Categories> DATAS;

    public Sous_Categorie_Base_Adapter(Context context, ArrayList<Sous_Categories> DATAS) {
        this.context = context;
        this.DATAS = DATAS;
    }

    @Override
    public int getCount() {
        return DATAS.size();
    }

    @Override
    public Object getItem(int position) {
        return DATAS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.model_cat,null);
        ImageView profil = convertView.findViewById(R.id.profil);
        TextView nom = convertView.findViewById(R.id.nom);
        TextView description = convertView.findViewById(R.id.description_service);
        RatingBar Rating = convertView.findViewById(R.id.Rating);

        Sous_Categories C = DATAS.get(position);
        nom.setText(C.getTitle());
        description.setText(C.getDescription());
        Rating.setRating(new Random().nextInt(5));

        return convertView;
    }
}
