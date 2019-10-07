package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Notification;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.ManageLocalData;
import cd.maichapayteam.zuajob.Tools.RoundedImageView;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Notifications_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Notification> DATA;

    public Notifications_Base_Adapter(Context context, ArrayList<Notification> DATA) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.view_notification,null);
        TextView time = convertView.findViewById(R.id.time);
        TextView message = convertView.findViewById(R.id.message);
        TextView titre = convertView.findViewById(R.id.titre);
        ImageView img = convertView.findViewById(R.id.img);
        LinearLayout back = convertView.findViewById(R.id.back);

        final Notification N = DATA.get(position);
        titre.setVisibility(View.GONE);
        time.setText(Tool.formatingDate(N.getTime()));
        message.setText(N.getMessage());

        if (N.isReaded() == true){
            back.setBackgroundColor(Color.parseColor("#ffffff"));//
        }else{
            back.setBackgroundColor(Color.parseColor("#D6E6F5"));//
        }

        if (N.getTypeNotification().equals("created") && N.getTypeObject().equals("sollicitation") ){
            img.setImageResource(R.drawable.ic_sollicitation);
        }else{
            img.setImageResource(R.drawable.ic_annonce);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Setreaded(N.getId());
            }
        });

        return convertView;
    }

    private void Setreaded(final long id){
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                ManageLocalData.setNotificationReaded(id);
                return null;
            }
        }.execute();
    }



}
