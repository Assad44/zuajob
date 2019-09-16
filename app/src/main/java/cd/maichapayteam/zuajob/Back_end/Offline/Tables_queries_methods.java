package cd.maichapayteam.zuajob.Back_end.Offline;

import cd.maichapayteam.zuajob.Back_end.Objects.O_Annonces;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Categories;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Services;
import cd.maichapayteam.zuajob.Back_end.Objects.O_Sous_Categories;


public class Tables_queries_methods {

    // TODO : Created tables categorie Query
    public static String create_table_Categorie(){
        O_Categories c = new O_Categories();
        return "create table "+c.table_name_categorie+" (" +
                c.id_categorie+ " varchar(25) primary key, " +
                c.designation_categorie+ " varchar(500),"+
                c.description_categorie+ " varchar(500),"+
                c.urlImage_categorie+ " varchar(500) )";
    }
    // TODO : Created tables souscategorie Query
    public static String create_table_Souscategorie(){
        O_Sous_Categories  S = new O_Sous_Categories();
        return "create table "+S.table_name_souscategorie+" (" +
                S.id_souscategorie+ " varchar(25) primary key, " +
                S.designation_souscategorie+ " varchar(500),"+
                S.description_souscategorie+ " varchar(500),"+
                S.urlImage_souscategorie+ " varchar(500) )";
    }


    // TODO : Created tables anonces Query
    public static String create_table_Annonces(){
        O_Annonces A = new O_Annonces();
        return "create table "+A.table_name_annonces+" (" +
                A.id_annonces+ " varchar(25) primary key, " +
                A.description_annonces+ " varchar(500),"+
                A.montant_annonces+ " varchar(500),"+
                A.devise_annonces+ " varchar(500),"+
                A.date_publication_annonces+ " varchar(500),"+
                A.is_confied_annonces+ " varchar(500),"+
                A.user_id_annonces+ " varchar(500),"+
                A.user_name_annonces+ " varchar(500),"+
                A.user_phone_annonces+ " varchar(500),"+
                A.user_url_img_annonces+ " varchar(500),"+
                A.sousCategorie_id+ " varchar(500),"+
                A.sousCategorie_name+ " varchar(500) )";
    }

    // TODO : Created tables services Query
    public static String create_table_Services(){
        O_Services S = new O_Services();
        return "create table "+S.table_name_services+" (" +
                S.id_services+ " varchar(25) primary key, " +
                S.description_services+ " varchar(500),"+
                S.montant_services+ " varchar(500),"+
                S.devise_services+ " varchar(500),"+
                S.date_publication_services+ " varchar(500),"+
                S.nbr_realisation_services+ " varchar(500),"+
                S.code_services+ " varchar(500),"+
                S.jober_id_services+ " varchar(500),"+
                S.jober_name_services+ " varchar(500),"+
                S.jober_phone_services+ " varchar(500),"+
                S.jober_url_img_services+ " varchar(500),"+
                S.sousCategorie_id_services+ " varchar(500),"+
                S.sousCategorie_name_services+ " varchar(500) )";
    }



}
