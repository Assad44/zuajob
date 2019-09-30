package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.json.JSONException;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postuler {

    private boolean error = false;
    private String errorMessage = "";
    private int errorCode = 0;
    private long id = -1;
    private long idAnnonce = -1;
    private String descriptionAnnonce = "";
    private float montantAnnonce = 0;
    private String deviseAnnonce = "";
    private long idUser = -1;
    private String nomsUser = "";
    private String phoneUser = "";
    private String urlImageUser = "";
    private String date = "";
    private int statut = 0;
    private boolean isRDV = false;
    private boolean isConclu = false;
    private float montantConclu = 0;
    private String deviseConclu = "";
    private String dateRDV = "";
    private String heureRDV = "";
    private int cote = 0;
    private String comment = "";

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isRefused() {
        return isRefused;
    }

    public void setRefused(boolean refused) {
        isRefused = refused;
    }

    private boolean isAccepted = false;
    private boolean isRefused = false;

    public String getDetailRDV() {
        return detailRDV;
    }

    public void setDetailRDV(String detailRDV) {
        this.detailRDV = detailRDV;
    }

    private String detailRDV = "";
    private boolean havePostuled = false;
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

    public float getMontantAnnonce() {
        return montantAnnonce;
    }

    public void setMontantAnnonce(float montantAnnonce) {
        this.montantAnnonce = montantAnnonce;
    }

    public String getDeviseAnnonce() {
        return deviseAnnonce;
    }

    public void setDeviseAnnonce(String deviseAnnonce) {
        this.deviseAnnonce = deviseAnnonce;
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

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();

        try { jsonObject.put("id", id); } catch (JSONException e) { }
        try { jsonObject.put("idAnnonce", idAnnonce); } catch (JSONException e) { }

        return jsonObject;
    }

}
