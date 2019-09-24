package cd.maichapayteam.zuajob.Models.Object;

import android.graphics.ColorSpace;
import android.widget.ArrayAdapter;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private boolean error = false;
    private String errorMessage = "";
    private int errorCode = 0;
    private long id = -1;
    private String authCode = "";
    private String prenom = "";
    private String nom = "";
    private String phone = "";
    private String adresse = "";
    private String pays = "";
    private String ville = "";
    private String quartier = "";
    private String urlPhoto = "";
    private String urlThumbnail = "";
    private boolean identiteVerifie = false;
    private String birthday = "";
    private String description = "";
    private int cote = 0;
    private int typeIdentite = 0;
    private String numIdentite = "";

    @JsonIgnoreProperties
    private boolean myProfil = false;
    private int type = 0;
    private String email = "";
    private String password = "";
    private String sexe = "";
    private String codePays = "+243";
    private String commune = "";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public boolean isIdentiteVerifie() {
        return identiteVerifie;
    }

    public void setIdentiteVerifie(boolean identiteVerifie) {
        this.identiteVerifie = identiteVerifie;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getTypeIdentite() {
        return typeIdentite;
    }

    public void setTypeIdentite(int typeIdentite) {
        this.typeIdentite = typeIdentite;
    }

    public String getNumIdentite() {
        return numIdentite;
    }

    public void setNumIdentite(String numIdentite) {
        this.numIdentite = numIdentite;
    }

    public boolean isMyProfil() {
        return myProfil;
    }

    public void setMyProfil(boolean myProfil) {
        this.myProfil = myProfil;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();

        try { jsonObject.put("id", id); } catch (JSONException e) { }
        try { jsonObject.put("authCode", authCode); } catch (JSONException e) { }
        try { jsonObject.put("prenom", prenom); } catch (JSONException e) { }
        try { jsonObject.put("nom", nom); } catch (JSONException e) { }
        try { jsonObject.put("phone", phone); } catch (JSONException e) { }
        try { jsonObject.put("adresse", adresse); } catch (JSONException e) { }
        try { jsonObject.put("pays", pays); } catch (JSONException e) { }
        try { jsonObject.put("ville", ville); } catch (JSONException e) { }
        try { jsonObject.put("quartier", quartier); } catch (JSONException e) { }
        try { jsonObject.put("urlPhoto", urlPhoto); } catch (JSONException e) { }
        try { jsonObject.put("identiteVerifie", identiteVerifie); } catch (JSONException e) { }
        try { jsonObject.put("birthday", birthday); } catch (JSONException e) { }
        try { jsonObject.put("description", description); } catch (JSONException e) { }
        try { jsonObject.put("cote", cote); } catch (JSONException e) { }
        try { jsonObject.put("numIdentite", numIdentite); } catch (JSONException e) { }
        try { jsonObject.put("type", type); } catch (JSONException e) { }
        try { jsonObject.put("email", email); } catch (JSONException e) { }
        try { jsonObject.put("password", password); } catch (JSONException e) { }
        try { jsonObject.put("sexe", sexe); } catch (JSONException e) { }
        try { jsonObject.put("codePays", codePays); } catch (JSONException e) { }
        try { jsonObject.put("commune", commune); } catch (JSONException e) { }
        try { jsonObject.put("description", description); } catch (JSONException e) { }
        try { jsonObject.put("urlThumbnail", urlThumbnail); } catch (JSONException e) { }

        return jsonObject;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("error: ");
        stringBuilder.append(error);
        stringBuilder.append("\n");
        stringBuilder.append("Id: ");
        stringBuilder.append(id);
        stringBuilder.append("\n");
        stringBuilder.append("Prenom: ");
        stringBuilder.append(prenom);
        stringBuilder.append("\n");
        stringBuilder.append("Nom: ");
        stringBuilder.append(this.getNom());
        stringBuilder.append("\n");
        stringBuilder.append("Password: ");
        stringBuilder.append(this.getPassword());
        stringBuilder.append("\n");
        stringBuilder.append("Code pays: ");
        stringBuilder.append(this.getCodePays());
        stringBuilder.append("\n");
        stringBuilder.append("Description: ");
        stringBuilder.append(this.getDescription());
        stringBuilder.append("\n");
        stringBuilder.append("Adresse: ");
        stringBuilder.append(this.getAdresse());
        stringBuilder.append("\n");
        stringBuilder.append("Auth code: ");
        stringBuilder.append(this.getAuthCode());
        stringBuilder.append("\n");
        stringBuilder.append("Birthday: ");
        stringBuilder.append(this.getBirthday());
        stringBuilder.append("\n");
        stringBuilder.append("Commune: ");
        stringBuilder.append(this.getCommune());
        stringBuilder.append("\n");
        stringBuilder.append("Email: ");
        stringBuilder.append(this.getEmail());
        stringBuilder.append("\n");
        stringBuilder.append("Num identité: ");
        stringBuilder.append(this.getNumIdentite());
        stringBuilder.append("\n");
        stringBuilder.append("Phone: ");
        stringBuilder.append(this.getPhone());
        stringBuilder.append("\n");
        stringBuilder.append("Quartier: ");
        stringBuilder.append(this.getQuartier());
        stringBuilder.append("\n");
        stringBuilder.append("Sexe: ");
        stringBuilder.append(this.getSexe());
        stringBuilder.append("\n");
        stringBuilder.append("Type: ");
        stringBuilder.append(this.getType());
        stringBuilder.append("\n");
        stringBuilder.append("Type identité: ");
        stringBuilder.append(this.getTypeIdentite());
        stringBuilder.append("\n");
        stringBuilder.append("Url: ");
        stringBuilder.append(this.getUrlPhoto());
        stringBuilder.append("\n");
        stringBuilder.append("Ville: ");
        stringBuilder.append(this.getVille());
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

}
