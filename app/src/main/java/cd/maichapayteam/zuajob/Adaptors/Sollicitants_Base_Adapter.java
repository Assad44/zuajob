package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import cd.maichapayteam.zuajob.Front_end.Mines.Mes_annonces;
import cd.maichapayteam.zuajob.Front_end.Mines.Mes_services;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.Tool;
import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Sollicitants_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Sollicitation> DATA;

    public Sollicitants_Base_Adapter(Context context, ArrayList<Sollicitation> DATA) {
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

        confier.setText("Accepter");

        final Sollicitation u = DATA.get(position);
        nom_user.setText(u.getNomsUser());
        nom_number.setText("+"+u.getPhoneUser());
        // TODO  LOAD IMAGE
        Tool.Load_Image2(context, avatar, u.getUrlImageUser());

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

    private void setRDV(final Sollicitation u){
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

                new AsyncTask<Void, Void, Sollicitation>() {
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
                    protected Sollicitation doInBackground(Void... voids) {
                        return ManageLocalData.creerRDVbyJobeur(
                                u.getId(),
                                date.getText().toString(),
                                heure.getText().toString(),
                                note.getText().toString().replace("'","''"),
                                Integer.parseInt(montant.getText().toString()),
                                devise.getSelectedItem().toString()
                        );
                    }

                    @Override
                    protected void onPostExecute(Sollicitation service) {
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
                            context.startActivity(new Intent(context, Mes_services.class));
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
