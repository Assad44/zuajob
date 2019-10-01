package cd.maichapayteam.zuajob.Front_end.Notification_result_classes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;

import cd.maichapayteam.zuajob.Front_end.Mines.Mes_services_sollicites;
import cd.maichapayteam.zuajob.R;
import cd.maichapayteam.zuajob.Tools.Tool;

/**
 * Created by Deon_Massadi on 23/11/2018.
 */

public class BroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent i) {
        //Toast.makeText(context, "I receive something", Toast.LENGTH_SHORT).show();
        //Log.i("WWWWWWWWWWWWWWWWW", "I receive something");

        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info !=null && info.isConnected()){
            //Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            //Log.i("WWWWWWWWWWWWWWWWW", "COnnected");
            if (Tool.getUserPreferences(context,"notification").equals("1")){
                Notify_sollicitations(context);
            }

        }else{
            //Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show();
            //Log.e("WWWWWWWWWWWWWWWWW", "NO connection");
        }


    }


    private void Notify_sollicitations(Context context){
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.baccc)).build();


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent returnresult = new Intent(context, Mes_services_sollicites.class);
        returnresult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,(int) Calendar.getInstance().getTimeInMillis(),
                returnresult,0);


        // Build notification
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSound(uri)
                .setSmallIcon(R.drawable.ic_annonce)
                .setContentTitle("Nouvelle sollicitation")
                .setContentText(context.getResources().getString(R.string.Lorem_short))
                .setStyle(bigPictureStyle)
                .addAction(R.drawable.ic_annonce, "Voir les détail",pendingIntent);

        notificationManager.notify(0, builder.build());
    }



}
