package cd.maichapayteam.zuajob.BackEnd.Objects;

public class Services {

    // Variables
    public String id;
    public String nom_user;
    public String description_services;
    public String nbr_cote;
    public String nbr_services;
    public String prix;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getDescription_services() {
        return description_services;
    }

    public void setDescription_services(String description_services) {
        this.description_services = description_services;
    }

    public String getNbr_cote() {
        return nbr_cote;
    }

    public void setNbr_cote(String nbr_cote) {
        this.nbr_cote = nbr_cote;
    }

    public String getNbr_services() {
        return nbr_services;
    }

    public void setNbr_services(String nbr_services) {
        this.nbr_services = nbr_services;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
