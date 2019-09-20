package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class SollicitationDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String ID_SERVICE = "ids";
    public static final String DESCRIPTION_SERVICE = "description";
    public static final String CATEGORIE = "categorie";
    public static final String SOUS_CATEGORIE = "sous_cat";
    public static final String MONTANT_SERVICE = "montant";
    public static final String NOM_USER = "noms";
    public static final String PHONE_USER = "phone";
    public static final String ID_USER = "iduser";
    public static final String URL_IMAGE_USER = "url_img";
    public static final String DATE = "datesol";
    public static final String STATUT = "statut";
    public static final String HAVE_SOLLICITED = "havesol";
    public static final String IS_RDV = "isrdv";
    public static final String IS_CONCLU = "isconclu";
    public static final String MONTANT_CONCLU = "montantc";
    public static final String DEVISE_CONCLU = "devc";
    public static final String DATE_RDV = "daterdv";
    public static final String HEURE_RDV = "heurerdv";
    public static final String COTE = "cote";
    public static final String COMMENT = "comments";
    public static final String IS_MY = "is_my";
    public static final String IS_ACCEPTED = "isacc";
    public static final String IS_REFUSED = "isref";
    public static final String TABLE_NOM = "t_sollicitation";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    ID_SERVICE + " INTEGER, " +
                    DESCRIPTION_SERVICE + " TEXT, " +
                    MONTANT_SERVICE + " REAL, " +
                    NOM_USER + " TEXT, " +
                    PHONE_USER + " TEXT, " +
                    ID_USER + " INTEGER, " +
                    URL_IMAGE_USER + " TEXT, " +
                    DATE + " TEXT, " +
                    STATUT + " INTEGER, " +
                    HAVE_SOLLICITED + " INTEGER, " +
                    CATEGORIE + " TEXT, " +
                    SOUS_CATEGORIE + " TEXT, " +
                    IS_RDV + " INTEGER, " +
                    IS_CONCLU + " INTEGER, " +
                    MONTANT_CONCLU + " REAL, " +
                    DEVISE_CONCLU + " TEXT, " +
                    DATE_RDV + " TEXT, " +
                    HEURE_RDV + " TEXT, " +
                    COTE + " INTEGER, " +
                    COMMENT + " TEXT, " +
                    IS_MY + " INTEGER, " +
                    IS_ACCEPTED + " INTEGER, " +
                    IS_REFUSED + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static SollicitationDAO instance;

    public SollicitationDAO(Context pContext) {
        super(pContext);
    }

    public static SollicitationDAO getInstance(Context ctx){
        if(instance==null) instance = new SollicitationDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public Sollicitation ajouter(Sollicitation object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(ID_SERVICE, object.getIdService());
                value.put(DESCRIPTION_SERVICE, object.getDescriptionService());
                value.put(MONTANT_SERVICE, object.getMontant());
                value.put(NOM_USER, object.getNomsUser());
                value.put(PHONE_USER, object.getPhoneUser());
                value.put(ID_USER, object.getIdUser());
                value.put(URL_IMAGE_USER, object.getUrlImageUser());
                value.put(DATE, object.getDate());
                value.put(STATUT, object.getStatut());
                value.put(HAVE_SOLLICITED, object.isHaveSollicited());
                value.put(CATEGORIE, object.getCategorie());
                value.put(SOUS_CATEGORIE, object.getSouscategorie());
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

    public Sollicitation find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            Sollicitation object = null;
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
                String cat=c.getString(11);
                String scat=c.getString(12);
                int rdv=c.getInt(13);
                int conclu=c.getInt(14);
                float montc=c.getFloat(15);
                String devc=c.getString(16);
                String datec=c.getString(17);
                String heure=c.getString(18);
                int cote=c.getInt(19);
                String com=c.getString(20);
                int ismy=c.getInt(21);
                int isacc=c.getInt(22);
                int isref=c.getInt(23);

                object = new Sollicitation();
                object.setId(_id);
                object.setIdService(ids);
                object.setDescriptionService(des);
                object.setMontant(mont);
                object.setNomsUser(nomu);
                object.setPhoneUser(phnu);
                object.setIdUser(idu);
                object.setUrlImageUser(urlimg);
                object.setDate(date);
                object.setStatut(stat);
                object.setHaveSollicited(false);
                if(have==1) object.setHaveSollicited(true);
                object.setCategorie(cat);
                object.setSouscategorie(scat);
                if(rdv==1) object.setRDV(true);
                if(conclu==1) object.setConclu(true);
                object.setMontantConclu(montc);
                object.setDeviseConclu(devc);
                object.setDateRDV(datec);
                object.setHeureRDV(heure);
                object.setCote(cote);
                object.setComment(com);
                if(ismy==1) object.setMy(true);
                if(isacc==1) object.setAccepted(true);
                if(isref==1) object.setRefused(true);
            }
            c.close();
            close();
            return object;
        }catch (Exception e){
            return null;
        }
    }

    public List<Sollicitation> getMesSollucitations() {
        List<Sollicitation> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + HAVE_SOLLICITED + " = 1", null);
            while (c.moveToNext()) {
                list.add(find(c.getLong(0)));
            }
            c.close();
            close();
        }catch (Exception e){

        }
        return list;
    }

    public List<Sollicitation> getSollicitants(Service service) {
        List<Sollicitation> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + IS_MY + " = 1 and " + ID_SERVICE + " = ?", new String[]{String.valueOf(service.getId())});
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

    public long modifier(Sollicitation object) {
        ContentValues value = new ContentValues();
        value.put(ID_SERVICE, object.getIdService());
        value.put(DESCRIPTION_SERVICE, object.getDescriptionService());
        value.put(MONTANT_SERVICE, object.getMontant());
        value.put(NOM_USER, object.getNomsUser());
        value.put(PHONE_USER, object.getPhoneUser());
        value.put(ID_USER, object.getIdUser());
        value.put(URL_IMAGE_USER, object.getUrlImageUser());
        value.put(DATE, object.getDate());
        value.put(STATUT, object.getStatut());
        value.put(HAVE_SOLLICITED, object.isHaveSollicited());
        value.put(CATEGORIE, object.getCategorie());
        value.put(SOUS_CATEGORIE, object.getSouscategorie());
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
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}
