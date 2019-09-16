package cd.maichapayteam.zuajob.Back_end.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Back_end.Offline.DataBase;
import cd.maichapayteam.zuajob.Back_end.Offline.ExecuteUpdate;

public class O_Sous_Categories {

    // TODO : LES VARIABLEES
    public String table_name_souscategorie                = "CATEGORIE";
    public String id_souscategorie                        = "id_souscategorie";
    public String designation_souscategorie               = "DESIGNATION";
    public String description_souscategorie               = "DESCRIPTION";
    public String urlImage_souscategorie                  = "URL_img";
    public String id_categorie                            = "id_cat";

    // TODO : LES GETTERS ET SETTERS

    public String getId_souscategorie() {
        return id_souscategorie;
    }

    public void setId_souscategorie(String id_souscategorie) {
        this.id_souscategorie = id_souscategorie;
    }

    public String getDesignation_souscategorie() {
        return designation_souscategorie;
    }

    public void setDesignation_souscategorie(String designation_souscategorie) {
        this.designation_souscategorie = designation_souscategorie;
    }

    public String getDescription_souscategorie() {
        return description_souscategorie;
    }

    public void setDescription_souscategorie(String description_souscategorie) {
        this.description_souscategorie = description_souscategorie;
    }

    public String getUrlImage_souscategorie() {
        return urlImage_souscategorie;
    }

    public void setUrlImage_souscategorie(String urlImage_souscategorie) {
        this.urlImage_souscategorie = urlImage_souscategorie;
    }

    public String getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(String id_categorie) {
        this.id_categorie = id_categorie;
    }


    // TODO : METHODES

    public long insert(Context context, O_Sous_Categories p){
        ContentValues values = new ContentValues();
        values.put(id_souscategorie, p.getId_souscategorie() );
        values.put(designation_souscategorie, p.getDesignation_souscategorie());
        values.put(description_souscategorie, p.getDescription_souscategorie());
        values.put(urlImage_souscategorie, p.getUrlImage_souscategorie());
        return ExecuteUpdate.insert(context, values, table_name_souscategorie);
    }

    public ArrayList<O_Sous_Categories> getall_souscategorie(Context context, String id_cat){

        ArrayList<O_Sous_Categories> ret = new ArrayList<>();
        try{
            DataBase mabase = new DataBase(context, DataBase.db_name, null, DataBase.db_version);
            SQLiteDatabase base = mabase.getReadableDatabase();
            O_Sous_Categories column = new O_Sous_Categories();
            Cursor c = base.query(column.table_name_souscategorie, new String[]{
                    column.id_souscategorie,
                    column.designation_souscategorie,
                    column.description_souscategorie,
                    column.urlImage_souscategorie
            },id_categorie +" = ' "+id_cat+" '", null, null, null, null);
            Log.i("Client0".toUpperCase(), " Before" + c.getCount());
            if(c.moveToFirst()){
                do {
                    O_Sous_Categories C = new O_Sous_Categories();
                    C.setId_souscategorie(c.getString(0));
                    C.setDesignation_souscategorie(c.getString(1));
                    C.setDescription_souscategorie(c.getString(2));
                    C.setUrlImage_souscategorie(c.getString(3));
                    ret.add(C);
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
