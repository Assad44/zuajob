package cd.maichapayteam.zuajob.Back_end.Objects;

public class O_Annonces {

    // TODO : LES VARIABLEES
    public  String table_name_annonces= "CATEGORIE";
    public  String id_annonces = "id_annonces";
    public  String description_annonces= "DESCRIPTION";
    public  String montant_annonces= "montant_annonces";
    public  String devise_annonces= "devise_annonces";
    public  String date_publication_annonces= "date_publication_annonces";
    public  String is_confied_annonces= "is_confied_annonces";

    public  String user_id_annonces= "user_id_annonces";
    public  String user_name_annonces= "user_name_annonces";
    public  String user_phone_annonces= "user_phone_annonces";
    public  String user_url_img_annonces= "user_url_img_annonces";

    public  String sousCategorie_id                  = "id_sousCategorie";
    public  String sousCategorie_name                  = "sousCategorie_name";


    // TODO : LES GETTERS ET SETTERS

    public String getId_annonces() {
        return id_annonces;
    }

    public void setId_annonces(String id_annonces) {
        this.id_annonces = id_annonces;
    }

    public String getDescription_annonces() {
        return description_annonces;
    }

    public void setDescription_annonces(String description_annonces) {
        this.description_annonces = description_annonces;
    }

    public String getMontant_annonces() {
        return montant_annonces;
    }

    public void setMontant_annonces(String montant_annonces) {
        this.montant_annonces = montant_annonces;
    }

    public String getDevise_annonces() {
        return devise_annonces;
    }

    public void setDevise_annonces(String devise_annonces) {
        this.devise_annonces = devise_annonces;
    }

    public String getDate_publication_annonces() {
        return date_publication_annonces;
    }

    public void setDate_publication_annonces(String date_publication_annonces) {
        this.date_publication_annonces = date_publication_annonces;
    }

    public String getIs_confied_annonces() {
        return is_confied_annonces;
    }

    public void setIs_confied_annonces(String is_confied_annonces) {
        this.is_confied_annonces = is_confied_annonces;
    }

    public String getUser_id_annonces() {
        return user_id_annonces;
    }

    public void setUser_id_annonces(String user_id_annonces) {
        this.user_id_annonces = user_id_annonces;
    }

    public String getUser_name_annonces() {
        return user_name_annonces;
    }

    public void setUser_name_annonces(String user_name_annonces) {
        this.user_name_annonces = user_name_annonces;
    }

    public String getUser_phone_annonces() {
        return user_phone_annonces;
    }

    public void setUser_phone_annonces(String user_phone_annonces) {
        this.user_phone_annonces = user_phone_annonces;
    }

    public String getUser_url_img_annonces() {
        return user_url_img_annonces;
    }

    public void setUser_url_img_annonces(String user_url_img_annonces) {
        this.user_url_img_annonces = user_url_img_annonces;
    }

    public String getSousCategorie_id() {
        return sousCategorie_id;
    }

    public void setSousCategorie_id(String sousCategorie_id) {
        this.sousCategorie_id = sousCategorie_id;
    }

    public String getSousCategorie_name() {
        return sousCategorie_name;
    }

    public void setSousCategorie_name(String sousCategorie_name) {
        this.sousCategorie_name = sousCategorie_name;
    }
}
