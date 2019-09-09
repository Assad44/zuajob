package cd.maichapayteam.zuajob.BackEnd.SQLite_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cd.maichapayteam.zuajob.Tools.Object.Users;

/**
 * Created by Deon-Mass on 24/02/2018.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final String db_name = "ZuajobApp.db";
    public static final int db_version = 1;

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(Users.create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE "+Users.table_name+" ;");
        }catch (Exception e){
            e.printStackTrace();
        }
        onCreate(db);
    }

}
