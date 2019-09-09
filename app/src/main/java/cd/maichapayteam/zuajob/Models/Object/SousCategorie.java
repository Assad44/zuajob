package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sous_categorie")
public class SousCategorie extends Model {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;

    @JsonProperty("id")
    @Column(name = "remoteId")
    public long remoteId = -1;

    @Column(name = "designation")
    public String designation = "";

    @Column(name = "idCategorie")
    public long idCategorie = -1;

    @Column(name = "urlImage")
    public String urlImage = "";

    @JsonIgnoreProperties
    @Column(name = "categorie")
    public Categorie categorie;

    //public static List<SousCategorie> listSousCategorie(Categorie categorie) {
    //    return new Select().from(SousCategorie.class).where("categorie = ", categorie).execute();
    //}
//
    //public static void createSousCategories() {
    //    int id = 1;
    //    SousCategorie sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Aménagement";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Electricité";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Rénovation";
    //    sousCategorie.categorie = Categorie.find(1);
    //    sousCategorie.save();
//
    //    id++;
    //    sousCategorie = new SousCategorie();
    //    sousCategorie.remoteId = id;
    //    sousCategorie.designation = "Lessive";
    //    sousCategorie.categorie = Categorie.find(2);
    //    sousCategorie.save();
    //}

}
