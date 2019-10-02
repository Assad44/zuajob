package cd.maichapayteam.zuajob.Tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ElikyaLK on 02/10/2019.
 */

public class BrodcastLaunchService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            Intent launchService = new Intent(context, AutoRunService.class);
            context.startService(launchService);
        }
    }
}
