package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Didier LEMENDE on 31/01/2018.
 */
public class DAOBase {

    protected final static int VERSION = 1;
    protected final static String NOM = "zuajob.db";
    protected SQLiteDatabase mDb = null;
    protected BaseDeDonnees mHandler = null;

    public DAOBase(Context pContext) {
        this.mHandler = BaseDeDonnees.getInstance(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }

}
