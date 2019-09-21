package cd.maichapayteam.zuajob.Tools;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.DAOClass.AnnonceDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.CategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.PostulerDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.ServiceDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SollicitationDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SousCategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Models.Object.User;

public class ManageLocalData {

    public static User createUser(User user) {
        //user.authCode = generate(32);
        //user.myProfil = true;
        //UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        //userDAO.ajouter(user);
        //RemoteDataSync.getRandomUser();

        return RemoteDataSync.createUser(user);
    }

    public static boolean checkNumero(String numero) {
        //UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        //User user = userDAO.findByPhoneNumer(numero);
        //if(user!=null) return true;
        //return false;
        return RemoteDataSync.checkNumero(numero);
    }

    public static String confirmCode(long id, String code) {
        return RemoteDataSync.confirmCode(id, code);
    }

    public static User updateUser(User user) {
        return RemoteDataSync.updateUser(user);
    }

    public static User login(String phone, String mdp) {
        //UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        //User user = userDAO.findByPhoneNumer(phone);
        //if(user!=null) {
        //    if(user.password.equals(mdp)) {
        //        RemoteDataSync.getRandomUser();
        //        return user;
        //    }
        //}
        //user = new User();
        //user.error = true;
        //user.errorCode = 36212;
        //user.errorMessage = "Le numéro de téléphone et le mot de passe saisis ne correspondent pas. Veuillez réessayer SVP.";
        return RemoteDataSync.login(phone, mdp);
    }

    public static boolean changePassword(String acienMDP, String nouveauMDP) {
        return RemoteDataSync.updatePassword(acienMDP, nouveauMDP);
    }

    public static List<Categorie> listUserPreferenceCategorie() {
        CategorieDAO categorieDAO = CategorieDAO.getInstance(GeneralClass.applicationContext);
        List<Categorie> list = categorieDAO.getAllUserPreference();
        if(list.size()==0) RemoteDataSync.getUserPreference();
        return list;
    }

    public static List<User> listJobeurs(int min) {
        RemoteDataSync.getListJobeur((int)(min/20));
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        return userDAO.listJobeurs(min);
    }

    public static List<User> listJobeurs(int min, String keyword) {
        RemoteDataSync.getListJobeur(keyword, (int)(min/20));
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        return userDAO.listJobeurs(min, keyword);
    }

    public static List<Service> listRandomService(int min) {
        RemoteDataSync.getRandomServices((int)(min/20));
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        return serviceDAO.randomService(min);
    }

    public static List<Service> listNewService(int min, SousCategorie sousCategorie) {
        RemoteDataSync.getNewServices((int)(min/20), sousCategorie.getId());
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        return serviceDAO.getNewService(min, sousCategorie);
    }

    public static List<Service> listServiceByCote(int min, SousCategorie sousCategorie) {
        RemoteDataSync.getServicesByRealisationCount((int)(min/20), sousCategorie.getId());
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        return serviceDAO.getServiceByCote(min, sousCategorie);
    }

    public static List<Annonce> listNewAnnonce(int min, SousCategorie sousCategorie) {
        RemoteDataSync.getNewAnnonces((int)(min/20), sousCategorie.getId());
        AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
        return annonceDAO.getNewAnnonce(min, sousCategorie);
    }

    public static List<Annonce> listAnnonce(int min, SousCategorie sousCategorie) {
        RemoteDataSync.getAnnonces((int)(min/20), sousCategorie.getId());
        AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
        return annonceDAO.getAll(min, sousCategorie);
    }

    public static List<Annonce> listRandomAnnonce(int min) {
        RemoteDataSync.getRandomAnnonces((int)(min/20));
        AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
        return annonceDAO.randomAnnonce(min);
    }

    public static List<Service> listMesServices() {
        RemoteDataSync.getMesServices();
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        return serviceDAO.getMesServices();
    }

    public static List<Annonce> listMesAnnonces() {
        RemoteDataSync.getMesAnnonces();
        AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
        return annonceDAO.getMesAnnonces();
    }

    public static List<Sollicitation> listMesSollicitations() {
        RemoteDataSync.getMesSollicitations();
        SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
        return sollicitationDAO.getMesSollucitations();
    }

    public static List<Postuler> listMesPostulances() {
        RemoteDataSync.getMesPostulations();
        PostulerDAO postulerDAO = PostulerDAO.getInstance(GeneralClass.applicationContext);
        return postulerDAO.getMesPostulances();
    }

    public static List<Postuler> listPostulants(Annonce annonce) {
        RemoteDataSync.getPostulants(annonce.getId());
        PostulerDAO postulerDAO = PostulerDAO.getInstance(GeneralClass.applicationContext);
        return postulerDAO.getPostulants(annonce);
    }

    public static List<Sollicitation> listSollicitant(Service service) {
        RemoteDataSync.getSollicitations(service.getId());
        SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
        return sollicitationDAO.getSollicitants(service);
    }

    public static List<Categorie> listCategorie() {
        CategorieDAO categorieDAO = CategorieDAO.getInstance(GeneralClass.applicationContext);
        List<Categorie> list = categorieDAO.getAll();
        if(list.size()==0) list = RemoteDataSync.getListCategorie();
        return list;
    }

    public static List<SousCategorie> listSousCategorie(Categorie categorie) {
        SousCategorieDAO sousCategorieDAO = SousCategorieDAO.getInstance(GeneralClass.applicationContext);
        List<SousCategorie> list = sousCategorieDAO.getAll(categorie);
        if(list.size()==0) list = RemoteDataSync.getListSousCategorie();
        return list;
    }

    public static Sollicitation solliciter(Sollicitation sollicitation) {
        return RemoteDataSync.solliciter(sollicitation);
    }

    public static Service creerService(Service service) {
        return RemoteDataSync.publierService(service);
    }

    public static Annonce creerAnnonce(Annonce annonce) {
        return RemoteDataSync.publierAnnonce(annonce);
    }

    public static Postuler postuler(Postuler postuler) {
        return RemoteDataSync.postuler(postuler);
    }

    public static List<Object> historiqueBySollicitation() {
        List<Object> objectList = new ArrayList<>();
        objectList.add(RemoteDataSync.getHistoriqueRealisationBySollicitation());
        objectList.add(RemoteDataSync.getHistoriqueRealisationByPostulance());
        return objectList;
    }

    public boolean deconnection() {
        if(UserDAO.getInstance(GeneralClass.applicationContext).deconnection()>0) return true;
        return false;
    }

}
