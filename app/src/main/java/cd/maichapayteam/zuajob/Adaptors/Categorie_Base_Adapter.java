package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Categorie_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Categorie> DATAS;

    public Categorie_Base_Adapter(Context context, ArrayList<Categorie> DATAS) {
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
        TextView categorie = convertView.findViewById(R.id.categorie);
        TextView description = convertView.findViewById(R.id.description);
        ImageView backImage = convertView.findViewById(R.id.backImage);

        Categorie C = DATAS.get(position);
        categorie.setText(C.getDesignation());
        description.setText(C.getDescription());


        return convertView;
    }


    private void LoadI(){

    }

}
