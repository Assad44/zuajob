package cd.maichapayteam.zuajob.Models;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;

import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.Tools.GeneralClass;
import cd.maichapayteam.zuajob.Tools.RemoteDataSync;

public class Main extends Application {

    public Main() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        GeneralClass.applicationContext = this;

        User u = new UserDAO(this).myProfil();
        if (null != u) GeneralClass.Currentuser = u;
    }

}
