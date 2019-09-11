package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import cd.maichapayteam.zuajob.Models.Object.Reaction;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class ReactionDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String COMMENT = "comment";
    public static final String COTE = "cote";
    public static final String ID_SERVICE = "id_serv";
    public static final String DATE = "datee";
    public static final String ID_USER = "id_user";
    public static final String NOMS_USER = "noms_user";
    public static final String URL_IMAGE_USER = "url_image";
    public static final String TABLE_NOM = "t_Reaction";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    COMMENT + " TEXT, " +
                    COTE + " INTEGER, " +
                    ID_SERVICE + " INTEGER, " +
                    DATE + " TEXT, " +
                    ID_USER + " INTEGER, " +
                    NOMS_USER + " TEXT, " +
                    URL_IMAGE_USER + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static ReactionDAO instance;

    public ReactionDAO(Context pContext) {
        super(pContext);
    }

    public static ReactionDAO getInstance(Context ctx){
        if(instance==null) instance = new ReactionDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public Reaction ajouter(Reaction object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(COMMENT, object.getComment());
                value.put(COTE, object.getCote());
                value.put(ID_SERVICE, object.getIdService());
                value.put(DATE, object.getDate());
                value.put(ID_USER, object.getIdUser());
                value.put(NOMS_USER, object.getNomsUser());
                value.put(URL_IMAGE_USER, object.getUrlImageUser());
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

    public Reaction find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            Reaction object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(0);
                String com=c.getString(1);
                int cote=c.getInt(2);
                long ids=c.getLong(3);
                String date=c.getString(4);
                long idu=c.getLong(5);
                String noms=c.getString(6);
                String url=c.getString(7);

                object = new Reaction();
                object.setId(_id);
                object.setComment(com);
                object.setCote(cote);
                object.setIdService(ids);
                object.setDate(date);
                object.setIdUser(idu);
                object.setNomsUser(noms);
                object.setUrlImageUser(url);
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

    public long modifier(Reaction object) {
        ContentValues value = new ContentValues();
        value.put(COMMENT, object.getComment());
        value.put(COTE, object.getCote());
        value.put(ID_SERVICE, object.getIdService());
        value.put(DATE, object.getDate());
        value.put(ID_USER, object.getIdUser());
        value.put(NOMS_USER, object.getNomsUser());
        value.put(URL_IMAGE_USER, object.getUrlImageUser());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}
