package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import cd.maichapayteam.zuajob.Models.Object.Postuler;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class PostulerDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String ID_ANNONCE = "idan";
    public static final String DESCRIPTION_ANNONCE = "desc";
    public static final String MONTANT_ANNONCE = "montant";
    public static final String NOM_USER = "noms";
    public static final String PHONE_USER = "phone";
    public static final String ID_USER = "iduser";
    public static final String URL_IMAGE_USER = "url_img";
    public static final String DATE = "datesol";
    public static final String STATUT = "statut";
    public static final String TABLE_NOM = "t_postuler";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    ID_ANNONCE + " INTEGER, " +
                    DESCRIPTION_ANNONCE + " TEXT, " +
                    MONTANT_ANNONCE + " REAL, " +
                    NOM_USER + " TEXT, " +
                    PHONE_USER + " TEXT, " +
                    ID_USER + " INTEGER, " +
                    URL_IMAGE_USER + " TEXT, " +
                    DATE + " TEXT, " +
                    STATUT + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static PostulerDAO instance;

    public PostulerDAO(Context pContext) {
        super(pContext);
    }

    public static PostulerDAO getInstance(Context ctx){
        if(instance==null) instance = new PostulerDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public Postuler ajouter(Postuler object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(ID_ANNONCE, object.getIdAnnonce());
                value.put(DESCRIPTION_ANNONCE, object.getDescriptionAnnonce());
                value.put(MONTANT_ANNONCE, object.getMontant());
                value.put(NOM_USER, object.getNomsUser());
                value.put(PHONE_USER, object.getPhoneUser());
                value.put(ID_USER, object.getIdUser());
                value.put(URL_IMAGE_USER, object.getUrlImageUser());
                value.put(DATE, object.getDate());
                value.put(STATUT, object.getStatut());
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

    public Postuler find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            Postuler object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(0);
                long ids=c.getLong(1);
                String des=c.getString(2);
                float mont=c.getFloat(3);
                String nomu=c.getString(4);
                String phnu=c.getString(5);
                long idu=c.getLong(6);
                String urlimg=c.getString(7);
                String date=c.getString(8);
                int stat=c.getInt(9);

                object = new Postuler();
                object.setId(_id);
                object.setIdAnnonce(ids);
                object.setDescriptionAnnonce(des);
                object.setMontant(mont);
                object.setNomsUser(nomu);
                object.setPhoneUser(phnu);
                object.setIdUser(idu);
                object.setUrlImageUser(urlimg);
                object.setDate(date);
                object.setStatut(stat);
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

    public long modifier(Postuler object) {
        ContentValues value = new ContentValues();
        value.put(ID_ANNONCE, object.getIdAnnonce());
        value.put(DESCRIPTION_ANNONCE, object.getDescriptionAnnonce());
        value.put(MONTANT_ANNONCE, object.getMontant());
        value.put(NOM_USER, object.getNomsUser());
        value.put(PHONE_USER, object.getPhoneUser());
        value.put(ID_USER, object.getIdUser());
        value.put(URL_IMAGE_USER, object.getUrlImageUser());
        value.put(DATE, object.getDate());
        value.put(STATUT, object.getStatut());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}
