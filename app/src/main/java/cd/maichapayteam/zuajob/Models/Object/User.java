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
public class User extends Model {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;

    @JsonProperty("id")
    @Column(name = "remoteId")
    public long remoteId = -1;

    @Column(name = "authCode")
    public String authCode = "";

    @Column(name = "prenom")
    public String prenom = "";

    @Column(name = "nom")
    public String nom = "";

    @Column(name = "phone")
    public int phone = 0;

    @Column(name = "adresse")
    public String adresse = "";

    @Column(name = "pays")
    public String pays = "";

    @Column(name = "ville")
    public String ville = "";

    @Column(name = "quartier")
    public String quartier = "";

    @Column(name = "urlPhoto")
    public String urlPhoto = "";

    @Column(name = "identiteVerifie")
    public boolean identiteVerifie = false;

    @Column(name = "birthday")
    public long birthday = 0;

    @JsonIgnoreProperties
    @Column(name = "typeIdentite")
    public int typeIdentite = 0;

    @JsonIgnoreProperties
    @Column(name = "numIdentite")
    public String numIdentite = "";

    @JsonIgnoreProperties
    @Column(name = "myProfil")
    public boolean myProfil = false;

    //public ArrayList<Categorie> prefferences = new ArrayList<>();

    @Column(name = "type")
    public int type = 0;

    @Column(name = "email")
    public String email = "";

    @Column(name = "password")
    public String password = "";

    @Column(name = "sexe")
    public String sexe = "";

    @Column(name = "codePays")
    public String codePays = "+243";

    @Column(name = "commune")
    public String commune = "";

    @Column(name = "about")
    public String about = "";

    public static User find(int id) {
        return new Select().from(User.class).where("remoteId = ?", id).executeSingle();
    }

    public static User findByPhoneNumer(String phone) {
        return new Select().from(User.class).where("phone = ?", Integer.parseInt(phone)).executeSingle();
    }

    public static User myProfile() {
        return new Select().from(User.class).where("myProfil = ?", 1).executeSingle();
    }

    public static boolean deconnect() {
        /*User user = myProfile();
        if(user!=null) {
            user.delete();
        }*/
        return true;
    }

    public static List<User> listJobeurs(int next) {
        next *= 20;
        return new Select().from(User.class).where("type = ?", 1).limit(next + ", 20").execute();
    }

    public static List<User> listJobeurs() {
        return new Select().from(User.class).where("type = ?", 1).execute();
    }




}
