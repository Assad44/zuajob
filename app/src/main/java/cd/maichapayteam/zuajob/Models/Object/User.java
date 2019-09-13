package cd.maichapayteam.zuajob.Models.Object;

import android.graphics.ColorSpace;
import android.widget.ArrayAdapter;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "user")
public class User {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;
    public long id = -1;
    public String authCode = "";
    public String prenom = "";
    public String nom = "";
    public String phone = "";
    public String adresse = "";
    public String pays = "";
    public String ville = "";
    public String quartier = "";
    public String urlPhoto = "";
    public boolean identiteVerifie = false;
    public String birthday = "";

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

    public String description = "";
    public int cote = 0;

    @JsonIgnoreProperties
    public int typeIdentite = 0;

    @JsonIgnoreProperties
    public String numIdentite = "";

    @JsonIgnoreProperties
    public boolean myProfil = false;

    public int type = 0;
    public String email = "";
    public String password = "";
    public String sexe = "";
    public String codePays = "+243";
    public String commune = "";
    public String about = "";

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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    //public static User find(int id) {
    //    return new Select().from(User.class).where("remoteId = ?", id).executeSingle();
    //}
//
    //public static User findByPhoneNumer(String phone) {
    //    return new Select().from(User.class).where("phone = ?", Integer.parseInt(phone)).executeSingle();
    //}
//
    //public static User myProfile() {
    //    return new Select().from(User.class).where("myProfil = ?", 1).executeSingle();
    //}
//
    //public static boolean deconnect() {
    //    User user = myProfile();
    //    if(user!=null) {
    //        user.delete();
    //    }
    //    return true;
    //}
//
    //public static List<User> listJobeurs(int next) {
    //    next *= 20;
    //    return new Select().from(User.class).where("type = ?", 1).limit(next + ", 20").execute();
    //}
//
    //public static List<User> listJobeurs() {
    //    return new Select().from(User.class).where("type = ?", 1).execute();
    //}

}
