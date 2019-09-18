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
    private long id = -1;

    public IncomingSms(Activity activity, ZuaJobMessageListener zuaJobMessageListener, long id) {
        super();
        this.activity = activity;
        mZuaJobMessageListener = zuaJobMessageListener;
        this.id = id;
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(1912331391);
        activity.registerReceiver(this, filter);
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
                                    ConfirmCodeRunnable confirmCodeRunnable = new ConfirmCodeRunnable(this.id, code);
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

        long id = -1;
        String code = "";

        ConfirmCodeRunnable(long id, String code) {
            this.id = id;
            this.code = code;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return RemoteDataSync.confirmCode(id, code);
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

    public void destroy() {
        activity.unregisterReceiver(this);
    }

}
