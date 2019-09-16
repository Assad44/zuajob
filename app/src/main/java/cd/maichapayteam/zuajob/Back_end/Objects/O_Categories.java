package cd.maichapayteam.zuajob.Back_end.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import cd.maichapayteam.zuajob.Back_end.Offline.DataBase;
import cd.maichapayteam.zuajob.Back_end.Offline.ExecuteUpdate;

public class O_Categories {

    // TODO : LES VARIABLEES
    public String table_name_categorie                = "CATEGORIE";
    public String id_categorie                        = "id_categorie";
    public String designation_categorie               = "DESIGNATION";
    public String description_categorie               = "DESCRIPTION";
    public String urlImage_categorie                  = "URL_img";

    // TODO : LES GETTERS ET SETTERS

    public String getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(String id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getDesignation_categorie() {
        return designation_categorie;
    }

    public void setDesignation_categorie(String designation_categorie) {
        this.designation_categorie = designation_categorie;
    }

    public String getDescription_categorie() {
        return description_categorie;
    }

    public void setDescription_categorie(String description_categorie) {
        this.description_categorie = description_categorie;
    }

    public String getUrlImage_categorie() {
        return urlImage_categorie;
    }

    public void setUrlImage_categorie(String urlImage_categorie) {
        this.urlImage_categorie = urlImage_categorie;
    }


    // TODO : METHODES

    public long insert(Context context, O_Categories p){
        ContentValues values = new ContentValues();
        values.put(id_categorie, p.getId_categorie() );
        values.put(designation_categorie, p.getDesignation_categorie());
        values.put(description_categorie, p.getDescription_categorie());
        values.put(urlImage_categorie, p.getUrlImage_categorie());
        return ExecuteUpdate.insert(context, values, table_name_categorie);
    }

    public ArrayList<O_Categories> getall_categorie(Context context){

        ArrayList<O_Categories> ret = new ArrayList<>();
        try{
            DataBase mabase = new DataBase(context, DataBase.db_name, null, DataBase.db_version);
            SQLiteDatabase base = mabase.getReadableDatabase();
            O_Categories column = new O_Categories();
            Cursor c = base.query(column.table_name_categorie, new String[]{
                    column.id_categorie,
                    column.designation_categorie,
                    column.description_categorie,
                    column.urlImage_categorie
            },null, null, null, null, null);
            Log.i("Client0".toUpperCase(), " Before" + c.getCount());
            if(c.moveToFirst()){
                do {
                    O_Categories C = new O_Categories();
                    C.setId_categorie(c.getString(0));
                    C.setDesignation_categorie(c.getString(1));
                    C.setDescription_categorie(c.getString(2));
                    C.setUrlImage_categorie(c.getString(3));
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
