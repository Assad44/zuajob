package cd.maichapayteam.zuajob.Models;

import android.app.Application;
import android.content.Context;
import android.support.design.widget.AppBarLayout;

import com.activeandroid.ActiveAndroid;

import cd.maichapayteam.zuajob.Tools.GeneralClass;

public class Main extends Application {

    public Main() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GeneralClass.applicationContext = this;
        //ActiveAndroid.initialize(this);
    }

}
