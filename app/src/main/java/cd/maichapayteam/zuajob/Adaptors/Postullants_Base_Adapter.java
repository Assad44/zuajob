package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cd.maichapayteam.zuajob.R;

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
        TextView confier = convertView.findViewById(R.id.confier);
        ImageView avatar = convertView.findViewById(R.id.avatar);

        confier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, id_annonce, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


}
