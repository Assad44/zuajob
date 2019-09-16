package cd.maichapayteam.zuajob.Back_end.Offline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static cd.maichapayteam.zuajob.Back_end.Objects.O_Annonces.*;
import static cd.maichapayteam.zuajob.Back_end.Objects.O_Categories.*;
import static cd.maichapayteam.zuajob.Back_end.Objects.O_Services.*;
import static cd.maichapayteam.zuajob.Back_end.Objects.O_Sous_Categories.*;

import cd.maichapayteam.zuajob.Back_end.Config;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Annonces;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Categories;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Services;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Sous_Categories;


/**
 * Created by Deon-Mass on 24/02/2018.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final String db_name = Config.db_name;
    public static final int db_version = 1;

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(Tables_queries_methods.create_table_Annonces());
       db.execSQL(Tables_queries_methods.create_table_Services());
       db.execSQL(Tables_queries_methods.create_table_Categorie());
       db.execSQL(Tables_queries_methods.create_table_Souscategorie());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE "+new O_Annonces().table_name_annonces+" ;");
            db.execSQL("DROP TABLE "+new O_Services().table_name_services+" ;");
            db.execSQL("DROP TABLE "+new O_Categories().table_name_categorie+" ;");
            db.execSQL("DROP TABLE "+new O_Sous_Categories().table_name_souscategorie+" ;");
        }catch (Exception e){
            e.printStackTrace();
        }
        onCreate(db);
    }

}
