package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postuler {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;
    public long id = -1;
    public long idAnnonce = -1;
    public String descriptionAnnonce = "";
    public float montant = 0;
    public String devise = "";
    public long idUser = -1;
    public String nomsUser = "";
    public String phoneUser = "";
    public String urlImageUser = "";
    public String date = "";
    public int statut = 0;
    public boolean isRDV = false;
    public boolean isConclu = false;
    public float montantConclu = 0;
    public String deviseConclu = "";
    public String dateRDV = "";
    public String heureRDV = "";

    public boolean havePostuled = false;

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

    public long getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(long idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getDescriptionAnnonce() {
        return descriptionAnnonce;
    }

    public void setDescriptionAnnonce(String descriptionAnnonce) {
        this.descriptionAnnonce = descriptionAnnonce;
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

    public boolean isHavePostuled() {
        return havePostuled;
    }

    public void setHavePostuled(boolean havePostuled) {
        this.havePostuled = havePostuled;
    }

    public boolean isRDV() {
        return isRDV;
    }

    public void setRDV(boolean RDV) {
        isRDV = RDV;
    }

    public boolean isConclu() {
        return isConclu;
    }

    public void setConclu(boolean conclu) {
        isConclu = conclu;
    }

    public float getMontantConclu() {
        return montantConclu;
    }

    public void setMontantConclu(float montantConclu) {
        this.montantConclu = montantConclu;
    }

    public String getDeviseConclu() {
        return deviseConclu;
    }

    public void setDeviseConclu(String deviseConclu) {
        this.deviseConclu = deviseConclu;
    }

    public String getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(String dateRDV) {
        this.dateRDV = dateRDV;
    }

    public String getHeureRDV() {
        return heureRDV;
    }

    public void setHeureRDV(String heureRDV) {
        this.heureRDV = heureRDV;
    }

}
