package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Jobeur_Base_Adapter extends BaseAdapter {
    Context context;
    List<User> DATA;

    public Jobeur_Base_Adapter(Context context, List<User> DATA) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.model_jobeurs,null);

        ImageView avatar = convertView.findViewById(R.id.avatar);
        TextView nom = convertView.findViewById(R.id.nom);
        TextView desc = convertView.findViewById(R.id.desc);
        TextView age = convertView.findViewById(R.id.age);
        RatingBar MyRating = convertView.findViewById(R.id.MyRating);

        User S = DATA.get(position);

        nom.setText(S.getNom());
        age.setText(S.getBirthday());
        MyRating.setRating(new Random().nextInt(5));

        /*int cote = S.getc;
        int real = S.getNombreRealisation()*10;
        if (real == 0) real = 1;
        float rating = cote * 5 / real;
        MyRating.setRating(rating);*/

        int profil = 0;
        if (position%3 == 0)
            profil = R.drawable.avatar3;
        else{
            profil = R.drawable.avatar2;
        }
        avatar.setImageResource(profil);

        return convertView;
    }
}
