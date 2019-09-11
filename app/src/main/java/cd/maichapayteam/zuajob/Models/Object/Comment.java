package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    public boolean error = false;
    public String errorMessage = "";
    public int errorCode = 0;
    long id = -1;
    String comment = "";
    long idUser = -1;
    String nomsUser = "";
    String urlImageUser = "";
    long idUserConcerne = -1;
    String nomsUserConcerne = "";
    String urlImageUserConcerne = "";
    String date = "";

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNomsUser() {
        return nomsUser;
    }

    public void setNomsUser(String nomsUser) {
        this.nomsUser = nomsUser;
    }

    public String getUrlImageUser() {
        return urlImageUser;
    }

    public void setUrlImageUser(String urlImageUser) {
        this.urlImageUser = urlImageUser;
    }

    public long getIdUserConcerne() {
        return idUserConcerne;
    }

    public void setIdUserConcerne(long idUserConcerne) {
        this.idUserConcerne = idUserConcerne;
    }

    public String getNomsUserConcerne() {
        return nomsUserConcerne;
    }

    public void setNomsUserConcerne(String nomsUserConcerne) {
        this.nomsUserConcerne = nomsUserConcerne;
    }

    public String getUrlImageUserConcerne() {
        return urlImageUserConcerne;
    }

    public void setUrlImageUserConcerne(String urlImageUserConcerne) {
        this.urlImageUserConcerne = urlImageUserConcerne;
    }

}
