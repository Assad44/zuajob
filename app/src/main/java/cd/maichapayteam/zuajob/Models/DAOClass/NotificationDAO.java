package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Notification;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Tools.GeneralClass;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class NotificationDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String ID_USER = "cat";
    public static final String ID_OBJECT = "description";
    public static final String TYPE_OBJECT = "dev";
    public static final String MESSAGE = "idcat";
    public static final String TYPE_NOTIFICATION = "iduser";
    public static final String TIME = "idsouscat";
    public static final String IS_READED = "is_readed";
    public static final String TABLE_NOM = "t_notif";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    ID_OBJECT + " INTEGER, " +
                    ID_USER + " INTEGER, " +
                    TYPE_OBJECT + " TEXT, " +
                    TYPE_NOTIFICATION + " TEXT, " +
                    MESSAGE + " TEXT, " +
                    TIME + " TEXT, " +
                    IS_READED + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static NotificationDAO instance;

    public NotificationDAO(Context pContext) {
        super(pContext);
    }

    public static NotificationDAO getInstance(Context ctx){
        if(instance==null) instance = new NotificationDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public Notification ajouter(Notification object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(ID_OBJECT, object.getIdObject());
                value.put(ID_USER, object.getIdUser());
                value.put(TYPE_NOTIFICATION, object.getTypeNotification());
                value.put(TYPE_OBJECT, object.getTypeObject());
                value.put(MESSAGE, object.getMessage());
                value.put(TIME, object.getTime());
                value.put(IS_READED, object.isReaded());
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
            Log.e("NotificationDAO", e.getMessage());
            return 0;
        }
    }

    public Notification find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            Notification object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(c.getColumnIndex(KEY));
                long idus=c.getLong(c.getColumnIndex(ID_USER));
                long idob=c.getLong(c.getColumnIndex(ID_OBJECT));
                String tyob=c.getString(c.getColumnIndex(TYPE_OBJECT));
                String tyno=c.getString(c.getColumnIndex(TYPE_NOTIFICATION));
                String msg=c.getString(c.getColumnIndex(MESSAGE));
                String time=c.getString(c.getColumnIndex(TIME));
                int isread=c.getInt(c.getColumnIndex(IS_READED));

                Postuler postuler = null;
                Sollicitation sollicitation = null;
                if(tyob.equals("postulance")) {
                    PostulerDAO postulerDAO = PostulerDAO.getInstance(GeneralClass.applicationContext);
                    postuler = postulerDAO.find(idob);
                } else {
                    SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
                    sollicitation = sollicitationDAO.find(idob);
                }

                object = new Notification();
                object.setId(_id);
                object.setIdUser(idus);
                object.setIdObject(idob);
                object.setTypeObject(tyob);
                object.setTypeNotification(tyno);
                object.setMessage(msg);
                object.setTime(time);
                object.setPostulance(postuler);
                object.setSollicitation(sollicitation);
                if(isread==1) object.setReaded(true);
            }
            c.close();
            close();
            return object;
        }catch (Exception e){
            return null;
        }
    }

    public List<Notification> getAll() {
        List<Notification> list = new ArrayList<>();
        try{
            open();
            Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM + " order by " + TIME + " desc", null);
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

    public long modifier(Notification object) {
        ContentValues value = new ContentValues();
        value.put(ID_OBJECT, object.getIdObject());
        value.put(ID_USER, object.getIdUser());
        value.put(TYPE_NOTIFICATION, object.getTypeNotification());
        value.put(TYPE_OBJECT, object.getTypeObject());
        value.put(MESSAGE, object.getMessage());
        value.put(TIME, object.getTime());
        value.put(IS_READED, object.isReaded());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

    public long setReaded(long idNotif) {
        ContentValues value = new ContentValues();
        value.put(IS_READED, true);
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(idNotif)});
        close();
        return rep;
    }

    public long deletePersonnelData() {
        return supprimer();
    }

}
