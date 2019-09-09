package cd.maichapayteam.zuajob.Models.Object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

import cd.maichapayteam.zuajob.BackEnd.SQLite_db.DataBase;
import cd.maichapayteam.zuajob.BackEnd.SQLite_db.ExecuteUpdate;
import cd.maichapayteam.zuajob.Tools.Tool;

public class Users {

    // TODO: variables
    public String id;
    public String nom;
    public String prenom;
    public String sexe;
    public String type;
    public String phone;
    public String birthday  ;

    public String adresse  ;
    public String pays  ;
    public String ville  ;
    public String quartier  ;

    public String email;
    public String passeword;
    public String avatar;

    public String created_by;
    public String created_at;
    public String updating;
    public String status;

    public String last_connexion;
    public String connexion_token;

    public String authCode  ;
    public String urlPhoto  ;
    public String identiteVerifie  ;
    public String typeIdentite  ;
    public String numIdentite  ;
    public String myProfil  ;
    public String password  ;
    public String codePays  ;
    public String commune  ;
    public String about  ;

    // TODO : GETTERS & SETTERS
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    // TODO : DATABASE COLUMNS
    public static String table_name        = "users";
    public static String colonne_id        = "id_"+table_name;
    public static String colonne_nom = "name";
    public static String colonne_prenom = "prenom";
    public static String colonne_type= "type";
    public static String colonne_date_naissance= "birthday";
    public static String colonne_phone= "phone";

    public static String create_table = "create table "+table_name+" (" +
            colonne_id+ " varchar(25) primary key, " +
            colonne_nom+ " varchar(500),"+
            colonne_prenom+ " varchar(500),"+
            colonne_type+ " varchar(500),"+
            colonne_date_naissance+ " varchar(500),"+
            colonne_phone+ " varchar(500) )";

    // TODO : Table's Methods

    public static Long insert(Context context, Users u){
        ContentValues values = new ContentValues();
        values.put(colonne_id, Tool.KeyCodeGen());
        values.put(colonne_nom, u.getNom());
        values.put(colonne_prenom, u.getPrenom());
        values.put(colonne_type, u.getType());
        values.put(colonne_date_naissance, u.getBirthday());
        values.put(colonne_phone, u.getPhone());
        return ExecuteUpdate.insert(context, values, table_name);
    }

    public static ArrayList<Users> getall(Context context){

        ArrayList<Users> ret = new ArrayList<>();
        try{
            DataBase mabase = new DataBase(context, DataBase.db_name, null, DataBase.db_version);
            SQLiteDatabase base = mabase.getReadableDatabase();

            Cursor c = base.query(table_name, new String[]{
                    colonne_id,
                    colonne_nom,
                    colonne_prenom,
                    colonne_type,
                    colonne_date_naissance,
                    colonne_phone

            },null, null, null, null, null);
            Log.i("Client0".toUpperCase(), " Before" + c.getCount());
            if(c.moveToFirst()){
                do {
                    //Todo : A adapter a chaque type de reponse
                    //Todo : _______________________________________________
                    Users user = new Users();
                    user.setId(c.getString(0));
                    user.setNom(c.getString(1));
                    user.setPrenom(c.getString(2));
                    user.setType(c.getString(3));
                    user.setBirthday(c.getString(4));
                    user.setPhone(c.getString(5));
                    //Todo : _______________________________________________

                    ret.add(user);
                }while (c.moveToNext());
                Log.i("USER_GET".toUpperCase(), " After " + c.getCount());
                base.close();
                mabase.close();
                return ret;
            }
            else {
                Log.i("USER_GET2".toUpperCase(), " After2" + c.getCount());
                Log.i("USER_GET2".toUpperCase(), " no data");
                base.close();
                mabase.close();
                return null;
            }
        }catch (Exception e){
            Log.i("USER_GET_ERROR".toUpperCase(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
