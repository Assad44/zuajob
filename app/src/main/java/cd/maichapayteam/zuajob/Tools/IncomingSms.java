package cd.maichapayteam.zuajob.Tools;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class IncomingSms extends BroadcastReceiver {
    private ZuaJobMessageListener mZuaJobMessageListener;
    Activity activity;

    private static int MY_PERMISSIONS_REQUEST = 6362;
    private String numero = "";

    public IncomingSms(Activity activity, ZuaJobMessageListener zuaJobMessageListener, String numero) {
        super();
        this.activity = activity;
        mZuaJobMessageListener = zuaJobMessageListener;
        this.numero = numero;
        permission(activity);
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(1912331391);
        activity.registerReceiver(this, filter);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void permission(Activity activity) {
        if (activity.checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.BROADCAST_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS};
            activity.requestPermissions(permissions, MY_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();
                    //if(mZuaJobMessageListener!=null) mZuaJobMessageListener.OnNewMessage(message);
                    if(phoneNumber.equals("ZuaJob")) {
                        if(message.startsWith("Votre code de confirmation ZuaJob est : ")) {
                            String[] tab = message.split(":");
                            if(tab.length==2) {
                                String code = tab[1].trim();
                                if(code.length()==4) {
                                    ConfirmCodeRunnable confirmCodeRunnable = new ConfirmCodeRunnable(this.numero, code);
                                    confirmCodeRunnable.execute();
                                }
                            }
                        }
                    }

                } // end for loop
            } // bundle is null

        } catch (Exception e) {

        }
    }

    private class ConfirmCodeRunnable extends AsyncTask<String, String, Boolean> {

        String numero = "";
        String code = "";

        ConfirmCodeRunnable(String num, String cod) {
            this.numero = num;
            this.code = cod;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return RemoteDataSync.confirmCode(numero, code);
        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
            if(mZuaJobMessageListener!=null) {
                if(s) {
                    mZuaJobMessageListener.OnCorrectConfirmationCode();
                } else {
                    mZuaJobMessageListener.OnIncorrectConfirmationCode();
                }
            }
        }
    }

    public interface ZuaJobMessageListener {
        void OnCorrectConfirmationCode();
        void OnIncorrectConfirmationCode();
        void OnNewMessage(String message);
    }

}
