package cd.maichapayteam.zuajob.Front_end.Notification_result_classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cd.maichapayteam.zuajob.Tools.AutoRunService;

/**
 * Created by Deon_Massadi on 23/11/2018.
 */

public class BrodcastLaunchService extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (context != null && intent != null) {
            if (info !=null && info.isConnected() && !AutoRunService.existInstance()){
                Intent launchService = new Intent(context, AutoRunService.class);
                context.startService(launchService);
            }
        }

    }

}
