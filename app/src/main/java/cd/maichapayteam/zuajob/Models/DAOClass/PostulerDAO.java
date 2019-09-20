package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Annonce;
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
    public static final String HAVE_POSTULED = "havepost";
    public static final String IS_RDV = "isrdv";
    public static final String IS_CONCLU = "isconclu";
    public static final String MONTANT_CONCLU = "montantc";
    public static final String DEVISE_CONCLU = "devc";
    public static final String DATE_RDV = "daterdv";
    public static final String HEURE_RDV = "heurerdv";
    public static final String DEVISE = "devise";
    public static final String COTE = "cote";
    public static final String COMMENT = "comments";
    public static final String IS_MY = "is_my";
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
                    STATUT + " INTEGER, " +
                    HAVE_POSTULED + " INTEGER, " +
                    IS_RDV + " INTEGER, " +
                    IS_CONCLU + " INTEGER, " +
                    MONTANT_CONCLU + " REAL, " +
                    DEVISE_CONCLU + " TEXT, " +
                    DATE_RDV + " TEXT, " +
                    HEURE_RDV + " TEXT, " +
                    DEVISE + " TEXT, " +
                    COTE + " INTEGER, " +
                    COMMENT + " TEXT, " +
                    IS_MY + " INTEGER);";

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
                value.put(DEVISE, object.getDevise());
                value.put(NOM_USER, object.getNomsUser());
                value.put(PHONE_USER, object.getPhoneUser());
                value.put(ID_USER, object.getIdUser());
                value.put(URL_IMAGE_USER, object.getUrlImageUser());
                value.put(DATE, object.getDate());
                value.put(STATUT, object.getStatut());
                value.put(HAVE_POSTULED, object.isHavePostuled());
                value.put(IS_RDV, object.isRDV());
                value.put(IS_CONCLU, object.isConclu());
                value.put(MONTANT_CONCLU, object.getMontantConclu());
                value.put(DEVISE_CONCLU, object.getDeviseConclu());
                value.put(DATE_RDV, object.getDateRDV());
                value.put(HEURE_RDV, object.getHeureRDV());
                value.put(COTE, object.getCote());
                value.put(COMMENT, object.getComment());
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
            Log.e("PostulerDAO", e.getMessage());
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

    public long max(){
        try{
            open();
            Cursor c = mDb.rawQuery("select max(" + KEY + ") from " + TABLE_NOM, null);
            long co = 0;
            while (c.moveToNext()) {
                co = c.getLong(0);
            }
            c.close();
            close();
            return co;
        }catch (Exception e){
            Log.e("ServiceDAO", e.getMessage());
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
                int have=c.getInt(10);
                int rdv=c.getInt(11);
                int conclu=c.getInt(12);
                float montc=c.getFloat(13);
                String devc=c.getString(14);
                String datec=c.getString(15);
                String heure=c.getString(16);
                String dev=c.getString(17);
                int cote=c.getInt(18);
                String com=c.getString(19);
                int ismy=c.getInt(20);

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
                object.setHavePostuled(false);
                if(have==1) object.setHavePostuled(true);
                if(rdv==1) object.setRDV(true);
                if(conclu==1) object.setConclu(true);
                object.setMontantConclu(montc);
                object.setDeviseConclu(devc);
                object.setDateRDV(datec);
                object.setHeureRDV(heure);
                object.setDevise(dev);
                object.setCote(cote);
                object.setComment(com);
                if(ismy==1) object.setMy(true);
            }
            c.close();
            close();
            return object;
        }catch (Exception e){
            return null;
        }
    }

    public List<Postuler> getMesPostulances() {
        List<Postuler> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + HAVE_POSTULED + " = 1", null);
            while (c.moveToNext()) {
                list.add(find(c.getLong(0)));
            }
            c.close();
            close();
        }catch (Exception e){

        }
        return list;
    }

    public List<Postuler> getPostulants(Annonce annonce) {
        List<Postuler> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + IS_MY + " = 1 and " + ID_ANNONCE + " = ?", new String[]{String.valueOf(annonce.getId())});
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

    public long modifier(Postuler object) {
        ContentValues value = new ContentValues();
        value.put(ID_ANNONCE, object.getIdAnnonce());
        value.put(DESCRIPTION_ANNONCE, object.getDescriptionAnnonce());
        value.put(MONTANT_ANNONCE, object.getMontant());
        value.put(DEVISE, object.getDevise());
        value.put(NOM_USER, object.getNomsUser());
        value.put(PHONE_USER, object.getPhoneUser());
        value.put(ID_USER, object.getIdUser());
        value.put(URL_IMAGE_USER, object.getUrlImageUser());
        value.put(DATE, object.getDate());
        value.put(STATUT, object.getStatut());
        value.put(HAVE_POSTULED, object.isHavePostuled());
        value.put(IS_RDV, object.isRDV());
        value.put(IS_CONCLU, object.isConclu());
        value.put(MONTANT_CONCLU, object.getMontantConclu());
        value.put(DEVISE_CONCLU, object.getDeviseConclu());
        value.put(DATE_RDV, object.getDateRDV());
        value.put(HEURE_RDV, object.getHeureRDV());
        value.put(COTE, object.getCote());
        value.put(COMMENT, object.getComment());
        value.put(IS_MY, object.isMy());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}
