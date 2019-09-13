package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import cd.maichapayteam.zuajob.Models.DAOClass.SousCategorieDAO;
import cd.maichapayteam.zuajob.Tools.GeneralClass;

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

    public static void createSousCategories() {
        SousCategorieDAO sousCategorieDAO = new SousCategorieDAO(GeneralClass.applicationContext);

        int id = 1;
        SousCategorie sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Aménagement";
        sousCategorie.idCategorie = 1;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Electricité";
        sousCategorie.idCategorie = 1;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Rénovation";
        sousCategorie.idCategorie = 1;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Plomberie";
        sousCategorie.idCategorie = 1;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Tondre la pelouse";
        sousCategorie.idCategorie = 2;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Couper un arbre";
        sousCategorie.idCategorie = 2;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Autre job de jardinage";
        sousCategorie.idCategorie = 2;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Ménage";
        sousCategorie.idCategorie = 4;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Repassage";
        sousCategorie.idCategorie = 4;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Lavage automobile";
        sousCategorie.idCategorie = 4;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Nettoyage de vitre";
        sousCategorie.idCategorie =4;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Autre job de nettoyage";
        sousCategorie.idCategorie = 4;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Femme de ménage";
        sousCategorie.idCategorie = 4;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Homme de ménage";
        sousCategorie.idCategorie = 4;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Déplacer un meuble";
        sousCategorie.idCategorie = 3;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Déplacer de l'électroménager";
        sousCategorie.idCategorie = 3;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Aide au déménagement";
        sousCategorie.idCategorie = 3;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Autres job de déménagement";
        sousCategorie.idCategorie = 3;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Garde des enfants";
        sousCategorie.idCategorie = 5;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Garde de chien";
        sousCategorie.idCategorie = 6;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Garde de chat";
        sousCategorie.idCategorie = 6;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Faire promener son chien";
        sousCategorie.idCategorie = 6;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Vétériner";
        sousCategorie.idCategorie = 6;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Nettoyer mon ordinateur";
        sousCategorie.idCategorie = 7;
        sousCategorieDAO.ajouter(sousCategorie);
//
        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Cours informatique";
        sousCategorie.idCategorie = 7;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Installer une imprimante";
        sousCategorie.idCategorie = 7;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Autres job d'informatique";
        sousCategorie.idCategorie = 7;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Serveur / serveuse";
        sousCategorie.idCategorie = 8;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Organiser un événément";
        sousCategorie.idCategorie = 8;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Couture";
        sousCategorie.idCategorie = 8;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Cuisinier";
        sousCategorie.idCategorie = 8;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Coach";
        sousCategorie.idCategorie = 9;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Club de sport";
        sousCategorie.idCategorie = 9;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Homme";
        sousCategorie.idCategorie = 10;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Femme";
        sousCategorie.idCategorie = 10;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Chauffeur journalier";
        sousCategorie.idCategorie = 11;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Mécanicien";
        sousCategorie.idCategorie = 11;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Autres service auto";
        sousCategorie.idCategorie = 11;
        sousCategorieDAO.ajouter(sousCategorie);

        id++;
        sousCategorie = new SousCategorie();
        sousCategorie.id = id;
        sousCategorie.designation = "Course super marché";
        sousCategorie.idCategorie = 12;
        sousCategorieDAO.ajouter(sousCategorie);
    }

}
