package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Didier LEMENDE on 31/01/2018.
 */
public class BaseDeDonnees  extends SQLiteOpenHelper {

    private Context cnt = null;
    private static BaseDeDonnees instance;

    private BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        cnt = context;
    }

    public static BaseDeDonnees getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if(instance==null) instance = new BaseDeDonnees(context, name, factory, version);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(UserDAO.TABLE_CREATE);
        }catch (Exception ex){
            Toast.makeText(cnt, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL(UserDAO.TABLE_DROP);
            onCreate(db);
        }catch (Exception ex){
            Toast.makeText(cnt, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

}
