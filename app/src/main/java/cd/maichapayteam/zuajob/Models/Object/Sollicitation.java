package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.json.JSONException;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sollicitation {

    private boolean aujourd = false;
    private boolean error = false;
    private String errorMessage = "";
    private int errorCode = 0;
    private long id = -1;
    private long idService = -1;
    private String descriptionService = "";
    private String categorie = "";
    private String souscategorie = "";
    private float montant = 0;
    private String devise = "";
    private long idUser = -1;
    private String nomsUser = "";
    private String phoneUser = "";
    private String urlImageUser = "";
    private String date = "";
    private int statut = 0;
    private boolean haveSollicited = false;
    private boolean isRDV = false;
    private boolean isConclu = false;
    private float montantConclu = 0;
    private String deviseConclu = "";
    private String dateRDV = "";
    private String heureRDV = "";
    private int cote = 0;
    private String comment = "";

    public boolean isWaitingPayement() {
        return waitingPayement;
    }

    public void setWaitingPayement(boolean waitingPayement) {
        this.waitingPayement = waitingPayement;
    }

    private boolean waitingPayement = false;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    private String html = "";

    private int idCategorie = -1;

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(int idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    private int idSousCategorie = -1;

    public String getDetailRDV() {
        return detailRDV;
    }

    public void setDetailRDV(String detailRDV) {
        this.detailRDV = detailRDV;
    }

    private String detailRDV = "";
    private boolean isMy = false;
    private boolean isAccepted = false;
    private boolean isRefused = false;
    private boolean recent = false;

    public boolean isAujourd() {
        return aujourd;
    }

    public void setAujourd(boolean aujourd) {
        this.aujourd = aujourd;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        this.isAccepted = accepted;
    }

    public boolean isRefused() {
        return isRefused;
    }

    public void setRefused(boolean refused) {
        this.isRefused = refused;
    }

    public boolean isMy() {
        return isMy;
    }

    public void setMy(boolean my) {
        isMy = my;
    }


    public boolean isRecent() {
        return recent;
    }

    public void setRecent(boolean recent) {
        this.recent = recent;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSouscategorie() {
        return souscategorie;
    }

    public void setSouscategorie(String souscategorie) {
        this.souscategorie = souscategorie;
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

    public boolean isHaveSollicited() {
        return haveSollicited;
    }

    public void setHaveSollicited(boolean haveSollicited) {
        this.haveSollicited = haveSollicited;
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
        try { jsonObject.put("idService", idService); } catch (JSONException e) { }

        return jsonObject;
    }

}
