package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "categorie")
public class Categorie extends Model {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;

    @JsonProperty("id")
    @Column(name = "remoteId")
    public long remoteId = -1;

    @Column(name = "designation")
    public String designation = "";

    @Column(name = "urlImage")
    public String urlImage = "";

    public List<SousCategorie> items() {
        return getMany(SousCategorie.class, "categorie");
    }

    public static Categorie find(int id) {
        return new Select().from(Categorie.class).where("id = ?", id).executeSingle();
    }

    public static List<Categorie> listCategorie() {
        return new Select().from(Categorie.class).execute();
    }

    public static void createCategories() {
        int id = 1;
        Categorie categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Bricolage";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Jardinage";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Démenagement";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Ménage";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Garde enfants";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Annimaux";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Informatique";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Consciergérie";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Sport";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Rencontre";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Auto";
        categorie.save();

        id++;
        categorie = new Categorie();
        categorie.remoteId = id;
        categorie.designation = "Coursieux";
        categorie.save();
    }

}
