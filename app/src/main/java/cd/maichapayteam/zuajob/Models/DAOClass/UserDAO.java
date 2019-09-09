package cd.maichapayteam.zuajob.Models.DAOClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.User;

/**
 * Created by ElikyaLK on 29/12/2018.
 */

public class UserDAO extends DAOBase {

    public static final String KEY = "_id";
    public static final String AUTH_CODE = "authCode";
    public static final String PRENOM = "prenom";
    public static final String NOM = "nom";
    public static final String PHONE = "phone";
    public static final String ADRESSE = "adresse";
    public static final String PAYS = "pays";
    public static final String VILLE = "ville";
    public static final String QUARTIER = "quartier";
    public static final String URL_PHOTO = "urlPhoto";
    public static final String IDENTITE_VERIFIE = "identiteVerifie";
    public static final String BIRTHDAY = "birthday";
    public static final String TYPE_IDENTITE = "typeIdentite";
    public static final String NUM_IDENTITE = "numIdentite";
    public static final String MY_PROFIL = "myProfil";
    public static final String TYPE = "type";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String SEXE = "sexe";
    public static final String CODE_PAYS = "codePays";
    public static final String COMMUNE = "commune";
    public static final String ABOUT = "about";
    public static final String TABLE_NOM = "t_user";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOM + " (" +
                    KEY + " INTEGER PRIMARY KEY, " +
                    AUTH_CODE + " TEXT, " +
                    PRENOM + " TEXT, " +
                    NOM + " TEXT, " +
                    PHONE + " TEXT, " +
                    ADRESSE + " TEXT, " +
                    PAYS + " TEXT, " +
                    VILLE + " TEXT, " +
                    QUARTIER + " TEXT, " +
                    URL_PHOTO + " TEXT, " +
                    IDENTITE_VERIFIE + " INTEGER, " +
                    BIRTHDAY + " TEXT, " +
                    TYPE_IDENTITE + " INTEGER, " +
                    NUM_IDENTITE + " TEXT, " +
                    MY_PROFIL + " INTEGER, " +
                    TYPE + " INTEGER, " +
                    EMAIL + " TEXT, " +
                    PASSWORD + " TEXT, " +
                    SEXE + " TEXT, " +
                    CODE_PAYS + " TEXT, " +
                    COMMUNE + " TEXT, " +
                    ABOUT + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    private static Context context;
    private static UserDAO instance;

    public UserDAO(Context pContext) {
        super(pContext);
    }

    public static UserDAO getInstance(Context ctx){
        if(instance==null) instance = new UserDAO(ctx);
        if(context==null) context = ctx;
        return instance;
    }

    public User ajouter(User object){
        try{
            if (find(object.getId())==null){
                ContentValues value = new ContentValues();
                value.put(KEY, object.getId());
                value.put(ABOUT, object.getAbout());
                value.put(AUTH_CODE, object.getAuthCode());
                value.put(CODE_PAYS, object.getCodePays());
                value.put(COMMUNE, object.getCommune());
                value.put(CODE_PAYS, object.getCodePays());
                value.put(EMAIL, object.getEmail());
                value.put(IDENTITE_VERIFIE, object.isIdentiteVerifie());
                value.put(SEXE, object.getSexe());
                value.put(PASSWORD, object.getPassword());
                value.put(TYPE_IDENTITE, object.getTypeIdentite());
                value.put(MY_PROFIL, object.isMyProfil());
                value.put(NUM_IDENTITE, object.getNumIdentite());
                value.put(BIRTHDAY, object.getBirthday());
                value.put(URL_PHOTO, object.getUrlPhoto());
                value.put(TYPE, object.getType());
                value.put(PRENOM, object.getPrenom());
                value.put(NOM, object.getNom());
                value.put(PHONE, object.getPhone());
                value.put(ADRESSE, object.getAdresse());
                value.put(PAYS, object.getPays());
                value.put(VILLE, object.getVille());
                value.put(QUARTIER, object.getQuartier());
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

    public User find(long id){
        try{
            open();
            Cursor c = mDb.rawQuery("select * from " + TABLE_NOM + " where " + KEY + " = ?", new String[]{String.valueOf(id)});
            User object = null;
            while (c.moveToNext()) {
                long _id = c.getLong(0);
                String authcode=c.getString(1);
                String prenom=c.getString(2);
                String nom=c.getString(3);
                String phone=c.getString(4);
                String adresse=c.getString(5);
                String pays=c.getString(6);
                String ville=c.getString(7);
                String quartier=c.getString(8);
                String url=c.getString(9);
                boolean ident= false;
                if(c.getInt(10)==1) ident=true;
                String birth=c.getString(11);
                int type_id=c.getInt(12);
                String num_id=c.getString(13);
                boolean myProf= false;
                if(c.getInt(14)==1) myProf=true;
                int type=c.getInt(15);
                String email=c.getString(16);
                String pass=c.getString(17);
                String sexe=c.getString(18);
                String code_pays=c.getString(19);
                String commune=c.getString(20);
                String about=c.getString(21);

                object = new User();
                object.setId(_id);
                object.setAuthCode(authcode);
                object.setPrenom(prenom);
                object.setNom(nom);
                object.setPhone(phone);
                object.setAdresse(adresse);
                object.setPays(pays);
                object.setVille(ville);
                object.setQuartier(quartier);
                object.setUrlPhoto(url);
                object.setIdentiteVerifie(ident);
                object.setBirthday(birth);
                object.setTypeIdentite(type_id);
                object.setNumIdentite(num_id);
                object.setMyProfil(myProf);
                object.setType(type);
                object.setEmail(email);
                object.setPassword(pass);
                object.setSexe(sexe);
                object.setCodePays(code_pays);
                object.setCommune(commune);
                object.setAbout(about);
            }
            c.close();
            close();
            return object;
        }catch (Exception e){
            return null;
        }
    }

    //public List<Dechet> findAll(){
    //    List<Dechet> list = new ArrayList<>();
    //    try{
    //        open();
    //        Cursor c = mDb.rawQuery("select " + KEY + " from " + TABLE_NOM, null);
    //        while (c.moveToNext()) {
    //            list.add(find(c.getLong(0)));
    //        }
    //        c.close();
    //        close();
    //    }catch (Exception e){
//
    //    }
    //    return list;
    //}

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

    public long modifier(User object) {
        ContentValues value = new ContentValues();
        value.put(ABOUT, object.getAbout());
        value.put(AUTH_CODE, object.getAuthCode());
        value.put(CODE_PAYS, object.getCodePays());
        value.put(COMMUNE, object.getCommune());
        value.put(CODE_PAYS, object.getCodePays());
        value.put(EMAIL, object.getEmail());
        value.put(IDENTITE_VERIFIE, object.isIdentiteVerifie());
        value.put(SEXE, object.getSexe());
        value.put(PASSWORD, object.getPassword());
        value.put(TYPE_IDENTITE, object.getTypeIdentite());
        value.put(MY_PROFIL, object.isMyProfil());
        value.put(NUM_IDENTITE, object.getNumIdentite());
        value.put(BIRTHDAY, object.getBirthday());
        value.put(URL_PHOTO, object.getUrlPhoto());
        value.put(TYPE, object.getType());
        value.put(PRENOM, object.getPrenom());
        value.put(NOM, object.getNom());
        value.put(PHONE, object.getPhone());
        value.put(ADRESSE, object.getAdresse());
        value.put(PAYS, object.getPays());
        value.put(VILLE, object.getVille());
        value.put(QUARTIER, object.getQuartier());
        open();
        long rep = mDb.update(TABLE_NOM, value, KEY + " = ?", new String[]{String.valueOf(object.getId())});
        close();
        return rep;
    }

}