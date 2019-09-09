package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SousCategorie {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;
    public long id = -1;
    public String designation = "";
    public String description = "";
    public long idCategorie = -1;
    public String urlImage = "";

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

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
