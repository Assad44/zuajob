package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import cd.maichapayteam.zuajob.Models.Object.Comment;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class CommentDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String COMMENT = "comment";
    public static final String DATE = "datee";
    public static final String ID_USER = "id_user";
    public static final String NOMS_USER = "noms_user";
    public static final String URL_IMAGE_USER = "url_image";
    public static final String ID_USER_CONCERNE = "id_user_c";
    public static final String NOMS_USER_CONCERNE = "noms_user_c";
    public static final String URL_IMAGE_USER_CONCERNE = "url_image_c";
    public static final String TABLE_NOM = "t_comment";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    COMMENT + " TEXT, " +
                    DATE + " TEXT, " +
                    ID_USER + " INTEGER, " +
                    NOMS_USER + " TEXT, " +
                    URL_IMAGE_USER + " TEXT, " +
                    ID_USER_CONCERNE + " INTEGER, " +
                    NOMS_USER_CONCERNE + " TEXT, " +
                    URL_IMAGE_USER_CONCERNE + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static CommentDAO instance;

    public CommentDAO(Context pContext) {
        super(pContext);
    }

    public static CommentDAO getInstance(Context ctx){
        if(instance==null) instance = new CommentDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public Comment ajouter(Comment object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(COMMENT, object.getComment());
                value.put(DATE, object.getDate());
                value.put(ID_USER, object.getIdUser());
                value.put(NOMS_USER, object.getNomsUser());
                value.put(URL_IMAGE_USER, object.getUrlImageUser());
                value.put(ID_USER_CONCERNE, object.getIdUserConcerne());
                value.put(NOMS_USER_CONCERNE, object.getNomsUserConcerne());
                value.put(URL_IMAGE_USER_CONCERNE, object.getUrlImageUserConcerne());
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

    public Comment find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            Comment object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(c.getColumnIndex(KEY));
                String com=c.getString(c.getColumnIndex(COMMENT));
                String date=c.getString(c.getColumnIndex(DATE));
                long idu=c.getLong(c.getColumnIndex(ID_USER));
                String noms=c.getString(c.getColumnIndex(NOMS_USER));
                String url=c.getString(c.getColumnIndex(URL_IMAGE_USER));
                long iduc=c.getLong(c.getColumnIndex(ID_USER_CONCERNE));
                String nomsc=c.getString(c.getColumnIndex(NOMS_USER_CONCERNE));
                String urlc=c.getString(c.getColumnIndex(URL_IMAGE_USER_CONCERNE));

                object = new Comment();
                object.setId(_id);
                object.setComment(com);
                object.setDate(date);
                object.setIdUser(idu);
                object.setNomsUser(noms);
                object.setUrlImageUser(url);
                object.setIdUserConcerne(iduc);
                object.setNomsUserConcerne(nomsc);
                object.setUrlImageUserConcerne(urlc);
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

    public long modifier(Comment object) {
        ContentValues value = new ContentValues();
        value.put(COMMENT, object.getComment());
        value.put(DATE, object.getDate());
        value.put(ID_USER, object.getIdUser());
        value.put(NOMS_USER, object.getNomsUser());
        value.put(URL_IMAGE_USER, object.getUrlImageUser());
        value.put(ID_USER_CONCERNE, object.getIdUserConcerne());
        value.put(NOMS_USER_CONCERNE, object.getNomsUserConcerne());
        value.put(URL_IMAGE_USER_CONCERNE, object.getUrlImageUserConcerne());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}
