package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

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
        TextView phone = convertView.findViewById(R.id.phone);
        TextView age = convertView.findViewById(R.id.age);
        RatingBar MyRating = convertView.findViewById(R.id.MyRating);

        User S = DATA.get(position);

        nom.setText(S.getNom());
        phone.setText(S.getPhone());

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        try {
            Date d1 = new Date(sdf1.parse(S.getBirthday()).getTime());
            int old = Integer.parseInt(sdf2.format(new Date())) - Integer.parseInt(sdf2.format(d1));
            age.setText(String.valueOf(old).concat(" an(s)"));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        /*int cote = S.getCote();
        int real = S.getNombreRealisation()*10;
        if (real == 0) real = 1;
        float rating = cote * 5 / real;*/
        MyRating.setRating(S.getCote());

        Tool.Load_Image(context,avatar,S.getUrlPhoto());

        return convertView;
    }

}
