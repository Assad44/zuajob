package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Annonce {

    private boolean error = false;
    private String errorMessage = "";
    private int errorCode = 0;
    private long id = -1;
    private long idSousCategorie = -1;
    private String sousCategorie = "";
    private String description = "";
    private String devise = "";
    private float montant = 0;
    private long idUser = -1;
    private String nomsUser = "";
    private String phoneUser = "";
    private String urlImageUser = "";
    private long idCategorie = -1;
    private String categorie = "";
    private String datePublication = "";
    private boolean isConfied = false;
    private boolean isMy = false;

    public boolean isMy() {
        return isMy;
    }

    public void setMy(boolean my) {
        isMy = my;
    }

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

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
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

    public boolean isConfied() {
        return isConfied;
    }

    public void setConfied(boolean confied) {
        isConfied = confied;
    }

}
