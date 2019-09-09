package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Categorie extends Model {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;
    public long id = -1;
    public String designation = "";
    public String urlImage = "";

    //public static Categorie find(int id) {
    //    return new Select().from(Categorie.class).where("id = ?", id).executeSingle();
    //}
//
    //public static List<Categorie> listCategorie() {
    //    return new Select().from(Categorie.class).execute();
    //}

}
