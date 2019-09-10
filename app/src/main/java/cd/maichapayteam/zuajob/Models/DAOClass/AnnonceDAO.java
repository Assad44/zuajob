package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import cd.maichapayteam.zuajob.Models.Object.Annonce;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class AnnonceDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String CATEGORIE = "cat";
    public static final String DESCRIPTION = "description";
    public static final String DEVISE = "dev";
    public static final String ID_CATEGORIE = "idcat";
    public static final String ID_USER = "iduser";
    public static final String ID_SOUS_CATEGORIE = "idsouscat";
    public static final String MONTANT = "montant";
    public static final String NOMS_USER = "nomsuser";
    public static final String PHONE_USER = "phoneuser";
    public static final String SOUS_CATEGORIE = "souscat";
    public static final String URL_PHOTO_USER = "urluser";
    public static final String DATE = "datepub";
    public static final String IS_MY = "ismy";
    public static final String TABLE_NOM = "t_annonce";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    CATEGORIE + " TEXT, " +
                    DESCRIPTION + " TEXT, " +
                    DEVISE + " TEXT, " +
                    ID_CATEGORIE + " INTEGER, " +
                    ID_USER + " INTEGER, " +
                    ID_SOUS_CATEGORIE + " INTEGER, " +
                    MONTANT + " REAL, " +
                    NOMS_USER + " TEXT, " +
                    PHONE_USER + " TEXT, " +
                    SOUS_CATEGORIE + " TEXT, " +
                    URL_PHOTO_USER + " TEXT, " +
                    DATE + " TEXT, " +
                    IS_MY + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static AnnonceDAO instance;

    public AnnonceDAO(Context pContext) {
        super(pContext);
    }

    public static AnnonceDAO getInstance(Context ctx){
        if(instance==null) instance = new AnnonceDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public Annonce ajouter(Annonce object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(CATEGORIE, object.getCategorie());
                value.put(DESCRIPTION, object.getDescription());
                value.put(DEVISE, object.getDevise());
                value.put(ID_CATEGORIE, object.getIdCategorie());
                value.put(ID_USER, object.getIdUser());
                value.put(ID_SOUS_CATEGORIE, object.getIdSousCategorie());
                value.put(MONTANT, object.getMontant());
                value.put(NOMS_USER, object.getNomsUser());
                value.put(PHONE_USER, object.getPhoneUser());
                value.put(SOUS_CATEGORIE, object.getSousCategorie());
                value.put(URL_PHOTO_USER, object.getUrlImageUser());
                value.put(DATE, object.getDate());
                value.put(IS_MY, object.isMy());
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

    public Annonce find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            Annonce object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(0);
                String cat=c.getString(1);
                String des=c.getString(2);
                String dev=c.getString(3);
                long idcat=c.getLong(4);
                long idus=c.getLong(5);
                long idscat=c.getLong(6);
                float mont=c.getFloat(7);
                String nomsus=c.getString(8);
                String phoneus=c.getString(9);
                String sousCat=c.getString(10);
                String urlImage=c.getString(11);
                String date=c.getString(12);
                int ismy=c.getInt(13);

                object = new Annonce();
                object.setId(_id);
                object.setCategorie(cat);
                object.setDescription(des);
                object.setDevise(dev);
                object.setIdCategorie(idcat);
                object.setIdUser(idus);
                object.setIdSousCategorie(idscat);
                object.setMontant(mont);
                object.setNomsUser(nomsus);
                object.setPhoneUser(phoneus);
                object.setSousCategorie(sousCat);
                object.setUrlImageUser(urlImage);
                object.setDate(date);
                if(ismy==1) object.setMy(true);
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

    public long modifier(Annonce object) {
        ContentValues value = new ContentValues();
        value.put(CATEGORIE, object.getCategorie());
        value.put(DESCRIPTION, object.getDescription());
        value.put(DEVISE, object.getDevise());
        value.put(ID_CATEGORIE, object.getIdCategorie());
        value.put(ID_USER, object.getIdUser());
        value.put(ID_SOUS_CATEGORIE, object.getIdSousCategorie());
        value.put(MONTANT, object.getMontant());
        value.put(NOMS_USER, object.getNomsUser());
        value.put(PHONE_USER, object.getPhoneUser());
        value.put(SOUS_CATEGORIE, object.getSousCategorie());
        value.put(URL_PHOTO_USER, object.getUrlImageUser());
        value.put(DATE, object.getDate());
        value.put(IS_MY, object.isMy());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}
