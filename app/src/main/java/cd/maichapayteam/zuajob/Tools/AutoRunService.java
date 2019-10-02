package cd.maichapayteam.zuajob.Tools;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Notification;

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
                for (Notification noti : listNotification) traiteNotif(noti);
            } catch (InterruptedException e) {
                Log.e("AutoRunService", "runnable breaked");
                break;
            }
        }
    }

    private void traiteNotif(Notification noti){
        //if(noti.getActionSur().equals(ACTION_SUR_COMMENT)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    notificationDAO.ajouter(noti);
        //    remoteData.getComment(noti.getIdAction());
        //    if(noti.getUser()>0) notifier(noti, "Nouveau commentaire");
        //}else if(noti.getActionSur().equals(ACTION_SUR_DISTRIBUTION)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    notificationDAO.ajouter(noti);
        //    remoteData.getParticipation(noti.getIdAction());
        //    if(noti.getAction().equals(ACTION_ADD)) {
        //        if(noti.getUser()>0) notifier(noti, "Nouveau distributeur");
        //    } else if(noti.getAction().equals(ACTION_DELETE)) {
        //        ParticipantDAO.getInstance(this).supprimer(noti.getIdAction());
        //        if(noti.getUser()>0) notifier(noti, "Distributeur supprimée");
        //    } else {
        //        if(noti.getUser()>0) notifier(noti, "Distribution modifiée");
        //    }
        //}else if(noti.getActionSur().equals(ACTION_SUR_EVENT)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    notificationDAO.ajouter(noti);
        //    remoteData.getEvent(noti.getIdAction());
        //    if(noti.getAction().equals(ACTION_ADD)) {
        //        if(noti.getUser()>0) notifier(noti, "Nouvel événément.");
        //    } else if(noti.getAction().equals(ACTION_DELETE)) {
        //        eventsDAO.supprimer(noti.getIdAction());
        //    } else {
        //        if(noti.getUser()>0) notifier(noti, "Evénément modifié");
        //    }
        //}else if(noti.getActionSur().equals(ACTION_SUR_FOLLOW_EVENT)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    notificationDAO.ajouter(noti);
        //    remoteData.getEvent(noti.getIdConcerne());
        //    remoteData.getUser(noti.getIdAction());
        //    if(noti.getAction().equals(ACTION_ADD)) {
        //        if(noti.getUser()>0) notifier(noti, "Nouveau follower");
        //    }
        //}else if(noti.getActionSur().equals(ACTION_SUR_FOLLOW_USER)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    notificationDAO.ajouter(noti);
        //    remoteData.getUser(noti.getIdConcerne());
        //    remoteData.getUser(noti.getIdAction());
        //    if(noti.getAction().equals(ACTION_ADD)) {
        //        if(noti.getUser()>0) notifier(noti, "Nouveau follower");
        //    }
        //}else if(noti.getActionSur().equals(ACTION_SUR_LIKE)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    notificationDAO.ajouter(noti);
        //    remoteData.getEvent(noti.getIdConcerne());
        //    if(noti.getAction().equals(ACTION_ADD)) {
        //        if(noti.getUser()>0) notifier(noti, "Nouveau like");
        //    }
        //}else if(noti.getActionSur().equals(ACTION_SUR_MESSAGE)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    try{ mNewMessageListener.onNewMessage(); }catch (Exception ex){ Log.e("AutoRunService", "onNewMessageListener error - " + ex.getMessage()); }
        //    Message smg = remoteData.getMessage(noti.getIdAction());
        //    if(!smg.getError()) {
        //        noti.setIdConcerne(smg.getId());
        //        noti.setIdAction(smg.getId());
        //        notificationDAO.ajouter(noti);
        //    }
        //    notifier(noti, "Nouveau message");
        //}else if(noti.getActionSur().equals(ACTION_SUR_PARTICIPATION)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    notificationDAO.ajouter(noti);
        //    remoteData.getParticipation(noti.getIdAction());
        //    if(noti.getAction().equals(ACTION_ADD)) {
        //        if(noti.getUser()>0) notifier(noti, "Nouvelle participation");
        //    } else if(noti.getAction().equals(ACTION_DELETE)) {
        //        ParticipantDAO.getInstance(this).supprimer(noti.getIdAction());
        //        if(noti.getUser()>0) notifier(noti, "Participation supprimée");
        //    } else {
        //        if(noti.getUser()>0) notifier(noti, "Participation modifiée");
        //    }
        //}else if(noti.getActionSur().equals(ACTION_SUR_PLACE)){
        //    remoteData.getPlace(noti.getIdAction());
        //    if(noti.getAction().equals(ACTION_DELETE)) {
        //        PlaceDAO.getInstance(this).supprimer(noti.getIdAction());
        //    }
        //}else if(noti.getActionSur().equals(ACTION_SUR_PRIORITE)){
        //    remoteData.getPriority(noti.getIdAction());
        //}else if(noti.getActionSur().equals(ACTION_SUR_SIEGE)){
        //    remoteData.getPlace(noti.getIdAction());
        //    if(noti.getAction().equals(ACTION_DELETE)) {
        //        SiegeDAO.getInstance(this).supprimer(noti.getIdAction());
        //    }
        //}else if(noti.getActionSur().equals(ACTION_SUR_VIEW)){
        //    try{ mNewNotificationListener.onNewNotification(noti); }catch (Exception ex){ }
        //    notificationDAO.ajouter(noti);
        //    remoteData.getEvent(noti.getIdConcerne());
        //    if(noti.getAction().equals(ACTION_ADD)) {
        //        if(noti.getUser()>0) notifier(noti, "Nouveau vue");
        //    }
        //}
    }

    private void notifier(Notification notif, String titre){
        try{
            android.app.Notification.Builder nb = new android.app.Notification.Builder(this);
            //int icon = R.mipmap.doxa_event_logo;
            //String text = notif.getTexte();
            //text = text.replace("</b>", "");
            //text = text.replace("<b>", "");
            //text = text.replace("</li>", "");
            //text = text.replace("<li>", "");
            //text = text.replace("</ul>", "");
            //text = text.replace("<ul>", "");
            //text = text.replace("</br>", "");
            //long time = notif.getTemps() * 1000;
            //nb.setContentText(text);
            //nb.setContentTitle(titre);
            //nb.setSmallIcon(icon);
            //nb.setWhen(time);
            //Intent notificationIntent;
            //notificationIntent = new Intent(this, NotificationActivity.class);
            //notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            //nb.setContentIntent(contentIntent);
            //nb.setLights(Color.BLUE, 500, 500);
            //nb.setVibrate(new long[]{0,50,100,150,200,250,300,350,400,450,500});
            //nb.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
            //android.app.Notification notification = nb.getNotification();
            //NotificationManager manager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
            //manager.notify(NOTIFICATION_ID, notification);
        }catch (Exception ex){

        }
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
