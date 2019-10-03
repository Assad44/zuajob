package cd.maichapayteam.zuajob.Adaptors;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Back_end.Objects.O_Categories;
import cd.maichapayteam.zuajob.R;

/**
 * Created by Deon-Mass on 08/02/2018.
 */
public class Categorie2_Base_Adapter extends BaseAdapter {
    Context context;
    ArrayList<O_Categories> DATAS;

    public Categorie2_Base_Adapter(Context context, ArrayList<O_Categories> DATAS) {
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

        O_Categories C = DATAS.get(position);
        categorie.setText(C.getDesignation_categorie());
        description.setText(C.getDescription_categorie());
        Load_image(backImage, C.getUrlImage_categorie());

        return convertView;
    }


    private void  Load_image(final ImageView backImage, String url){
        /*Ion.with(backImage)
                .placeholder(R.drawable.avatar)
                .error(R.mipmap.ic_url_error)
                .animateGif(AnimateGifMode.ANIMATE)
                .load(url);*/

        AndroidNetworking.get(url)
                .setTag("imageRequestTag")
                .setPriority(Priority.MEDIUM)
                .setBitmapMaxHeight(100)
                .setBitmapMaxWidth(100)
                .setBitmapConfig(Bitmap.Config.ARGB_8888)
                .build()
                .getAsBitmap(new BitmapRequestListener() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        backImage.setImageBitmap(bitmap);
                    }
                    @Override
                    public void onError(ANError error) {
                        backImage.setImageResource(R.mipmap.ic_url_error);
                        Log.e("DDDDDDDDDD", error.getMessage());
                    }
                });


    }

}
