package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.User;
import cd.maichapayteam.zuajob.Tools.GeneralClass;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class CategorieDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String DESIGNATION = "designation";
    public static final String DESCRIPTION = "description";
    public static final String URL_IMAGE = "url_image";
    public static final String USER_PREFERENCE = "userpref";
    public static final String TABLE_NOM = "t_cat";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    DESIGNATION + " TEXT, " +
                    DESCRIPTION + " TEXT, " +
                    URL_IMAGE + " TEXT, " +
                    USER_PREFERENCE + " INTEGER);";

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
                value.put(USER_PREFERENCE, object.isUserPreference());
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
                int up=c.getInt(4);

                object = new Categorie();
                object.setId(_id);
                object.setDescription(description);
                object.setDesignation(designation);
                object.setUrlImage(url);
                if(up==1) object.setUserPreference(true);
            }
            c.close();
            close();
            return object;
        }catch (Exception e){
            return null;
        }
    }

    public List<Categorie> getAll() {
        List<Categorie> list = new ArrayList<>();
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

    public List<Categorie> getAllUserPreference() {
        List<Categorie> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + USER_PREFERENCE + " = 1", null);
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

    public long modifier(Categorie object) {
        ContentValues value = new ContentValues();
        value.put(DESCRIPTION, object.getDescription());
        value.put(DESIGNATION, object.getDesignation());
        value.put(URL_IMAGE, object.getUrlImage());
        value.put(USER_PREFERENCE, object.isUserPreference());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

    public static void createCategories() {
        CategorieDAO categorieDAO = new CategorieDAO(GeneralClass.applicationContext);

        int id = 1;
        Categorie categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Bricolage";
        categorie.description = "Description catégorie";
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Jardinage";
        categorie.description = "Description catégorie";
        categorie.setUserPreference(true);
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Déménagement";
        categorie.description = "Description catégorie";
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Ménage";
        categorie.description = "Description catégorie";
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Garde enfant";
        categorie.description = "Description catégorie";
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Annimaux";
        categorie.description = "Description catégorie";
        categorie.setUserPreference(true);
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Informatique";
        categorie.description = "Description catégorie";
        categorie.setUserPreference(true);
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Consciergérie";
        categorie.description = "Description catégorie";
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Sport";
        categorie.description = "Description catégorie";
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Rencontre";
        categorie.description = "Description catégorie";
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Auto";
        categorie.description = "Description catégorie";
        categorieDAO.ajouter(categorie);

        id++;
        categorie = new Categorie();
        categorie.id = id;
        categorie.designation = "Coursieux";
        categorie.description = "Description catégorie";
        categorie.setUserPreference(true);
        categorieDAO.ajouter(categorie);
    }
}
