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
    public static final String IS_ACCEPTED = "isacc";
    public static final String IS_REFUSED = "isref";
    public static final String DETAIL_RDV = "det_rdv";
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
                    IS_MY + " INTEGER, " +
                    IS_ACCEPTED + " INTEGER, " +
                    IS_REFUSED + " INTEGER, " +
                    DETAIL_RDV + " TEXT);";

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
                value.put(MONTANT_ANNONCE, object.getMontantAnnonce());
                value.put(DEVISE, object.getDeviseAnnonce());
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
                value.put(IS_ACCEPTED, object.isAccepted());
                value.put(IS_REFUSED, object.isRefused());
                value.put(DETAIL_RDV, object.getDetailRDV());
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
                long _id = c.getLong(c.getColumnIndex(KEY));
                long ids=c.getLong(c.getColumnIndex(ID_ANNONCE));
                String des=c.getString(c.getColumnIndex(DESCRIPTION_ANNONCE));
                float mont=c.getFloat(c.getColumnIndex(MONTANT_ANNONCE));
                String nomu=c.getString(c.getColumnIndex(NOM_USER));
                String phnu=c.getString(c.getColumnIndex(PHONE_USER));
                long idu=c.getLong(c.getColumnIndex(ID_USER));
                String urlimg=c.getString(c.getColumnIndex(URL_IMAGE_USER));
                String date=c.getString(c.getColumnIndex(DATE));
                int stat=c.getInt(c.getColumnIndex(STATUT));
                int have=c.getInt(c.getColumnIndex(HAVE_POSTULED));
                int rdv=c.getInt(c.getColumnIndex(IS_RDV));
                int conclu=c.getInt(c.getColumnIndex(IS_CONCLU));
                float montc=c.getFloat(c.getColumnIndex(MONTANT_CONCLU));
                String devc=c.getString(c.getColumnIndex(DEVISE_CONCLU));
                String datec=c.getString(c.getColumnIndex(DATE_RDV));
                String heure=c.getString(c.getColumnIndex(HEURE_RDV));
                String dev=c.getString(c.getColumnIndex(DEVISE));
                int cote=c.getInt(c.getColumnIndex(COTE));
                String com=c.getString(c.getColumnIndex(COMMENT));
                int ismy=c.getInt(c.getColumnIndex(IS_MY));
                int isacc=c.getInt(c.getColumnIndex(IS_ACCEPTED));
                int isref=c.getInt(c.getColumnIndex(IS_REFUSED));
                String detail = c.getString(c.getColumnIndex(DETAIL_RDV));

                object = new Postuler();
                object.setId(_id);
                object.setIdAnnonce(ids);
                object.setDescriptionAnnonce(des);
                object.setMontantAnnonce(mont);
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
                object.setDeviseAnnonce(dev);
                object.setCote(cote);
                object.setComment(com);
                if(ismy==1) object.setMy(true);
                if(isacc==1) object.setAccepted(true);
                if(isref==1) object.setRefused(true);
                object.setDetailRDV(detail);
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
        value.put(MONTANT_ANNONCE, object.getMontantAnnonce());
        value.put(DEVISE, object.getDeviseAnnonce());
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
        value.put(IS_ACCEPTED, object.isAccepted());
        value.put(IS_REFUSED, object.isRefused());
        value.put(DETAIL_RDV, object.getDetailRDV());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

    public long deletePersonnelData() {
        ContentValues value = new ContentValues();
        value.put(HAVE_POSTULED, false);
        value.put(IS_MY, false);
        open();
        long rep = mDb.update(TABLE_NOM, value, null, null);
        close();
        return rep;
    }

}
