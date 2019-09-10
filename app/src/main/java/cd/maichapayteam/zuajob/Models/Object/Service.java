package cd.maichapayteam.zuajob.Models.Object;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Tools.RemoteDataSync;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;
    public long id = -1;
    public long idSousCategorie = -1;
    public String sousCategorie = "";
    public String description = "";
    public String devise = "";
    public float montant = 0;
    public long idJobeur = -1;
    public String nomsJobeur = "";
    public String phoneJobeur = "";
    public String urlImageJobeur = "";
    public long idCategorie = -1;
    public String categorie = "";
    public int nombreRealisation = 0;
    public int cote = 0;

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

    public static ArrayList<Service> listService() {
        ArrayList<Service> list = new ArrayList<>();
        //List<User> listPrestateur = User.listJobeurs();
        List<User> listPrestateur = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            //Service service = new Service();
            //service.remoteId = i++;
            //service.description = RemoteDataSync.getRandomParagraphe(new Random().nextInt(9) + 1);
            //service.montant = new Random().nextFloat();
            ////service.cotes = Cote.getListCote();
            //service.nombreRealisation = new Random().nextInt();
            //service.prestateur = listPrestateur.get(new Random().nextInt(listPrestateur.size())); //
            ////service.devise = GeneralClass.getDAleatoire();
            //service.sousCategorie = GeneralClass.getDAleatoireSCat(); //
            //list.add(service);
        }
        return list;
    }

}
