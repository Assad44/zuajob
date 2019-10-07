package cd.maichapayteam.zuajob.Tools;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;

import cd.maichapayteam.zuajob.Front_end.Notification_result_classes.Results;
import cd.maichapayteam.zuajob.Models.Object.Notification;
import cd.maichapayteam.zuajob.R;

/**
 * Created by ElikyaLK on 02/10/2019.
 */

public class AutoRunService extends Service implements Runnable {

    public static int NOTIFICATION_ID = 81232;

    private static Thread threadUser =null;
    private NewNotificationListener mNewNotificationListener;
    private NewMessageListener mNewMessageListener;

    private static AutoRunService instance;

    public AutoRunService() {
        super();
        Log.e("AutoRunService", "creating new AutoRunService class");
        instance = this;
    }

    public static AutoRunService getInstance(){
        if(instance==null) {
            new AutoRunService();
            Log.e("AutoRunService", "new instance created");
        }
        Log.e("AutoRunService", "instance returned");
        return instance;
    }

    public static boolean existInstance(){
        if(instance==null) return false;
        return true;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate () {
        Log.e("AutoRunService", "onCreate called");
    }

    @Override
    public void onDestroy() {
        Log.e("AutoRunService", "onDestroy called");
        instance = null;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("AutoRunService", "onStartCommand called");
        threadUser = new Thread(this);
        threadUser.start();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void run() {
        Log.e("AutoRunService", "runnable started");
        while (true){
            try {
                Thread.sleep(20000);
                List<Notification> listNotification = RemoteDataSync.getMesNotifications();
                Log.e("AutoRunService", "notification count: " + listNotification.size());
                for (Notification noti : listNotification) {
                    Log.e("AutoRunService", "notification" + noti.getId() + ": " + noti.getMessage());
                    traiteNotif(noti);
                }
            } catch (InterruptedException e) {
                Log.e("AutoRunService", "runnable breaked");
                //break;
            }
        }
    }

    private void traiteNotif(Notification noti){
        String titre = "";
        boolean notifier = true;

        if(noti.getTypeNotification().equals("rdv_created")) {
            titre = "Vous avez un nouveau Rendez-vous";
        } else if(noti.getTypeNotification().equals("refused")) {
            titre = "Un de vos Rendez-vous a été réfusé";
        } else if(noti.getTypeNotification().equals("rdv_accepted")) {
            titre = "Un de vos Rendez-vous a été accepté";
        } else if(noti.getTypeNotification().equals("created")) {
            if(noti.getTypeObject().equals("postulance")) {
                titre = "Vous avez un nouveau postulant";
            } else {
                titre = "Vous avez une nouvelle sollicitation";
            }
        } else if(noti.getTypeNotification().equals("rended")) {
            titre = "Un utilisateur vient de confirmer que vous lui avez rendu service";
        } else if(noti.getTypeNotification().equals("del_confied")) {
            notifier = false;
        } else if(noti.getTypeNotification().equals("confied")) {
            notifier = false;
        }

        try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        if(notifier) notifier(noti, titre);
    }

    private void notifier(Notification notif, String titre){
        if(Tool.getUserPreferences(this,"notification").equals("0")) return;
        int icon = R.drawable.ic_annonce;
        long time = System.currentTimeMillis();

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.baccc)).build();

        Intent notificationIntent;
        notificationIntent = new Intent(this, Results.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Build notification
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSound(uri)
                .setWhen(time)
                //.setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setSmallIcon(icon)
                .setContentTitle(titre)
                .setContentText(notif.getMessage())
                //.setStyle(bigPictureStyle)
                .setContentIntent(contentIntent)
                .setLights(Color.BLUE, 500, 500)
                .setVibrate(new long[]{0,50,100,150,200,250,300,350,400,450,500})
                .addAction(R.drawable.ic_annonce, "Ouvrir",contentIntent);

        android.app.Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, notification);

    }

    public void setOnNewMessageListener(NewMessageListener newMessageListener){
        mNewMessageListener = newMessageListener;
    }

    public void setOnNewNotificationListener(NewNotificationListener newNotificationListener){
        mNewNotificationListener = newNotificationListener;
    }

    public interface NewMessageListener{
        void onNewMessage();
    }

    public interface NewNotificationListener{
        void onNewNotification(Notification notification);
    }

}
