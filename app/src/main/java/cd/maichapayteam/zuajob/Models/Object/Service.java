package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.json.JSONException;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {

    private boolean error = false;
    private String errorMessage = "";
    private int errorCode = 0;
    private long id = -1;
    private long idSousCategorie = -1;
    private String sousCategorie = "";
    private String description = "";
    private String devise = "";
    private float montant = 0;
    private long idJobeur = -1;
    private String nomsJobeur = "";
    private String phoneJobeur = "";
    private String urlImageJobeur = "";
    private String datePublication = "";
    private long idCategorie = -1;
    private String categorie = "";
    private int nombreRealisation = 0;
    private int cote = 0;

    public boolean isMy() {
        return isMy;
    }

    public void setMy(boolean my) {
        isMy = my;
    }

    public boolean isMy = false;

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

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();

        try { jsonObject.put("id", id); } catch (JSONException e) { }
        try { jsonObject.put("idSousCategorie", idSousCategorie); } catch (JSONException e) { }
        try { jsonObject.put("description", description); } catch (JSONException e) { }
        try { jsonObject.put("devise", devise); } catch (JSONException e) { }
        try { jsonObject.put("montant", montant); } catch (JSONException e) { }

        return jsonObject;
    }

}
