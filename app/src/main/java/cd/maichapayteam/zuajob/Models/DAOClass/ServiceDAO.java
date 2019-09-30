package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Models.Object.User;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class ServiceDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String CATEGORIE = "cat";
    public static final String COTE = "cote";
    public static final String DESCRIPTION = "descr";
    public static final String DEVISE = "dev";
    public static final String ID_CATEGORIE = "idcat";
    public static final String ID_JOBEUR = "idjob";
    public static final String ID_SOUS_CATEGORIE = "idscat";
    public static final String MONTANT = "montant";
    public static final String NOMBRE_REALISATION = "nbrreal";
    public static final String NOMS_JOBEUR = "nomsjob";
    public static final String PHONE_JOBEUR = "phonejob";
    public static final String SOUS_CATEGORIE = "souscat";
    public static final String URL_PHOTO_JOBEUR = "urlphoto";
    public static final String IS_MY = "ismy";
    public static final String DATE_PUBLICATION = "datepub";
    public static final String TABLE_NOM = "t_service";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    CATEGORIE + " TEXT, " +
                    COTE + " INTEGER, " +
                    DESCRIPTION + " TEXT, " +
                    DEVISE + " TEXT, " +
                    ID_CATEGORIE + " INTEGER, " +
                    ID_JOBEUR + " INTEGER, " +
                    ID_SOUS_CATEGORIE + " INTEGER, " +
                    MONTANT + " REAL, " +
                    NOMBRE_REALISATION + " INTEGER, " +
                    NOMS_JOBEUR + " TEXT, " +
                    PHONE_JOBEUR + " TEXT, " +
                    SOUS_CATEGORIE + " TEXT, " +
                    URL_PHOTO_JOBEUR + " TEXT, " +
                    IS_MY + " INTEGER, " +
                    DATE_PUBLICATION + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static ServiceDAO instance;

    public ServiceDAO(Context pContext) {
        super(pContext);
    }

    public static ServiceDAO getInstance(Context ctx){
        if(instance==null) instance = new ServiceDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public Service ajouter(Service object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(CATEGORIE, object.getCategorie());
                value.put(COTE, object.getCote());
                value.put(DESCRIPTION, object.getDescription());
                value.put(DEVISE, object.getDevise());
                value.put(ID_CATEGORIE, object.getIdCategorie());
                value.put(ID_JOBEUR, object.getIdJobeur());
                value.put(ID_SOUS_CATEGORIE, object.getIdSousCategorie());
                value.put(MONTANT, object.getMontant());
                value.put(NOMBRE_REALISATION, object.getNombreRealisation());
                value.put(NOMS_JOBEUR, object.getNomsJobeur());
                value.put(PHONE_JOBEUR, object.getPhoneJobeur());
                value.put(SOUS_CATEGORIE, object.getSousCategorie());
                value.put(URL_PHOTO_JOBEUR, object.getUrlImageJobeur());
                value.put(IS_MY, object.isMy());
                value.put(DATE_PUBLICATION, object.getDatePublication());
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

    public Service find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            Service object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(c.getColumnIndex(KEY));
                String cat=c.getString(c.getColumnIndex(CATEGORIE));
                int cote=c.getInt(c.getColumnIndex(COTE));
                String des=c.getString(c.getColumnIndex(DESCRIPTION));
                String dev=c.getString(c.getColumnIndex(DEVISE));
                long idcat=c.getLong(c.getColumnIndex(ID_CATEGORIE));
                long idjob=c.getLong(c.getColumnIndex(ID_JOBEUR));
                long idscat=c.getLong(c.getColumnIndex(ID_SOUS_CATEGORIE));
                float mont=c.getFloat(c.getColumnIndex(MONTANT));
                int nbrRel=c.getInt(c.getColumnIndex(NOMBRE_REALISATION));
                String nomsJob=c.getString(c.getColumnIndex(NOMS_JOBEUR));
                String phoneJob=c.getString(c.getColumnIndex(PHONE_JOBEUR));
                String sousCat=c.getString(c.getColumnIndex(SOUS_CATEGORIE));
                String urlImage=c.getString(c.getColumnIndex(URL_PHOTO_JOBEUR));
                int ismy=c.getInt(c.getColumnIndex(IS_MY));
                String date=c.getString(c.getColumnIndex(DATE_PUBLICATION));

                object = new Service();
                object.setId(_id);
                object.setCategorie(cat);
                object.setCote(cote);
                object.setDescription(des);
                object.setDevise(dev);
                object.setIdCategorie(idcat);
                object.setIdJobeur(idjob);
                object.setIdSousCategorie(idscat);
                object.setMontant(mont);
                object.setNombreRealisation(nbrRel);
                object.setNomsJobeur(nomsJob);
                object.setPhoneJobeur(phoneJob);
                object.setSousCategorie(sousCat);
                object.setUrlImageJobeur(urlImage);
                if(ismy==1) object.setMy(true);
                object.setDatePublication(date);
            }
            c.close();
            close();
            return object;
        }catch (Exception e){
            return null;
        }
    }

    public List<Service> getAll() {
        List<Service> list = new ArrayList<>();
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

    public List<Service> randomService(int next) {
        List<Service> list = new ArrayList<>();
        //TODO implements algorithme for return random list of service
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " limit ?, 20", new String[]{String.valueOf(next)});
            while (c.moveToNext()) {
                list.add(find(c.getLong(0)));
            }
            Log.e("randomService", "List des services :" + next + " - " + list.size());
            c.close();
            close();
        }catch (Exception e){

        }
        return list;
    }

    public List<Service> getNewService(int next, SousCategorie sousCategorie) {
        List<Service> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + SOUS_CATEGORIE + " = ? order by " + DATE_PUBLICATION + " desc limit ?, 20", new String[]{String.valueOf(sousCategorie.getId()), String.valueOf(next)});
            while (c.moveToNext()) {
                list.add(find(c.getLong(0)));
            }
            c.close();
            close();
        }catch (Exception e){

        }
        return list;
    }

    public List<Service> getServiceByCote(int next, SousCategorie sousCategorie) {
        List<Service> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + SOUS_CATEGORIE + " = ? order by " + COTE + " desc limit ?, 20", new String[]{String.valueOf(sousCategorie.getId()), String.valueOf(next)});
            while (c.moveToNext()) {
                list.add(find(c.getLong(0)));
            }
            c.close();
            close();
        }catch (Exception e){

        }
        return list;
    }

    public List<Service> getMesServices() {
        List<Service> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " where " + IS_MY + " = 1", null);
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

    public long modifier(Service object) {
        ContentValues value = new ContentValues();
        value.put(CATEGORIE, object.getCategorie());
        value.put(COTE, object.getCote());
        value.put(DESCRIPTION, object.getDescription());
        value.put(DEVISE, object.getDevise());
        value.put(ID_CATEGORIE, object.getIdCategorie());
        value.put(ID_JOBEUR, object.getIdJobeur());
        value.put(ID_SOUS_CATEGORIE, object.getIdSousCategorie());
        value.put(MONTANT, object.getMontant());
        value.put(NOMBRE_REALISATION, object.getNombreRealisation());
        value.put(NOMS_JOBEUR, object.getNomsJobeur());
        value.put(PHONE_JOBEUR, object.getPhoneJobeur());
        value.put(SOUS_CATEGORIE, object.getSousCategorie());
        value.put(URL_PHOTO_JOBEUR, object.getUrlImageJobeur());
        value.put(IS_MY, object.isMy());
        value.put(DATE_PUBLICATION, object.getDatePublication());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

    public long deletePersonnelData() {
        ContentValues value = new ContentValues();
        value.put(IS_MY, false);
        open();
        long rep = mDb.update(TABLE_NOM, value, null, null);
        close();
        return rep;
    }

}
