package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;
import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Postullants_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Postuler> DATA;

    public Postullants_Base_Adapter(Context context, ArrayList<Postuler> DATA) {
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
        convertView  = LayoutInflater.from(context).inflate(R.layout.view_postullants,null);
        TextView nom_user = convertView.findViewById(R.id.nom_user);
        TextView nom_number = convertView.findViewById(R.id.nom_number);
        TextView confier = convertView.findViewById(R.id.confier);
        ImageView avatar = convertView.findViewById(R.id.avatar);

        final Postuler u = DATA.get(position);
        nom_user.setText(u.getNomsUser());
        nom_number.setText("+"+u.getPhoneUser());
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
                //detail_jobeur(u);
            }
        });
        confier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRDV(u);

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

    private void setRDV(final Postuler u){
        View convertView  = LayoutInflater.from(context).inflate(R.layout.view_edit_setting_rdv,null);
        LinearLayout date_zone = convertView.findViewById(R.id.date_zone);
        final EditText date = convertView.findViewById(R.id.date);
        LinearLayout heure_zone = convertView.findViewById(R.id.heure_zone);
        final EditText heure = convertView.findViewById(R.id.heure);
        final EditText note = convertView.findViewById(R.id.note);
        final EditText montant = convertView.findViewById(R.id.montant);
        final Spinner devise= convertView.findViewById(R.id.deviseAnnonce);
        TextView submit= convertView.findViewById(R.id.submit);
        TextView cancel = convertView.findViewById(R.id.cancel);



        date_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.Date_Picker(context, date);
            }
        });
        heure_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.Time_Picker(context, heure);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(date.getText().toString())){
                    date.setError("Veuillez renseignez la date du rendez-vous");
                    return;
                }else if (TextUtils.isEmpty(heure.getText().toString())){
                    heure.setError("Veuillez renseignez l'heure du rendez-vous");
                    return;
                }else if (TextUtils.isEmpty(montant.getText().toString())){
                    montant.setError("Veuillez renseignez le montant convenu");
                    return;
                }

                new AsyncTask<Void, Void, Postuler>() {
                    View convertView  = LayoutInflater.from(context).inflate(R.layout.view_progressebar,null);
                    TextView write_response = convertView.findViewById(R.id.write_response);
                    AlertDialog.Builder a = new AlertDialog.Builder(context)
                            .setView(convertView)
                            .setCancelable(false);
                    // Setting dialogview
                    final AlertDialog alert = a.create();

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        write_response.setText("Opération encours...");
                        alert.show();
                    }

                    @Override
                    protected Postuler doInBackground(Void... voids) {
                        return ManageLocalData.creerRDVbyUser(
                                u.getId(),
                                date.getText().toString(),
                                heure.getText().toString(),
                                note.getText().toString().replace("'","''"),
                                Integer.parseInt(montant.getText().toString()),
                                devise.getSelectedItem().toString(),
                                "1234"
                        );
                    }

                    @Override
                    protected void onPostExecute(Postuler service) {
                        alert.cancel();
                        AlertDialog.Builder a = new AlertDialog.Builder(context)
                                .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        if (service.isError() == true ){
                            a.setMessage(service.getErrorMessage()+ " "+service.getErrorCode());
                        }else{
                            a.setMessage("Opération réussi");

                        }
                        a.show();
                    }
                }.execute();

            }
        });


        AlertDialog.Builder a = new AlertDialog.Builder(context)
                .setView(convertView)
                .setCancelable(false);
        final AlertDialog alert = a.create();
        alert.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });
    }



}
