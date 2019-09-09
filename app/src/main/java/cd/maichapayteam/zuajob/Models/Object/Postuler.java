package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postuler {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;
    public long id = -1;
    public long idService = -1;
    public String descriptionService = "";
    public float montant = 0;
    public String devise = "";
    public long idJobeur = -1;
    public String nomsJobeur= "";
    public String phoneJobeur = "";
    public String urlImageJobeur = "";
    public String date = "";
    public int statut = 0;

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

    public long getIdService() {
        return idService;
    }

    public void setIdService(long idService) {
        this.idService = idService;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public long getIdJobeur() {
        return idJobeur;
    }

    public void setIdJobeur(long idJobeur) {
        this.idJobeur = idJobeur;
    }

    public String getNomsJobeur() {
        return nomsJobeur;
    }

    public void setNomsJobeur(String nomsJobeur) {
        this.nomsJobeur = nomsJobeur;
    }

    public String getPhoneJobeur() {
        return phoneJobeur;
    }

    public void setPhoneJobeur(String phoneJobeur) {
        this.phoneJobeur = phoneJobeur;
    }

    public String getUrlImageJobeur() {
        return urlImageJobeur;
    }

    public void setUrlImageJobeur(String urlImageJobeur) {
        this.urlImageJobeur = urlImageJobeur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

}
