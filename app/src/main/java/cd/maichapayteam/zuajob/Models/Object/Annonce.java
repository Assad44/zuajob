package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Annonce {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;
    public long id = -1;
    public long idSousCategorie = -1;
    public String sousCategorie = "";
    public String description = "";
    public String devise = "";
    public float montant = 0;
    public long idUser = -1;
    public String nomsUser = "";
    public String phoneUser = "";
    public String urlImageUser = "";
    public long idCategorie = -1;
    public String categorie = "";
    public int nombreRealisation = 0;
    public int cote = 0;

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

    public long getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(long idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public String getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(String sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getNomsUser() {
        return nomsUser;
    }

    public void setNomsUser(String nomsUser) {
        this.nomsUser = nomsUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getUrlImageUser() {
        return urlImageUser;
    }

    public void setUrlImageUser(String urlImageUser) {
        this.urlImageUser = urlImageUser;
    }

    public long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNombreRealisation() {
        return nombreRealisation;
    }

    public void setNombreRealisation(int nombreRealisation) {
        this.nombreRealisation = nombreRealisation;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }

}
