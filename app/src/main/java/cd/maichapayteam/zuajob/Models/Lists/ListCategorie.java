package cd.maichapayteam.zuajob.Models.Lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.Object.Categorie;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCategorie {

    private boolean error = true;
    private int errorCode = 0;
    private String errorMessage = "";
    private List<Categorie> liste = new ArrayList<>();

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Categorie> getListe() {
        return liste;
    }

    public void setListe(List<Categorie> liste) {
        this.liste = liste;
    }

}
