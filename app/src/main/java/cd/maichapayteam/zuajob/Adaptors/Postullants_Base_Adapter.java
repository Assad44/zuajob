package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Postullants_Base_Adapter extends BaseAdapter {
    Context context;
    String id_annonce;

    public Postullants_Base_Adapter(Context context, String id_annonce) {
        this.context = context;
        this.id_annonce = id_annonce;
    }
    @Override
    public int getCount() {
        return 20;
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
        convertView  = LayoutInflater.from(context).inflate(R.layout.view_postullants,null);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView nom_number = convertView.findViewById(R.id.nom_number);
        TextView confier = convertView.findViewById(R.id.confier);
        ImageView avatar = convertView.findViewById(R.id.avatar);

        final User u = GeneralClass.Currentuser;
        nom_user.setText(u.getNom() +" "+u.getPrenom());
        nom_number.setText("+"+u.getPhone());
        // TODO  LOAD IMAGE
        try {
            GifDrawable gifFromResource = new GifDrawable( context.getResources(), R.drawable.gif4);
            Ion.with(avatar)
                    .placeholder(gifFromResource)
                    .error(R.drawable.avatar_error)
                    .animateGif(AnimateGifMode.ANIMATE)
                    .load("http://hotprintdesign.com/wp-content/uploads/2019/02/no-profile-photo.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                detail_jobeur(u);
            }
        });
        confier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRDV();
                Toast.makeText(context, id_annonce, Toast.LENGTH_SHORT).show();

            }
        });

        return convertView;
    }

    private void detail_jobeur(@NotNull User u){
        View view  = LayoutInflater.from(context).inflate(R.layout.view_jobeurs_details2,null);
        TextView nom = view.findViewById(R.id.nom);
        TextView number = view.findViewById(R.id.number);
        TextView realisation = view.findViewById(R.id.realisation);

        nom.setText(u.getNom() +" "+u.getPrenom());
        number.setText(u.getPhone());
        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = a.create();
        alert.show();
    }

    private void setRDV(){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_edit_setting_rdv,null);
        LinearLayout date_zone = convertView.findViewById(R.id.date_zone);
        EditText date = convertView.findViewById(R.id.date);
        LinearLayout heure_zone = convertView.findViewById(R.id.heure_zone);
        EditText heure = convertView.findViewById(R.id.heure);
        EditText note = convertView.findViewById(R.id.note);

        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(false)
                .setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = a.create();
        alert.show();




    }



}
