package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Rdv_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Sollicitation> SOLLICITATION;


    public Rdv_Base_Adapter(Context context, ArrayList<Sollicitation> SOLLICITATION) {
        this.context = context;
        this.SOLLICITATION = SOLLICITATION;
    }

    @Override
    public int getCount() {
        return SOLLICITATION.size();
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
        convertView  = LayoutInflater.from(context).inflate(R.layout.view_list_postullants,null);
        final TextView count = convertView.findViewById(R.id.count);
        final TextView confier = convertView.findViewById(R.id.confier);
        final GridView list= convertView.findViewById(R.id.list);
        final SwipeRefreshLayout swipper= convertView.findViewById(R.id.swipper);

        confier.setText("Liste de sollicitants");
        count.setText(list.getCount()+" Sollicitants");
        return convertView;
    }


}
