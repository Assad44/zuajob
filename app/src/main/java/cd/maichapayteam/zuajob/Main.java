package cd.maichapayteam.zuajob;

import android.app.Application;
import android.content.Context;
import android.support.design.widget.AppBarLayout;

import com.activeandroid.ActiveAndroid;

public class Main extends Application {

    public Main() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

}
