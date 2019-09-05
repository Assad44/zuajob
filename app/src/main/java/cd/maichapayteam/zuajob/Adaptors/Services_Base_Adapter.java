package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

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

        Service S = DATA.get(position);



        return convertView;
    }
}
