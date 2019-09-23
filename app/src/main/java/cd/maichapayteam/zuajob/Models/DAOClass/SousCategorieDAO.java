package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class SousCategorieDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String DESIGNATION = "designation";
    public static final String DESCRIPTION = "description";
    public static final String URL_IMAGE = "url";
    public static final String ID_CATEGORIE = "idcat";
    public static final String TABLE_NOM = "t_sous_cat";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    DESIGNATION + " TEXT, " +
                    DESCRIPTION + " TEXT, " +
                    URL_IMAGE + " TEXT, " +
                    ID_CATEGORIE + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static SousCategorieDAO instance;

    public SousCategorieDAO(Context pContext) {
        super(pContext);
    }

    public static SousCategorieDAO getInstance(Context ctx){
        if(instance==null) instance = new SousCategorieDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public SousCategorie ajouter(SousCategorie object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(DESCRIPTION, object.getDescription());
                value.put(DESIGNATION, object.getDesignation());
                value.put(URL_IMAGE, object.getUrlImage());
                value.put(ID_CATEGORIE, object.getIdCategorie());
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

    public SousCategorie find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            SousCategorie object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(c.getColumnIndex(KEY));
                String designation=c.getString(c.getColumnIndex(DESIGNATION));
                String description=c.getString(c.getColumnIndex(DESCRIPTION));
                String url=c.getString(c.getColumnIndex(URL_IMAGE));
                long cat=c.getLong(c.getColumnIndex(ID_CATEGORIE));

                object = new SousCategorie();
                object.setId(_id);
                object.setDescription(description);
                object.setDesignation(designation);
                object.setUrlImage(url);
                object.setIdCategorie(cat);
            }
            c.close();
            close();
            return object;
        }catch (Exception e){
            return null;
        }
    }

    public List<SousCategorie> getAll() {
        List<SousCategorie> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM, null);
            while (c.moveToNext()) {
                list.add(find(c.getLong(0)));
            }
            c.close();
            close();
        }catch (Exception e){

        }
        return list;
    }

    public List<SousCategorie> getAll(Categorie categorie) {
        List<SousCategorie> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + ID_CATEGORIE + " = ?", new String[]{String.valueOf(categorie.getId())});
            while (c.moveToNext()) {
                list.add(find(c.getLong(0)));
            }
            c.close();
            close();
        }catch (Exception e){

        }
        return list;
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

    public long modifier(SousCategorie object) {
        ContentValues value = new ContentValues();
        value.put(DESCRIPTION, object.getDescription());
        value.put(DESIGNATION, object.getDesignation());
        value.put(URL_IMAGE, object.getUrlImage());
        value.put(ID_CATEGORIE, object.getIdCategorie());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}
