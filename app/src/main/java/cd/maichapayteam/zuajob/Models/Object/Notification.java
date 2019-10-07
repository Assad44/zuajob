package cd.maichapayteam.zuajob.Models.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {

    private boolean error = false;
    private String errorMessage = "";
    private int errorCode = 0;
    private long id = -1;
    private long idUser = -1;
    private long idObject = -1;
    private String typeObject = "";
    private String typeNotification = "";
    private String message = "";
    private String time = "";
    private Postuler postulance = null;
    private Sollicitation sollicitation = null;

    public boolean isReaded() {
        return isReaded;
    }

    public void setReaded(boolean readed) {
        isReaded = readed;
    }

    private boolean isReaded = false;

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

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdObject() {
        return idObject;
    }

    public void setIdObject(long idObject) {
        this.idObject = idObject;
    }

    public String getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(String typeObject) {
        this.typeObject = typeObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(String typeNotification) {
        this.typeNotification = typeNotification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Postuler getPostulance() {
        return postulance;
    }

    public void setPostulance(Postuler postulance) {
        this.postulance = postulance;
    }

    public Sollicitation getSollicitation() {
        return sollicitation;
    }

    public void setSollicitation(Sollicitation sollicitation) {
        this.sollicitation = sollicitation;
    }

}
