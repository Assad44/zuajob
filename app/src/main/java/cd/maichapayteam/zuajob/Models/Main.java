package cd.maichapayteam.zuajob.Models;

import android.app.Application;

import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.Tools.GeneralClass;

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
