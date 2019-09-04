package cd.maichapayteam.zuajob.Models.Object;

import android.graphics.ColorSpace;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "user")
public class User extends Model {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;

    @JsonProperty("id")
    public long remoteId = -1;

    public String authCode = "";
    public String prenom = "";
    public String nom = "";
    public int phone = 0;
    public String adresse = "";
    public String pays = "";
    public String ville = "";
    public String quartier = "";
    public String urlPhoto = "";
    public boolean identiteVerifie = false;
    public long birthday = 0;

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

    public static User find(int id) {
        return new Select().from(User.class).where("remoteId = ?", id).executeSingle();
    }

    public static User myProfile() {
        return new Select().from(User.class).where("myProfil = ?", true).executeSingle();
    }
}
