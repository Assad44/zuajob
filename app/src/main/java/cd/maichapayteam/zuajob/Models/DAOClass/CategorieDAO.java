package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.User;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class CategorieDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String DESIGNATION = "designation";
    public static final String DESCRIPTION = "description";
    public static final String URL_IMAGE = "url_image";
    public static final String TABLE_NOM = "t_cat";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    DESIGNATION + " TEXT, " +
                    DESCRIPTION + " TEXT, " +
                    URL_IMAGE + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static CategorieDAO instance;

    public CategorieDAO(Context pContext) {
        super(pContext);
    }

    public static CategorieDAO getInstance(Context ctx){
        if(instance==null) instance = new CategorieDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public Categorie ajouter(Categorie object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(DESCRIPTION, object.getDescription());
                value.put(DESIGNATION, object.getDesignation());
                value.put(URL_IMAGE, object.getUrlImage());
                open();
                long retour = mDb.insert(TABLE_NOM, null, value);
                close();
                return find(retour);
            }else{
                modifier(object);
                return find(object.getId());
            }
        }catch (Exception e){
            return null;
        }
    }

    public long count(){
        try{
            open();
            Cursor c = mDb.rawQuery("select count(*) from " + TABLE_NOM, null);
            long co = 0;
            while (c.moveToNext()) {
                co = c.getLong(0);
            }
            c.close();
            close();
            return co;
        }catch (Exception e){
            return 0;
        }
    }

    public Categorie find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            Categorie object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(0);
                String designation=c.getString(1);
                String description=c.getString(2);
                String url=c.getString(3);

                object = new Categorie();
                object.setId(_id);
                object.setDescription(description);
                object.setDesignation(designation);
                object.setUrlImage(url);
            }
            c.close();
            close();
            return object;
        }catch (Exception e){
            return null;
        }
    }

    public long supprimer(long id) {
        open();
        long rep = mDb.delete(TABLE_NOM, KEY + " = ?", new String[]{String.valueOf(id)});
        close();
        return rep;
    }

    public long supprimer() {
        open();
        long rep = mDb.delete(TABLE_NOM, null, null);
        close();
        return rep;
    }

    public long modifier(Categorie object) {
        ContentValues value = new ContentValues();
        value.put(DESCRIPTION, object.getDescription());
        value.put(DESIGNATION, object.getDesignation());
        value.put(URL_IMAGE, object.getUrlImage());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}
